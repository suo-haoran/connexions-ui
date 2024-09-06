package com.cs206g3.connexions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }

            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(placeholder: String) {
    var date by remember {
        mutableStateOf(placeholder)
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val containerColor = Color(0x1F767680)
    val iconColor = Color(0x993C3C43)
    val interactionSource = remember {
        MutableInteractionSource()
    }
    BasicTextField(
        value = date,
        onValueChange = { date = it },
        textStyle = TextStyle(
            fontSize = 15.sp,
            color = Color(0x993C3C43)
        ),
        interactionSource = interactionSource,
        modifier= Modifier
            .requiredSize(166.dp, 33.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(containerColor),
    ) {
        TextFieldDefaults.DecorationBox(
            value = date,
            innerTextField = it,
            enabled = false,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = "Date Range",
                    modifier = Modifier.requiredSize(21.dp),
                    tint = iconColor
                )
            }
        ) {
            Button(onClick = { showDatePicker = true }, colors = ButtonDefaults.buttonColors(Color.Transparent) ) {}
        }

    }
//    TextField(value = date,
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Outlined.DateRange, tint = iconColor,
//                contentDescription = "Date",
//                modifier = Modifier.requiredSize(21.dp)
//            )
//        },
//        textStyle = TextStyle(
//            fontSize = 15.sp,
//            lineHeight = 1.sp,
//        ),
//        placeholder = { Text(text = placeholder) },
//        modifier= Modifier
//            .clickable(onClick = { showDatePicker = true })
//            .requiredWidth(166.dp)
//            .clip(RoundedCornerShape(10.dp)),
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = containerColor,
//            unfocusedContainerColor = containerColor,
//            disabledContainerColor = containerColor,
//        ),
//        onValueChange = { },
//        enabled = false,
//    )

    if (showDatePicker) {
        MyDatePickerDialog(
            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false }
        )
    }
}
private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    return formatter.format(Date(millis))
}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {

    DatePickerDialog("From:")
    DatePickerDialog("To:")
//    MyDatePickerDialog(
//        onDateSelected = {  },
//        onDismiss = {  }
//    )
}