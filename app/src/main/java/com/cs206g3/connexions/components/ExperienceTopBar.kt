package com.cs206g3.connexions.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cs206g3.connexions.ui.theme.Orange

@Composable
fun ExperienceTopBar(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .padding(start = 15.dp, end = 25.dp)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 35.dp)
            ) {
                Image(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconToggleButton(
                checked = true,
                onCheckedChange = { },
                modifier = Modifier
                    .width(54.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
                    .border(
                        border = BorderStroke(1.dp, Color(0xffdddddd)),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(
                        horizontal = 15.dp,
                        vertical = 7.dp
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Fav",
                        tint = Color(0xff373737),
                    )
                }
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .width(54.dp)
                    .clip(shape = RoundedCornerShape(14.dp))
                    .border(
                        border = BorderStroke(1.dp, Color(0xffdddddd)),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(
                        horizontal = 15.dp,
                        vertical = 7.dp
                    )

            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "share 1",
                        tint = Color(0xff373737),
                        modifier = Modifier
                            .requiredSize(size = 23.dp)
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 38)
@Composable
private fun ExperienceTopBarPreview() {
    ExperienceTopBar(rememberNavController(), Modifier)
}
