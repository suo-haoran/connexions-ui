package com.cs206g3.connexions.models

import com.cs206g3.connexions.R

data class Profile(
    val username: String,
    val name: String,
    val avatarUrl: String,
    val rating: Double,
    val numReviews: Int = (Math.random() * 1002).toInt()
) {
    companion object {
        val DEFAULT = Profile( "", "", "",0.0, 0)
    }
}

fun Local.toProfile(): Profile =
    Profile(
        username, name, avatarUrl ?: "", rating ?: 0.0, numReviews
    )
