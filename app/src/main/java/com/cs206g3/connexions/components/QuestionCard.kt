package com.cs206g3.connexions.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cs206g3.connexions.models.Answer
import com.cs206g3.connexions.ui.theme.LightOrange

@Composable
fun QuestionCard(answer: Answer, onclick: () -> Unit) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    Surface(
        color = if (isSelected) LightOrange else Color(0xFFE7E7E8),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable { onclick()
                isSelected = !isSelected
            }
    ) {
        Column {
            AsyncImage(
                model = answer.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(142.dp)
                    .fillMaxWidth()
            )

            Text(
                text = answer.title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 7.dp, bottom = 3.dp, start=2.dp))
        }
    }
}
