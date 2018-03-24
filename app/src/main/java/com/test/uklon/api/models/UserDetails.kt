package com.test.uklon.api.models

data class UserDetails(
        val user: User,
        val posts: List<Post>
)