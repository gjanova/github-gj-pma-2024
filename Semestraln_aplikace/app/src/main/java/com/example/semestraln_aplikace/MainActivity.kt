package com.example.semestraln_aplikace

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.semestraln_aplikace.ui.theme.Semestralní_aplikaceTheme
import com.example.semestraln_aplikace.viewmodel.WaterIntakeViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private val waterIntakeViewModel: WaterIntakeViewModel by viewModels()

    // Register for permission result callback
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            Toast.makeText(this, "Notifikace jsou vypnuty.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Check if user is signed in
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        setContent {
            Semestralní_aplikaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent(waterIntakeViewModel)
                }
            }
        }

        // Create Notification Channel
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Water Reminder Channel"
            val descriptionText = "Channel for water reminder notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("WATER_REMINDER_CHANNEL", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

fun setReminder(context: Context, interval: Long) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, ReminderReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    val triggerTime = System.currentTimeMillis() + interval * 60 * 1000

    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime, interval * 60 * 1000, pendingIntent)
}

@Composable
fun StatisticsScreen(waterIntakeViewModel: WaterIntakeViewModel) {
    val dailyIntake = waterIntakeViewModel.getDailyIntake()
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(dailyIntake.keys.toList()) { date ->
            val amount = dailyIntake[date]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = date, style = MaterialTheme.typography.titleLarge)
                    Text(text = "$amount ml", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun MainContent(waterIntakeViewModel: WaterIntakeViewModel) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        var amount by remember { mutableStateOf("") }
        var reminderInterval by remember { mutableStateOf(60L) }
        val context = LocalContext.current
        val auth = FirebaseAuth.getInstance()
        var showStatistics by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Množství (ml)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                val amountInt = amount.toIntOrNull()
                if (amountInt != null && amountInt > 0) {
                    waterIntakeViewModel.addWaterIntake(amountInt)
                    Toast.makeText(context, "Záznam o pití vody přidán", Toast.LENGTH_SHORT).show()
                    amount = ""
                } else {
                    Toast.makeText(context, "Prosím, zadejte platné množství", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Přidat záznam")
        }

        OutlinedTextField(
            value = reminderInterval.toString(),
            onValueChange = { reminderInterval = it.toLongOrNull() ?: 60L },
            label = { Text("Interval připomenutí (minuty)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                setReminder(context, reminderInterval)
                Toast.makeText(context, "Připomenutí nastaveno na každých $reminderInterval minut", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Nastavit připomenutí")
        }

        Button(
            onClick = {
                auth.signOut()
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
                (context as? ComponentActivity)?.finish()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Odhlásit se")
        }

        Button(
            onClick = { showStatistics = !showStatistics },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(if (showStatistics) "Zavřít statistiky" else "Zobrazit statistiky")
        }

        if (showStatistics) {
            StatisticsScreen(waterIntakeViewModel)
        }

        Text(
            text = "Záznamy o pití vody",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(waterIntakeViewModel.allIntakes) { intake ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "${intake.amount} ml", style = MaterialTheme.typography.titleLarge)
                        Text(text = formatTimestamp(intake.timestamp), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
    return sdf.format(timestamp)
}
