package com.cs206g3.connexions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ProgressBar(current: Int, total: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 12.dp)
    ) {
        for (i in 1..current) {
            Box(
                modifier = Modifier
                    .requiredHeight(height = 7.dp)
                    .weight(weight = 0.25f)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xffff5c0a))
            )
        }

        for (i in current + 1..total) {
            Box(
                modifier = Modifier
                    .requiredHeight(height = 7.dp)
                    .weight(weight = 0.25f)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xffd9d9d9))
            )
        }
    }
}