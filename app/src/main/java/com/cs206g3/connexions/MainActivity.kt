package com.cs206g3.connexions

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cs206g3.connexions.components.BotNavBar
import com.cs206g3.connexions.components.DatePickerDialog
import com.cs206g3.connexions.components.LogoTopBar
import com.cs206g3.connexions.components.PopularExploreCard
import com.cs206g3.connexions.components.SearchBar
import com.cs206g3.connexions.components.TabBarItem
import com.cs206g3.connexions.models.Answer
import com.cs206g3.connexions.models.Post
import com.cs206g3.connexions.pages.ArticlePage
import com.cs206g3.connexions.pages.OnboardingDonePage
import com.cs206g3.connexions.pages.OnboardingPage
import com.cs206g3.connexions.models.PostViewModel
import com.cs206g3.connexions.pages.UserPreferenceViewModel
import com.cs206g3.connexions.ui.theme.ConnexionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var searchTerm by remember {
                mutableStateOf("Bras Basah, Singapore")
            }

            ConnexionsTheme {
                // A surface container using the 'background' color from the theme
                val searchTab = TabBarItem(
                    title = "Search",
                    selectedIcon = Icons.Filled.Search,
                    unselectedIcon = Icons.Outlined.Search
                )
                val savedTab = TabBarItem(
                    title = "Saved",
                    selectedIcon = Icons.Filled.Favorite,
                    unselectedIcon = Icons.Outlined.FavoriteBorder,
                )
                val historyTab = TabBarItem(
                    title = "History",
                    selectedIcon = ImageVector.vectorResource(R.drawable.baseline_history_24),
                    unselectedIcon = ImageVector.vectorResource(R.drawable.baseline_history_24)
                )
                val accountTab = TabBarItem(
                    title = "Account",
                    selectedIcon = Icons.Filled.AccountCircle,
                    unselectedIcon = Icons.Outlined.AccountCircle
                )
                // creating a list of all the tabs
                val tabBarItems = listOf(searchTab, savedTab, historyTab, accountTab)
                // creating our navController
                val navController = rememberNavController()

                val personId = (1 + (Math.random() * (60 - 1)).toInt()).toLong() //Min + (randNum * (Max - Min))
                val userPreferenceViewModel = UserPreferenceViewModel(personId)

                val postViewModel = PostViewModel(personId)

                NavHost(
                    navController = navController, startDestination = "onboarding-1",
                    enterTransition = {
                        this.slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                    },
                    exitTransition = {
                        this.slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                    },
                    modifier = Modifier.padding(vertical = 10.dp),
                ) {
                    composable("onboarding-1") {
                        userPreferenceViewModel.fetchAnswers()
                        val question1 =
                            "Please select at most two options that best describe your travel attitude"

//                        val answers1 = listOf(
//                            "Adrenaline Seeker",
//                            "Budget Traveller",
//                            "Culture Enthusiast",
//                            "Environmentally Friendly",
//                            "Foodie",
//                            "Relaxation Seeker",
//                            "Shopper"
//                        )

                        val answers1 = listOf(
                            Answer(1, "Lorem Picsum", "https://picsum.photos/200/300")
                        )
                        OnboardingPage(userPreferenceViewModel, 1, 4) {
                            navController.navigate("onboarding-2")
                        }
                    }

                    composable("onboarding-2") {
                        val question2 = "Which best matches your idea of an “authentic” experience?"

//                        val answers2 = listOf(
//                            "Cultural Immersion(Festivals, Cuisine, Customs)",
//                            "Exploring Local Neighborhoods",
//                            "Interacting with Fellow Travellers",
//                            "Interacting with Locals"
//                        )
                        val answers2 = listOf(
                            Answer(1, "Lorem Picsum", "https://picsum.photos/200/300")
                        )

                        OnboardingPage(userPreferenceViewModel,  2, 4) {
                            navController.navigate("onboarding-3")
                        }
                    }

                    composable("onboarding-3") {
                        val question3 =
                            "Which aspect(s) do you find the most troublesome about your travel experience?"

//                        val answers3 = listOf(
//                            "Budgeting & Deals",
//                            "Cultural Disparities",
//                            "Healthcare & Emergencies",
//                            "Language Barriers",
//                            "Navigation",
//                            "Personal Security & Belongings"
//                        )

                        val answers3 = listOf(
                            Answer(1, "Lorem Picsum", "https://picsum.photos/200/300")
                        )
                        OnboardingPage(userPreferenceViewModel, 3, 4) {
                            navController.navigate("onboarding-done")
                        }
                    }
                    composable("onboarding-done") {
                        // Purposely put here to fetch a bit earlier
                        OnboardingDonePage(
                            viewModel = userPreferenceViewModel,
                            postViewModel,
                            navController = navController,
                            pageNum = 4
                        )
                    }
                    composable(searchTab.title) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(11.dp, Alignment.Top),
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .padding(
                                    start = 25.dp,
                                    end = 15.dp,
                                    top = 10.dp
                                )
                                .fillMaxSize()
                                //.requiredWidth(width = 393.dp)
//                                .requiredHeight(height = 721.dp)
                        ) {
                            item {
                                LogoTopBar()
                            }
                            item {
                                SearchBar(onChange = { searchTerm = it })
                            }
                            item {
                                Row {
                                    DatePickerDialog("From:")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    DatePickerDialog("To:")
                                }
                                HorizontalDivider(modifier = Modifier.padding(top = 11.dp))
                            }
                            item {
                                Text(
                                    lineHeight = 1.sp,
                                    text = "Here are our top results for you in"
                                )
                                Text(
                                    lineHeight = 1.sp,
                                    style = TextStyle(
                                        color = Color(0xffff5c0a),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    text = searchTerm
                                )
                            }
                            postViewModel.getTags()?.let { it1 ->
                                items(it1.toTypedArray()) { recommendedTag ->
                                    Text(
                                        text = recommendedTag,
                                        color = Color(0xff373737),
                                        lineHeight = 1.31.em,
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            letterSpacing = (-0.32).sp
                                        ),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(11.dp))

                                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                                        postViewModel.posts.value?.let { it2 ->
                                            items(it2.filter { post -> post.tag == recommendedTag }) { post ->
                                                PopularExploreCard(navController, post)
                                                Spacer(modifier = Modifier.width(11.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    composable("article/{postId}") { backEntry ->
                        ArticlePage(postViewModel, navController, backEntry.arguments?.getString("postId")!!.toInt())
                    }
                    composable(savedTab.title) {
                        LogoTopBar()
                        BotNavBar(tabBarItems = tabBarItems, navController = navController)
                    }
                    composable(historyTab.title) {
                        LogoTopBar()
                        BotNavBar(tabBarItems = tabBarItems, navController = navController)
                    }
                    composable(accountTab.title) {
                        LogoTopBar()
                        BotNavBar(tabBarItems = tabBarItems, navController = navController)
                    }
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var searchTerm by remember {
        mutableStateOf("Dhoby Ghaut, Singapore")
    }


    // recommended tags, differs by user
    val recommendedTags = listOf(
        "Cafe/Restaurants",
        "Nature",
        "Night Life",
    )
    val posts = listOf<Post>()

    ConnexionsTheme {
        // A surface container using the 'background' color from the theme
        val searchTab = TabBarItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        )
        val savedTab = TabBarItem(
            title = "Saved",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
        )
        val historyTab = TabBarItem(
            title = "History",
            selectedIcon = ImageVector.vectorResource(R.drawable.baseline_history_24),
            unselectedIcon = ImageVector.vectorResource(R.drawable.baseline_history_24)
        )
        val accountTab = TabBarItem(
            title = "Account",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle
        )

        // creating a list of all the tabs
        val tabBarItems = listOf(searchTab, savedTab, historyTab, accountTab)

        // creating our navController
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .requiredHeight(43.dp)
                        .padding(start = 25.dp, top = 10.dp)
                )
            },
            bottomBar = {
                BotNavBar(tabBarItems, navController)
            }
        ) { paddings ->
            NavHost(
                navController = navController, startDestination = searchTab.title,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                },
                modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp),
            ) {
                composable(searchTab.title) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(11.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .requiredWidth(width = 393.dp)
                            .requiredHeight(height = 721.dp)
                            .padding(
                                start = 25.dp,
                                end = 25.dp,
                                top = paddings.calculateTopPadding() + 10.dp
                            )
                    ) {
                        item {
                            SearchBar(onChange = { searchTerm = it })
                        }
                        item {
                            Row {
                                DatePickerDialog("From:")
                                Spacer(modifier = Modifier.width(10.dp))
                                DatePickerDialog("To:")
                            }
                            Divider(modifier = Modifier.padding(top = 11.dp))
                        }
                        item {
                            Text(
                                lineHeight = 1.sp,
                                text = "Here are our top results for you in"
                            )
                            Text(
                                lineHeight = 1.sp,
                                style = TextStyle(
                                    color = Color(0xffff5c0a),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                text = searchTerm
                            )
                        }
                        items(recommendedTags) { recommendedTag ->
                            Text(
                                text = recommendedTag,
                                color = Color(0xff373737),
                                lineHeight = 1.31.em,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    letterSpacing = (-0.32).sp
                                ),
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(11.dp))

                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(posts.filter { post -> post.tag == recommendedTag }) { post ->
                                    PopularExploreCard(navController, post)
                                    Spacer(modifier = Modifier.width(11.dp))
                                }
                            }
                        }
                    }
                }
                composable(savedTab.title) {
                    Text(savedTab.title)
                }
                composable(historyTab.title) {
                    Text(historyTab.title)
                }
                composable(accountTab.title) {
                }
            }
        }
    }
}