package com.example.semestraln_aplikace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.semestraln_aplikace.ui.theme.Semestralní_aplikaceTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Semestralní_aplikaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Heslo") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                Log.d("LoginScreen", "Tlačítko Přihlásit se bylo kliknuto")
                Toast.makeText(context, "Přihlašování...", Toast.LENGTH_SHORT).show()
                auth.signInWithEmailAndPassword(email.trim(), password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, navigate to MainActivity
                            Log.d("LoginScreen", "Přihlášení úspěšné")
                            Toast.makeText(context, "Přihlášení úspěšné", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                            // finish current activity
                            (context as? ComponentActivity)?.finish()
                        } else {
                            // If sign in fails, display a message to the user
                            task.exception?.let {
                                Log.e("LoginScreen", "Přihlášení selhalo: ${it.message}")
                                when (it) {
                                    is FirebaseAuthInvalidUserException -> {
                                        Toast.makeText(context, "Email není registrován", Toast.LENGTH_SHORT).show()
                                    }
                                    is FirebaseAuthInvalidCredentialsException -> {
                                        if (it.message?.contains("The email address is badly formatted") == true) {
                                            Toast.makeText(context, "Emailová adresa je špatně formátována", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context, "Přihlášení selhalo: ${it.message}", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    else -> {
                                        Toast.makeText(context, "Přihlášení selhalo: ${it.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Přihlásit se")
        }

        TextButton(
            onClick = {
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Nemáte účet? Zaregistrujte se zde")
        }
    }
}
