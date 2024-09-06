package com.cs206g3.connexions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cs206g3.connexions.R
import com.cs206g3.connexions.models.Post
import com.cs206g3.connexions.models.Profile


@Composable
fun ArticleBody(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn {
            item {
                AsyncImage(
                    model = post.imageUrl,
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier.requiredHeight(290.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Column(modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 10.dp)) {
                    Text(
                        text = post.title,
                        color = Color(0xff373737),
                        lineHeight = 1.05.em,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.32).sp
                        ),
                    )
                    Text(
                        text = post.location,
                        color = Color(0xff373737),
                        lineHeight = 1.75.em,
                        style = TextStyle(
                            fontSize = 12.sp,
                            letterSpacing = (-0.32).sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ProfileCardBig(profile = post.profile)
                    HorizontalDivider(Modifier.padding(vertical = 12.dp))
                    Text(
                        text = post.body,color = Color.Black,
                        lineHeight = 1.75.em,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            letterSpacing = (-0.32).sp
                        ),
                        modifier = Modifier
                            .requiredWidth(width = 343.dp)
                    )
                }
            }
        }
    }
}
