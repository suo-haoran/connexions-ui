package com.cs206g3.connexions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cs206g3.connexions.R
import com.cs206g3.connexions.ui.theme.LightOrange
import com.cs206g3.connexions.ui.theme.Orange
import kotlinx.coroutines.launch

@Composable
fun SectionFiltersStrip(
    drawerState: DrawerState,
    selectedTags: SnapshotStateList<String>,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 343.dp)
            .padding(top = 2.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(weight = 1f)
        ) {
            if (selectedTags.isEmpty()) {
                Text(
                    text = "No filters selected.",
                    color = Color(0xff808085),
                    textAlign = TextAlign.Center,
                    lineHeight = 2.em,
                    style = TextStyle(
                        fontSize = 11.sp,
                        letterSpacing = (-0.41).sp
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            } else {
                LazyRow {
                    items(selectedTags) { tag ->
                        DeletableChip(tag = tag, onDelete = {selectedTags.remove(tag)})
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .requiredHeight(height = 25.dp)
                .clickable {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
        ) {
            Divider(
                Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(top = 2.dp, bottom = 2.dp)
            )
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.outline_filter_alt_24),
                    contentDescription = "Filter_alt",
                    modifier = Modifier
                        .requiredSize(size = 19.dp)
                )
                Text(
                    text = "Filters",
                    color = Color(0xff808085),
                    textAlign = TextAlign.Center,
                    lineHeight = 2.2.em,
                    style = TextStyle(
                        fontSize = 10.sp,
                        letterSpacing = (-0.41).sp
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(top = 2.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletableChip(tag: String, onDelete: () -> Unit) {
    var enabled by remember {
        mutableStateOf(true)
    }
    if (!enabled) {
        return
    }

    InputChip(
        selected = false,
        onClick = {
            enabled = !enabled
            onDelete()
        },
        label = { Text(text = tag) },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        modifier = Modifier.padding(horizontal = 5.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchFilterDrawer(homePage: @Composable (DrawerState, selectedTags: SnapshotStateList<String>) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var searchText by remember {
        mutableStateOf("")
    }
    val tags = listOf(
        "Animals",
        "Aesthetics",
        "Beaches",
        "Books",
        "Cafe/Restaurants",
        "Cultural",
        "Daily Life",
        "Festivals",
        "Games",
        "Historical",
        "Informative",
        "Jogging",
        "Nature",
        "Night Life",
        "Relaxation",
        "School Life",
        "Shopping",
        "Sports",
        "Volunteering"
    )
    val selectedTags = remember {
        mutableStateListOf<String>()
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ModalDrawerSheet {
                        TextField(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "Search"
                                )
                            },
                            value = searchText,
                            onValueChange = { searchText = it },
                            placeholder = { Text(text = "Search Tags") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Divider()
                        FlowRow(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            for (tag in tags) {
                                Tag(text = tag, onSelect = { selected ->
                                    if (selected) {
                                        selectedTags.add(tag)
                                    } else {
                                        selectedTags.remove(tag)
                                    }
                                })
                            }
                        }
                        Button(
                            onClick = {
                                scope.launch {
                                    drawerState.apply { close() }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(Orange),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Text(
                                text = "Apply",
                            )
                        }
                    }
                }
            },
            gesturesEnabled = false
        ) {
            // Screen content
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                homePage(drawerState, selectedTags)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tag(text: String, onSelect: (Boolean) -> Unit) {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        onClick = {
            selected = !selected
            onSelect(selected)
        },
        label = {
            Text(text, fontSize = 12.sp)
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = LightOrange
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Plus icon",
                modifier = Modifier.size(FilterChipDefaults.IconSize)
            )
        },
        modifier = Modifier.padding(horizontal = 5.dp)
    )
}


@Preview(widthDp = 343, heightDp = 27)
@Composable
private fun SectionFiltersPreview() {
    // SectionFiltersStrip(Modifier)
}