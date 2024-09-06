package com.cs206g3.connexions.models

data class OnboardingAnswer(
    val id: Long,
    val name: String,
    val description: String,
    val image_link: String?,
    val location: String,
    val label: String,
)

data class OnboardingQuestionResponse(
    val listingIds: List<Long>,
    val personId: Long,
)

data class OnboardingResponse(
    val message: String,
)

data class Local(
    val username: String,
    val name: String,
    val numReviews: Int,
    val avatarUrl: String?,
    val rating: Double?,
)

data class Experience(
    val title: String,
    val image_link: String?,
    val location: String,
    val description: String,
    val currency: String,
    val price: Int,
)

data class ListingRequest(
    val personId: Long
)

data class ListingResponse(
    val label: String,
    val local: Local,
    val experience: Experience
)