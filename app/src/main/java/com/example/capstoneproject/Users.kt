package com.example.capstoneproject

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val profilePicture: String, // URL or drawable resource
    val interests: List<String>,
    val bio: String,
    val uploadedImages: List<String>
)

object DummyUsers {
    private val users = listOf(
        User(
            id = 1,
            username = "alex_dating",
            email = "alex@example.com",
            password = "password123",
            profilePicture = "https://example.com/alex.jpg",
            interests = listOf("Hiking", "Gaming", "Music"),
            bio = "Love to explore new places and meet people!",
            uploadedImages = listOf("https://example.com/alex1.jpg", "https://example.com/alex2.jpg")
        ),
        User(
            id = 2,
            username = "sarah_smile",
            email = "sarah@example.com",
            password = "pass456",
            profilePicture = "https://example.com/sarah.jpg",
            interests = listOf("Yoga", "Travel", "Foodie"),
            bio = "Adventurer at heart, foodie by passion.",
            uploadedImages = listOf("https://example.com/sarah1.jpg", "https://example.com/sarah2.jpg")
        )
    )

    fun findUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }

    fun findUserById(userId: Int): User? {
        return users.find { it.id == userId }
    }
}
