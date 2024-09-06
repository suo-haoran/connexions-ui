package com.cs206g3.connexions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.cs206g3.connexions.R
import com.cs206g3.connexions.models.Post
import com.cs206g3.connexions.models.Profile
import com.cs206g3.connexions.ui.theme.LightOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularExploreCard(navController: NavController,post: Post) {
    Surface(
        color = LightOrange,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .requiredSize(235.dp, 249.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable { navController.navigate("article/${post.id}") }
    ) {
        Column {
            Box(
                modifier = Modifier.requiredSize(235.dp, 142.dp)
            ) {

                AsyncImage(
                    model = post.imageUrl,
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                InputChip(
                    label = {
                        Text(
                            text = post.tag,
                            color = Color(0xff373737),
                            textAlign = TextAlign.Center,
                            lineHeight = 2.2.em,
                            style = TextStyle(
                                fontSize = 10.sp,
                                letterSpacing = (-0.41).sp),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically))
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color(0xffdddddd)
                    ),
                    selected = true,
                    onClick = { },
                    modifier = Modifier.offset(15.dp, 2.dp))
//                ElevatedSuggestionChip(
//                    onClick = {},
//                    label = { Text(post.tag, fontSize = 10.sp) },
//                    modifier = Modifier
//                        .align(Alignment.TopStart)
//                        .offset(15.dp, 5.dp)
//                )
            }
            Column(modifier = Modifier.padding(start = 10.dp, top = 8.dp)) {
                ProfileCardSmall(profile = post.profile)

                Text(
                    text = post.title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 7.dp, bottom = 1.dp))
                Text(
                    text = post.body,
                    maxLines = 1,
                    fontSize = 9.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.requiredWidth(170.dp)
                )
            }
        }
    }
}

@Composable
fun ExploreCard(post: Post, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffffdaa1),
            contentColor = Color(0xff373737)
        ),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xffffdaa1))
            .requiredWidth(343.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 343.dp)
                .requiredHeight(height = 96.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 343.dp)
            ) {
                Row(modifier = modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 137.dp)
                            .requiredHeight(height = 96.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.japan),
                            contentDescription = "experience image",
                            modifier = Modifier
                                .requiredWidth(width = 143.dp)
                                .requiredHeight(height = 96.dp)
                                .clip(
                                    shape = RoundedCornerShape(
                                        topStart = 10.dp,
                                        bottomStart = 10.dp
                                    )
                                )
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 5.dp,
                                    y = 5.dp
                                )
                                .requiredHeight(height = 25.dp)
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = Color(0xffdddddd))
                                .padding(
                                    horizontal = 12.dp,
                                )
                        ) {
                            Text(
                                text = post.tag,
                                color = Color(0xff373737),
                                textAlign = TextAlign.Center,
                                lineHeight = 2.2.em,
                                style = TextStyle(
                                    fontSize = 9.sp,
                                    letterSpacing = (-0.41).sp
                                ),
                                modifier = Modifier
                                    .requiredHeight(13.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically)
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(start=13.dp, 5.dp)) {
                        ProfileCardSmall(profile = post.profile, modifier = Modifier.padding(bottom = 7.dp))
                        Text(
                            text = post.title,
                            color = Color(0xff33363f),
                            lineHeight = 1.25.em,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.32).sp),
                            modifier = Modifier
                                .requiredWidth(width = 176.dp)
                                .requiredHeight(height = 30.dp),
                            )
                    }
                }
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun ExploreCardPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val profile = Profile( "ph_tan", "Phoebe Tan", "https://picsum.photos/200",3.5)
        val post = Post(0, "Daily Life", "Walk Around Bugis with Me!", profile, "SGD", 200, "Bugis, Singapore", "")
        PopularExploreCard(rememberNavController(), post)
    }
}