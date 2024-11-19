package com.example.firstapplecture


/**
 * UserData is a data class that holds user information.
 * It includes nickname, email, birth date, gender, height, weight, and activity level.
 */

data class UserData(
    val nick: String,
    val email: String,
    val birthDate: String,
    val gender: Gender,
    val height: Float,
    val weight: Float,
    val activityLevel: Int
) : java.io.Serializable
