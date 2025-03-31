package com.example.capstoneproject

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip

import AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

class PublicProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Assume that you are passing a valid user to display
        val user = DummyUsers.getRandomUserExcluding("")
        setContent {
            AppTheme {
                if (user != null) {
                    PublicProfileScreen(user)
                } else {
                    Text("No user found", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun PublicProfileScreen(user: User) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surfaceVariant,
            MaterialTheme.colorScheme.background
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ProfileCard(user)
        }
    }
}

@Composable
fun ProfileCard(user: User) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(user.profilePicture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(user.username, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(user.bio, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(horizontal = 8.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Interests", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                user.interests.forEach { interest ->
                    Text("• $interest", style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (user.uploadedImages.isNotEmpty()) {
                Text("Photos", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
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
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Like functionality */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("❤️ Like", color = MaterialTheme.colorScheme.onSecondary)
                }
                Button(
                    onClick = { /* Message functionality */ },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("💬 Message", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}
