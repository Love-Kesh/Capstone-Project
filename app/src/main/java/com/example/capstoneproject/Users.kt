package com.example.capstoneproject

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val profilePicture: String,
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
        ),
        User(
            id = 3,
            username = "john_doe",
            email = "john@example.com",
            password = "john123",
            profilePicture = "https://example.com/john.jpg",
            interests = listOf("Cycling", "Photography", "Tech"),
            bio = "Tech geek who loves photography.",
            uploadedImages = listOf("https://example.com/john1.jpg", "https://example.com/john2.jpg")
        )
    )

    fun validateUser(email: String, password: String): Boolean {
        return users.any { it.email == email && it.password == password }
    }

    fun findUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }

    fun getRandomUserExcluding(loggedInEmail: String): User? {
        val otherUsers = users.filter { it.email != loggedInEmail }
        return otherUsers.randomOrNull() // Returns a random user or null if empty
    }
}
