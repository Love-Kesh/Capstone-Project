package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject.ui.theme.Styles
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Always clear login state on app restart for testing
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        setContent {
            SplashScreen {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}

@Composable
fun SplashScreen(onAnimationEnd: () -> Unit) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Splash screen duration
        visible = false
        delay(500) // Allow fade out animation
        onAnimationEnd()
    }
    if (visible) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Styles.splashBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Lif", style = Styles.bigText.copy(color = Color.White))
            Text(text = "Love is free!", style = Styles.smallText.copy(color = Color.White))
        }
    }
}
