package com.cs206g3.connexions.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs206g3.connexions.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel(private val personId: Long) : ViewModel() {
    private val _posts = MutableLiveData(listOf<Post>())
    val posts : LiveData<List<Post>> get() = _posts

    fun getTags() = _posts.value?.map { it.tag }?.toSet()

    fun fetchPosts() =
        viewModelScope.launch(Dispatchers.IO) {
            val result = ApiService.getRecommendationListing(ListingRequest(personId))
            Log.d("Posts", result.size.toString())
            launch(Dispatchers.Main) {
                _posts.value = result.mapIndexed { idx: Int, res: ListingResponse->
                    Log.d("Posts", res.experience.image_link?:"")
                    res.toPost(idx)
                }
            }
        }

}

