package com.example.capstoneproject

import AppTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    val context = LocalContext.current
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
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
                    .fillMaxWidth(0.9f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sign Up", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                var username by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") },
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
                    onClick = { /* Handle sign-up logic */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sign Up")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Already a member? Login",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.clickable {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}
