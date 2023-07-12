package com.example.suitmediatest.data

data class Response(
    val data: List<UserResponseItem>
)

data class UserResponseItem(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String,
)