
package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.capstoneproject.ui.theme.Styles

class UserProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userEmail = intent.getStringExtra("userEmail") ?: return
        val user = DummyUsers.findUserByEmail(userEmail)

        setContent {
            user?.let {
                UserProfileScreen(it) { otherUserId ->
                    val intent = Intent(this, PublicProfileActivity::class.java)
                    intent.putExtra("userId", otherUserId)
                    startActivity(intent)
                }
            } ?: run {
                Text("User not found", color = Color.Red)
            }
        }
    }
}

@Composable
fun UserProfileScreen(user: User, onViewOtherProfile: (Int) -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Picture
        Image(
            painter = rememberAsyncImagePainter(user.profilePicture),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Name and Email
        Text(
            text = user.username,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Bio Section
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = Color(0xFFF0F0F0), // Light grey background for bio section
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = user.bio,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Interests Section
        Text(text = "Interests:", style = Styles.mediumText)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            user.interests.forEach { interest ->
                Text(
                    text = "• $interest",
                    style = Styles.smallText,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Uploaded Images Section
        Text(text = "Uploaded Photos:", style = Styles.mediumText)
        Spacer(modifier = Modifier.height(8.dp))

        // Scrollable Images
        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(user.uploadedImages) { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Uploaded Photo",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp)), // Rounded edges for images
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // View Another User Button
        Button(
            onClick = { onViewOtherProfile(2) },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Custom color for the button
        ) {
            Text("View Sarah's Public Profile", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Settings Button to navigate to Settings Activity
        Button(
            onClick = {
                context.startActivity(Intent(context, SettingsActivity::class.java))
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(horizontal = 16.dp)
        ) {
            Text("Settings")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
