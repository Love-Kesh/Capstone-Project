package com.example.capstoneproject

import AppTheme
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.capstoneproject.ui.theme.Styles

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
    val context = LocalContext.current  // Get the current context

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Styles.appBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sign Up", style = Styles.bigText)

            Spacer(modifier = Styles.mediumSpacer)

            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Styles.outlinedTextField
            )
            Spacer(modifier = Styles.smallSpacer)

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                modifier = Styles.outlinedTextField
            )
            Spacer(modifier = Styles.smallSpacer)

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Styles.outlinedTextField
            )
            Spacer(modifier = Styles.mediumSpacer)

            Button(
                onClick = { /* Handle sign-up */ },
                modifier = Styles.primaryButton
            ) {
                Text("Sign Up")
            }

            Spacer(modifier = Styles.mediumSpacer)

            Text(
                "Already a member? Login",
                style = Styles.smallText.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}
