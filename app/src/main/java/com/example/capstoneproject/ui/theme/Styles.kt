package com.example.capstoneproject.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Styles {
    // Text styles
    val bigText = TextStyle(fontSize = 24.sp, color = Color.Black)
    val mediumText = TextStyle(fontSize = 20.sp, color = Color.Black)
    val smallText = TextStyle(fontSize = 14.sp, color = Color.Gray)

    // Button styles
    val primaryButton = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)

    // TextField styles
    val outlinedTextField = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)

    // Spacer styles
    val smallSpacer = Modifier.height(8.dp)
    val mediumSpacer = Modifier.height(16.dp)
    val largeSpacer = Modifier.height(24.dp)

    // Background colors
    val splashBackground = Color.Black
    val appBackground = Color.White

    // Error text style
    val errorText = TextStyle(fontSize = 14.sp, color = Color.Red)
}
