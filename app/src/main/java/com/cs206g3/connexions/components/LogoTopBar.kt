package com.cs206g3.connexions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cs206g3.connexions.R

@Composable
fun LogoTopBar(leftPadding: Dp = 0.dp) {
    Row(modifier=Modifier.fillMaxWidth().height(43.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillHeight,
            modifier = if (leftPadding != 0.dp) Modifier
                .requiredHeight(43.dp)
                .padding(start = leftPadding, top = 10.dp)
                else Modifier
                .requiredHeight(43.dp)
                .padding(top = 10.dp)
        )
    }
}


@Preview
@Composable
fun LogoPreview() {
    LogoTopBar()
}