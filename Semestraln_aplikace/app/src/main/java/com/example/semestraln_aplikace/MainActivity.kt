package com.example.semestraln_aplikace

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private val waterIntakeViewModel: WaterIntakeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is signed in
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
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
        val context = LocalContext.current
        val auth = FirebaseAuth.getInstance()

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount (ml)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val amountInt = amount.toIntOrNull()
                if (amountInt != null && amountInt > 0) {
                    waterIntakeViewModel.addWaterIntake(amountInt)
                    Toast.makeText(context, "Water intake added", Toast.LENGTH_SHORT).show()
                    amount = ""
                } else {
                    Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Add Water Intake")
        }

        Button(
            onClick = {
                auth.signOut()
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
                // finish current activity
                (context as? ComponentActivity)?.finish()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Odhlásit se")
        }

        val allIntakes by waterIntakeViewModel.getAllIntakes().collectAsState(initial = emptyList())

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(allIntakes) { intake ->
                Text(text = "${intake.amount} ml - ${formatTimestamp(intake.timestamp)}")
            }
        }
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
    return sdf.format(timestamp)
}
