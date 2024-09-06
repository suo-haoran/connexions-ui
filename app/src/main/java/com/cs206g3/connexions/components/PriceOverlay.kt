package com.cs206g3.connexions.components

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.transition.Transition
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cs206g3.connexions.R
import com.cs206g3.connexions.ui.theme.ConnexionsTheme
import com.cs206g3.connexions.ui.theme.Orange
@Composable
fun PriceOverlay(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .padding(horizontal = 25.dp,
                vertical = 5.dp)
    ) {
        Button(
            onClick = { },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffff5c0a)),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
            modifier = Modifier
                .requiredHeight(height = 41.dp)
                .weight(weight = 1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredWidth(width = 255.dp)
                    .requiredHeight(height = 41.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_fill),
                    contentDescription = "Chat_fill",
                    tint = Color.White)
                Text(
                    text = "Chat & Book",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.29.em,
                    style = TextStyle(
                        fontSize = 17.sp,
                        letterSpacing = (-0.41).sp),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Text(
                textAlign = TextAlign.End,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color(0xff373737),
                        fontSize = 11.sp)
                    ) {append("from")}
                    withStyle(style = SpanStyle(
                        color = Color(0xff373737),
                        fontSize = 11.sp)) {append(" ")}},
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
            Text(
                text = "SGD50",
                color = Color(0xff373737),
                textAlign = TextAlign.End,
                lineHeight = 1.em,
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.41).sp),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
            Text(
                text = "per adult",
                color = Color(0xff373737),
                textAlign = TextAlign.End,
                lineHeight = 1.58.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    letterSpacing = (-0.41).sp),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically))
        }
    }
}

@Preview(widthDp = 393, heightDp = 67)
@Composable
private fun PriceOverlayPreview() {
    PriceOverlay(Modifier)
}