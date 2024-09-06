package com.cs206g3.connexions.pages

import android.util.Log
import android.util.Printer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.cs206g3.connexions.R
import com.cs206g3.connexions.components.LogoTopBar
import com.cs206g3.connexions.components.ProgressBar
import com.cs206g3.connexions.components.QuestionCard
import com.cs206g3.connexions.components.TextWithBoldings
import com.cs206g3.connexions.components.TextWithHighlight
import com.cs206g3.connexions.models.OnboardingAnswer
import com.cs206g3.connexions.models.OnboardingQuestionResponse
import com.cs206g3.connexions.models.PostViewModel
import com.cs206g3.connexions.models.toAnswer
import com.cs206g3.connexions.services.ApiService
import com.cs206g3.connexions.ui.theme.LightOrange
import com.cs206g3.connexions.ui.theme.Orange
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UserPreference(
    val ids: List<Long>
) {
    fun toOnboardingResponse(personId: Long): OnboardingQuestionResponse =
        OnboardingQuestionResponse(ids, personId)
}

class UserPreferenceViewModel(private val personId: Long) : ViewModel() {
    private val _answers = MutableStateFlow(UserPreference(mutableListOf()))
    val answers = _answers.asStateFlow()
    private val _questions: MutableLiveData<List<OnboardingAnswer>> = MutableLiveData(null)
    val questions:LiveData<List<OnboardingAnswer>>
        get() = _questions
    fun updateAnswers(answerIds: List<Long>) {
        _answers.update { currentState ->
            val newIds = currentState.ids.toMutableList()
            newIds.addAll(answerIds)
            currentState.copy(
                ids = newIds
            )
        }
        Log.i("ViewModel", "ids now ${answers.value.ids}")
    }

    fun fetchAnswers() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = ApiService.getAnswers()
            launch(Dispatchers.Main) {
                result.forEach { Log.d("Answers", it.image_link?:"")}
                _questions.value = result
            }
        }
    }

    suspend fun sendAnswers() =
        viewModelScope.async {
            ApiService.postQuestions(answers.value.toOnboardingResponse(personId))
        }
}


@Composable
fun OnboardingPage(
    viewModel: UserPreferenceViewModel,
    currentPageNum: Int,
    totalPageNum: Int,
    modifier: Modifier = Modifier,
    nextOnboardingPage: () -> Unit
) {
    val selectedItems = remember {
        mutableStateListOf<Long>()
    }


    val questions = viewModel.questions.observeAsState()
    val question = "Please select at most two options that best describe your travel attitude"

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {
            LogoTopBar(leftPadding = 25.dp)
        }

        item {
            TextWithHighlight(
                text = "Help us find the **best experiences** for you with these short questions!",
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
            )
        }

        item {
            ProgressBar(currentPageNum, totalPageNum)
        }

        item {
            TextWithBoldings(
                text = question,
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
            )
        }

        val startElement = (currentPageNum - 1) * 4
        val endElement = startElement + 3
        items(questions.value?.toTypedArray()?.sliceArray(startElement..endElement)?: emptyArray()) { onboardingAnswer ->
            val isSelected = selectedItems.contains(onboardingAnswer.id)
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                QuestionCard(answer = onboardingAnswer.toAnswer()) {
                    if (isSelected) {
                        selectedItems.remove(onboardingAnswer.id)
                    } else {
                        selectedItems.add(onboardingAnswer.id)
                    }
                }
            }
        }

        item {
            Button(
                onClick = {
                    viewModel.updateAnswers(selectedItems)
                    nextOnboardingPage()
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffff5c0a)),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 7.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        5.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 343.dp)
                ) {
                    Text(
                        text = if (currentPageNum != totalPageNum - 1) "Next" else "Done",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.38.em,
                        style = TextStyle(
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingDonePage(
    viewModel: UserPreferenceViewModel,
    postViewModel: PostViewModel,
    navController: NavController,
    pageNum: Int
) {
    // loading = true
    var loading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = loading) {
        // Make api call here
        val res = viewModel.sendAnswers().await()
        if (!res) {
            Log.i("Send Ans", "Not Successful")
        }
        val job = postViewModel.fetchPosts()
        job.join()
        delay(500)
        loading = false
    }

    if (loading) {
        // show loading screen
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = Orange,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Text(
                    text = "Getting things ready for you",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 30.dp)
                )
        }
    } else {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            LogoTopBar(25.dp)
            Text(
                text = "Help us find the best experiences for you with these short questions!",
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 25.dp)
            )
            ProgressBar(pageNum, pageNum)
            Image(
                painter = painterResource(id = R.drawable.plane),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 25.dp)
                    .requiredSize(166.dp, 164.dp)
                    .align(Alignment.CenterHorizontally)
            )


            Text(
                textAlign = TextAlign.Center,
                lineHeight = 1.sp,
                text = "Wow, that was quick!",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 25.dp)
            )

            Text(
                textAlign = TextAlign.Center,
                lineHeight = 1.sp,
                text = "Your answers will be used to find experiences that",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                textAlign = TextAlign.Center,
                lineHeight = 1.sp,
                text = "travellers just like you love the most!",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    navController.navigate("Search")
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffff5c0a)),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 7.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        5.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .requiredWidth(width = 343.dp)
                ) {
                    Text(
                        text = "Continue to app",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.38.em,
                        style = TextStyle(
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun OnboardingPagePreview() {
    val viewModel = UserPreferenceViewModel(1)
    OnboardingPage(viewModel,  1, 4) {}


//    val question2 = "Which best matches your idea of an “authentic” experience?"
//
//    val answers2 = listOf(
//        "Cultural Immersion(Festivals, Cuisine, Customs)",
//        "Exploring Local Neighborhoods",
//        "Interacting with Fellow Travellers",
//        "Interacting with Locals"
//    )
//    OnboardingPage(viewModel,question2, answers2, 1, 4) {}


//    val question3 = "Which aspect(s) do you find the most troublesome about your travel experience?"
//
//    val answers3 = listOf(
//        "Budgeting & Deals",
//        "Cultural Disparities",
//        "Healthcare & Emergencies",
//        "Language Barriers",
//        "Navigation",
//        "Personal Security & Belongings"
//    )
//    OnboardingPage(viewModel,question3, answers3, 3, 4) {}
//    OnboardingDonePage(viewModel = viewModel, navController = rememberNavController(), pageNum = 4)
}