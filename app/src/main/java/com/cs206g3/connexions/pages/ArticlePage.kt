package com.cs206g3.connexions.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cs206g3.connexions.components.ArticleBody
import com.cs206g3.connexions.components.ExperienceTopBar
import com.cs206g3.connexions.components.PriceOverlay
import com.cs206g3.connexions.models.Post
import com.cs206g3.connexions.models.PostViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ArticlePage(viewModel: PostViewModel, navController: NavController, postId: Int) {
    val postState = viewModel.posts.observeAsState()
    Scaffold(
        topBar = {
            ExperienceTopBar(navController, Modifier.requiredHeight(40.dp))
        },
        bottomBar = {
            PriceOverlay()
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 10.dp)
    ) {
        Surface(modifier = Modifier.fillMaxSize().padding(it)) {
            ArticleBody(postState.value?.get(postId) ?: Post.DEFAULT)
        }
    }
}

@Preview
@Composable
fun PreviewArticle() {
    ArticlePage(PostViewModel(1), rememberNavController(),0)
}
