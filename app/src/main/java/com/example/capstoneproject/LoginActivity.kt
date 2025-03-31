package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val backgroundBrush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    MaterialTheme.colorScheme.background
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundBrush),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Sign in", style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(16.dp))
                        var email by remember { mutableStateOf("") }
                        var password by remember { mutableStateOf("") }
                        var loginError by remember { mutableStateOf(false) }
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                if (DummyUsers.validateUser(email, password)) {
                                    val intent = Intent(this@LoginActivity, UserProfileActivity::class.java)
                                    intent.putExtra("userEmail", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    loginError = true
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Login")
                        }
                        if (loginError) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Invalid credentials", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Not a member? Sign up",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .clickable {
                                    val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                                    startActivity(intent)
                                }
                                .padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
