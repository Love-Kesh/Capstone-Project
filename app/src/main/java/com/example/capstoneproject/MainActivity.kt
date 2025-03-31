package com.example.capstoneproject

import androidx.compose.ui.draw.alpha

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.capstoneproject.ui.theme.Styles
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Clear login state for testing
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
    var visible by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )
    LaunchedEffect(Unit) {
        visible = true
        delay(2000)
        onAnimationEnd()
    }
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color.Black, Color.DarkGray)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Lif",
                style = Styles.bigText.copy(color = Color.White),
                modifier = Modifier.alpha(alphaAnim.value)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Love is free!",
                style = Styles.smallText.copy(color = Color.White),
                modifier = Modifier.alpha(alphaAnim.value)
            )
        }
    }
}
