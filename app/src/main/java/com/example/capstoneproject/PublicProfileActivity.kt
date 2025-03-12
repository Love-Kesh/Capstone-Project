package com.example.capstoneproject
import AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.capstoneproject.ui.theme.Styles

class PublicProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                PublicProfileScreen()
            }
        }
    }
}

@Composable
fun PublicProfileScreen() {
    val user = remember {
        UserProfile(
            name = "John Doe",
            username = "john_doe",
            profilePic = R.drawable.ic_launcher_foreground,
            interests = listOf("Hiking", "Reading", "Gaming")
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = user.profilePic),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(user.name, style = Styles.bigText)
        Text(user.username, style = Styles.smallText, color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))
        Text("Interests:")
        user.interests.forEach { interest ->
            Text("- $interest")
        }
    }
}

data class UserProfile(
    val name: String,
    val username: String,
    val profilePic: Int,
    val interests: List<String>
)
