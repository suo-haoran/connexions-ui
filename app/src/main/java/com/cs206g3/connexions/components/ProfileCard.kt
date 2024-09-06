package com.cs206g3.connexions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cs206g3.connexions.R
import com.cs206g3.connexions.models.Profile

@Composable
fun ProfileCardBig(profile: Profile, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        modifier = modifier
    ) {
        // Image(
        //     painter = painterResource(id = profile.id),
        //     contentDescription = "Profile Image",
        //     modifier = Modifier
        //         .requiredSize(size = 79.dp)
        //         .clip(shape = RoundedCornerShape(100.dp))
        // )

        AsyncImage(
            model = if (profile.avatarUrl == "") "https://picsum.photos/79" else profile.avatarUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                     .requiredSize(size = 79.dp)
                     .clip(shape = RoundedCornerShape(100.dp))
        )
        Column(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = profile.name,
                    color = Color(0xff373737),
                    textAlign = TextAlign.Center,
                    lineHeight = 0.95.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        letterSpacing = (-0.32).sp))

                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = "Check_round_fill",
                    tint = Color(0xffff5500),
                    modifier = Modifier
                        .requiredSize(size = 21.dp))
            }
            Text(
                text = "@${profile.username}",
                color = Color(0xff373737),
                textAlign = TextAlign.Center,
                lineHeight = 1.08.em,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = (-0.32).sp),
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start)
            ) {
                RatingBarBall(rating = profile.rating, 14)
                Text(
                    text = "${profile.rating} (${profile.numReviews} reviews)",
                    color = Color(0xff33363f),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.08.em,
                    style = TextStyle(
                        fontSize = 13.sp,
                        letterSpacing = (-0.32).sp
                    ),
                    modifier = Modifier.offset(y = (-2).dp)
                )
            }
        }
    }
}


@Composable
fun ProfileCardSmall(profile: Profile, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        modifier = modifier
    ) {
        AsyncImage(
            model = if (profile.avatarUrl == "") "https://picsum.photos/45" else profile.avatarUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .requiredSize(size = 45.dp)
                .clip(shape = RoundedCornerShape(100.dp))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
            modifier = Modifier
                .requiredHeight(height = 45.dp)
        ) {
            Text(
                text = "@${profile.name}",
                color = Color(0xff33363f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                lineHeight = 1.em,
                style = TextStyle(
                    fontSize = 11.sp,
                    letterSpacing = (-0.32).sp
                ),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start)
            ) {
                RatingBarBall(rating = profile.rating, 10)
                Text(
                    text = "${profile.rating} (${profile.numReviews} reviews)",
                    color = Color(0xff33363f),
                    textAlign = TextAlign.Center,
                    lineHeight = 1.08.em,
                    style = TextStyle(
                        fontSize = 9.sp,
                        letterSpacing = (-0.32).sp
                    ),
                    modifier = Modifier.offset(y = (-2).dp)
                )
            }
        }
    }
}


@Composable
fun RatingBarBall(rating: Double, ballSize: Int) {
    if (rating > 5) {
        throw IllegalArgumentException("Rating can't be more than 5")
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start)
    ) {
        var i = 0
        while (i < rating.toInt()) {
            Box(
                modifier = Modifier
                    .requiredSize(size = ballSize.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffff5c0a))
            )
            i++
        }
        val ratingDecimal = rating - rating.toInt()

        if (ratingDecimal > 0) {
            Image(
                painter = painterResource(id = R.drawable.half_star),
                contentDescription = "Half Star",
                modifier = Modifier
                    .requiredSize(size = ballSize.dp)
                    .border(width = 1.dp, shape = CircleShape, color = Color(0xffff5c0a))
                    .offset(
                        x = (-3).dp,
                    )
            )
            i++
        }
        while (i < 5) {
            Box(
                modifier = Modifier
                    .requiredSize(ballSize.dp)
                    .clip(shape = CircleShape)
                    .border(1.dp, shape = CircleShape, color = Color(0xffff5c0a))
            )
            i++
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    val profile = Profile( "ph_tan", "Phoebe Tan", "https://picsum.photos/200",3.5)
    ProfileCardBig(profile, modifier = Modifier)
}