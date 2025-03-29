package com.example.capstoneproject

import AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.capstoneproject.ui.theme.Styles

class PublicProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggedInEmail = intent.getStringExtra("userEmail") ?: ""

        setContent {
            AppTheme {
                PublicProfileScreen(loggedInEmail)
            }
        }
    }
}

@Composable
fun PublicProfileScreen(loggedInEmail: String) {
    val randomUser = remember { DummyUsers.getRandomUserExcluding(loggedInEmail) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Styles.appBackground)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (randomUser == null) {
            Text("No other users found", style = Styles.errorText)
        } else {
            ProfileCard(randomUser)
        }
    }
}

@Composable
fun ProfileCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture
            Image(
                painter = rememberAsyncImagePainter(user.profilePicture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Styles.mediumSpacer)

            // User Name
            Text(
                text = user.username,
                style = Styles.bigText.copy(fontSize = 28.sp)
            )

            Spacer(modifier = Styles.smallSpacer)

            // Bio
            Text(
                text = user.bio,
                style = Styles.mediumText,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Styles.largeSpacer)

            // Interests Section
            Text("Interests", style = Styles.mediumText.copy(fontSize = 20.sp))

            Spacer(modifier = Styles.smallSpacer)

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                user.interests.forEach { interest ->
                    Text("• $interest", style = Styles.smallText)
                }
            }

            Spacer(modifier = Styles.largeSpacer)

            // Uploaded Images Carousel
            if (user.uploadedImages.isNotEmpty()) {
                Text("Photos", style = Styles.mediumText.copy(fontSize = 20.sp))
                Spacer(modifier = Styles.smallSpacer)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    user.uploadedImages.forEach { imageUrl ->
                        Image(
                            painter = rememberAsyncImagePainter(imageUrl),
                            contentDescription = "Uploaded Image",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Styles.largeSpacer)
            }

            // Like & Message Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Like functionality */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC0CB)) // Light pink
                ) {
                    Text("❤️ Like", color = Color.Black)
                }

                Button(
                    onClick = { /* Message functionality */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFADD8E6)) // Light blue
                ) {
                    Text("💬 Message", color = Color.Black)
                }
            }
        }
    }
}
