package com.cs206g3.connexions.models

data class Answer(
    val id: Long,
    val title: String,
    val imageUrl: String
)

fun OnboardingAnswer.toAnswer() = Answer(
    id, name, image_link?: "https://picsum.photos/300/200"
)
