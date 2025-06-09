/*
 * Copyright 2022 NAVER Webtoon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webtoonscorp.android.readmore.sample.compose.material3

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webtoonscorp.android.readmore.foundation.ReadMoreTextOverflow
import com.webtoonscorp.android.readmore.foundation.ToggleArea
import com.webtoonscorp.android.readmore.material3.ReadMoreText
import com.webtoonscorp.android.readmore.sample.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadMoreTextDemo() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar {
                    Text(data.visuals.message)
                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.compose_material3_title)) },
                scrollBehavior = scrollBehavior,
            )
        },
        content = {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                Item_DownToEarth()
                HorizontalDivider()
                Item_Hyperfocus()
                HorizontalDivider()
                Item_Reunion()
                HorizontalDivider()
                Item_TheWorldAfterTheFall()
                HorizontalDivider()
                Item_LoreOlympus()
                HorizontalDivider()
                Item_CustomText()
                HorizontalDivider()
                Item_RTL()
                HorizontalDivider()
                Item_Emoji()
                HorizontalDivider()
                Item_Hyperlink { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message = message)
                    }
                }
                HorizontalDivider()
            }
        },
    )
}

@Composable
private fun Item_DownToEarth() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_down_to_earth),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_down_to_earth),
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onExpandedChange = onExpandedChange,
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 2,
            readLessText = stringResource(id = R.string.read_less),
        )
    }
}

@Composable
private fun Item_Hyperfocus() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_hyperfocus),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_hyperfocus),
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 3,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreColor = MaterialTheme.colorScheme.primary,
            readMoreFontSize = 12.sp,
            readMoreFontWeight = FontWeight.Bold,
            readMoreTextDecoration = TextDecoration.Underline,
            readLessText = stringResource(id = R.string.read_less),
        )
    }
}

@Composable
private fun Item_Reunion() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_reunion),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_reunion),
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 3,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreColor = MaterialTheme.colorScheme.primary,
            readMoreFontSize = 12.sp,
            readMoreFontWeight = FontWeight.Bold,
            readMoreTextDecoration = TextDecoration.Underline,
            readLessText = stringResource(id = R.string.read_less),
        )
    }
}

@Composable
private fun Item_TheWorldAfterTheFall() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.clickable { onExpandedChange(!expanded) },
    ) {
        Text(
            text = stringResource(id = R.string.title_the_world_after_the_fall),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_the_world_after_the_fall),
            expanded = expanded,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 22.sp,
            readMoreMaxLines = 3,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Black,
            ),
            readMoreTextDecoration = TextDecoration.Underline,
            readLessText = stringResource(id = R.string.read_less),
        )
    }
}

@Composable
private fun Item_LoreOlympus() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_lore_olympus),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_lore_olympus),
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onExpandedChange = onExpandedChange,
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 3,
            readMoreFontSize = 14.sp,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreColor = MaterialTheme.colorScheme.secondary,
            readMoreOverflow = ReadMoreTextOverflow.Clip,
            readLessText = stringResource(id = R.string.read_less),
        )
    }
}

@Composable
private fun Item_CustomText() {
    val annotatedDescription = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.surface,
                background = MaterialTheme.colorScheme.onSurface,
            ),
        ) {
            append("abcdefghijklmnopqrstuvwxyz,")
        }
        withStyle(SpanStyle(fontSize = 12.sp)) {
            append("abcdefghijklmnopqrstuvwxyz,")
        }
        withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
            append("abcdefghijklmnopqrstuvwxyz,")
        }
        withStyle(SpanStyle(color = Color.Blue)) {
            append("abcdefghijklmnopqrstuvwxyz,")
        }
        append("abcdefghijklmnopqrstuvwxyz.")
    }
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_custom_text_compose),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        ReadMoreText(
            text = annotatedDescription,
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = tween(durationMillis = 100)),
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 2,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreColor = MaterialTheme.colorScheme.error,
            readMoreFontSize = 14.sp,
            readMoreFontWeight = FontWeight.Bold,
            readMoreFontStyle = FontStyle.Italic,
            readMoreTextDecoration = TextDecoration.Underline,
            readLessText = stringResource(id = R.string.read_less),
            toggleArea = ToggleArea.More,
        )
    }
}

@Composable
private fun Item_RTL() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column {
            Text(
                text = stringResource(id = R.string.title_rtl),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp, top = 16.dp),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            ReadMoreText(
                text = stringResource(id = R.string.description_rtl),
                expanded = expanded,
                modifier = Modifier.fillMaxWidth(),
                onExpandedChange = onExpandedChange,
                contentPadding = PaddingValues(
                    start = 18.dp,
                    top = 5.dp,
                    end = 18.dp,
                    bottom = 18.dp,
                ),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
                readMoreText = stringResource(id = R.string.read_more_rtl),
                readMoreMaxLines = 2,
                readMoreColor = MaterialTheme.colorScheme.primary,
                readMoreFontSize = 13.sp,
                readMoreFontWeight = FontWeight.Bold,
                readMoreTextDecoration = TextDecoration.Underline,
                readLessText = stringResource(id = R.string.read_less_rtl),
            )
        }
    }
}

@Composable
private fun Item_Emoji() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_emoji),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_emoji),
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onExpandedChange = onExpandedChange,
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 2,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreFontWeight = FontWeight.Bold,
            readMoreTextDecoration = TextDecoration.Underline,
            readLessText = stringResource(id = R.string.read_less),
        )
    }
}

@Composable
private fun Item_Hyperlink(showMessage: (String) -> Unit) {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_hyperlink),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        val context = LocalContext.current
        ReadMoreText(
            text = buildAnnotatedString {
                repeat(30) { index ->
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "TAG$index",
                            styles = TextLinkStyles(style = SpanStyle(color = Color.Blue))
                        ) {
                            showMessage("#TAG$index Clicked!")
                        },
                    ) {
                        append("#TAG$index")
                    }
                    append(' ')
                }
                append(' ')
                append("END")
            },
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 3,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreTextDecoration = TextDecoration.Underline,
            readLessText = stringResource(id = R.string.read_less),
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    CustomTheme {
        ReadMoreTextDemo()
    }
}
