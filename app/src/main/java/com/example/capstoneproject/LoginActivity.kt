package com.example.capstoneproject

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
import androidx.compose.ui.unit.dp
import com.example.capstoneproject.ui.theme.Styles

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var loginError by remember { mutableStateOf(false) }
            var randomUser by remember { mutableStateOf<User?>(null) }

            LaunchedEffect(Unit) {
                randomUser = DummyUsers.getRandomUserExcluding("")
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Styles.appBackground),
                contentAlignment = Alignment.Center // Centers the Column inside the Box
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f) // Adjust width for better UI balance
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Sign in", style = Styles.bigText)

                    Spacer(modifier = Styles.mediumSpacer)

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
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
                        onClick = {
                            if (DummyUsers.validateUser(email, password)) {
                                // Navigate to UserProfileActivity if login is successful
                                val intent =
                                    Intent(this@LoginActivity, UserProfileActivity::class.java)
                                intent.putExtra("userEmail", email) // Pass user email to the profile
                                startActivity(intent)
                                finish() // Close the current activity to prevent back navigation to login
                            } else {
                                loginError = true
                            }
                        },
                        modifier = Styles.primaryButton
                    ) {
                        Text("Login")
                    }

                    if (loginError) {
                        Spacer(modifier = Styles.smallSpacer)
                        Text("Invalid credentials", style = Styles.errorText)
                    }

                    Spacer(modifier = Styles.largeSpacer)

                    Text(
                        "Not a member? Sign up",
                        style = Styles.smallText.copy(color = MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable {
                                // Navigate to SignUpActivity for new users
                                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                                startActivity(intent)
                            }
                    )
                }
            }
        }
    }
}
