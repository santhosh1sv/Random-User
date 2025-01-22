package com.nisum.test.randomuser.ui.data

import kotlinx.serialization.Serializable

@Serializable
object RandomUsersRoute

@Serializable
data class RandomUsersDetailRoute(
    val firstName: String,
    val lastName: String,
    val address: String,
    val profilePicture: String,
    val gender: String,
    val email: String,
    val age: Int,
    val phone: String
)