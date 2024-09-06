package com.cs206g3.connexions.models

data class Post(
    val id: Int,
    val tag: String,
    val title: String,
    val profile: Profile,
    val currency: String,
    val price: Int,
    val location: String,
    val imageUrl: String?,
    val body: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with deskt",
) {
    companion object {
        val DEFAULT =
            Post(
                -1, "", "Lorem Ipsum", Profile.DEFAULT, "SGD", 10, "Bugis, Singapore",  "https://picsum.photos/235/142",
            )
    }
}

fun ListingResponse.toPost(id: Int): Post = Post(
    id,
    label,
    experience.title,
    local.toProfile(),
    experience.currency,
    experience.price,
    experience.location,
    experience.image_link ?: "https://picsum.photos/235/142",
    experience.description,
)

