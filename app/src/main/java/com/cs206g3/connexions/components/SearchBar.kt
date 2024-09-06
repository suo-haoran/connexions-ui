package com.cs206g3.connexions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cs206g3.connexions.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onChange: (it: String) -> Unit) {
    var text by remember { mutableStateOf("Current Location - Bras Basah") }
    Box(
        modifier = Modifier.requiredWidth(343.dp)
    ) {
        val containerColor = Color(0x1F767680)
        val iconColor = Color(0x993C3C43)
        val interactionSource = remember {
            MutableInteractionSource()
        }

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                fontSize = 15.sp,
            ),

            interactionSource = interactionSource,
            modifier = Modifier
                .requiredHeight(36.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(containerColor),
        ) {
            TextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = it,
                enabled = true ,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(start = 45.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        tint = iconColor,
                        contentDescription = "Search",
                        modifier = Modifier.requiredSize(21.dp)
                    )
                }
            }
        }

        Button(
            onClick = { onChange(text) },
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RoundedCornerShape(40),
            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 2.dp),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
                .requiredHeight(24.dp)
        ) {
            Text("Search", fontSize = 13.sp, lineHeight = 20.sp, letterSpacing = (-0.41).sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar {}
}