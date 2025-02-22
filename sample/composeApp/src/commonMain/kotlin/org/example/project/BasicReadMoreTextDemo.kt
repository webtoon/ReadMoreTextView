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
package org.example.project

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
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webtoonscorp.android.readmore.foundation.BasicReadMoreText
import com.webtoonscorp.android.readmore.foundation.ReadMoreTextOverflow
import com.webtoonscorp.android.readmore.foundation.ToggleArea
import org.jetbrains.compose.resources.stringResource
import readmoretextview.sample.composeapp.generated.resources.Res
import readmoretextview.sample.composeapp.generated.resources.compose_foundation_title
import readmoretextview.sample.composeapp.generated.resources.description_down_to_earth
import readmoretextview.sample.composeapp.generated.resources.description_emoji
import readmoretextview.sample.composeapp.generated.resources.description_hyperfocus
import readmoretextview.sample.composeapp.generated.resources.description_lore_olympus
import readmoretextview.sample.composeapp.generated.resources.description_reunion
import readmoretextview.sample.composeapp.generated.resources.description_rtl
import readmoretextview.sample.composeapp.generated.resources.description_the_world_after_the_fall
import readmoretextview.sample.composeapp.generated.resources.read_less
import readmoretextview.sample.composeapp.generated.resources.read_less_rtl
import readmoretextview.sample.composeapp.generated.resources.read_more
import readmoretextview.sample.composeapp.generated.resources.read_more_rtl
import readmoretextview.sample.composeapp.generated.resources.title_custom_text_compose
import readmoretextview.sample.composeapp.generated.resources.title_down_to_earth
import readmoretextview.sample.composeapp.generated.resources.title_emoji
import readmoretextview.sample.composeapp.generated.resources.title_hyperfocus
import readmoretextview.sample.composeapp.generated.resources.title_lore_olympus
import readmoretextview.sample.composeapp.generated.resources.title_reunion
import readmoretextview.sample.composeapp.generated.resources.title_rtl
import readmoretextview.sample.composeapp.generated.resources.title_the_world_after_the_fall

@Composable
fun BasicReadMoreTextDemo() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(Res.string.compose_foundation_title))
            })
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
                Divider()
                Item_Hyperfocus()
                Divider()
                ItemReunion()
                Divider()
                Item_TheWorldAfterTheFall()
                Divider()
                Item_LoreOlympus()
                Divider()
                Item_CustomText()
                Divider()
                Item_RTL()
                Divider()
                Item_Emoji()
                Divider()
            }
        },
    )
}

@Composable
private fun Item_DownToEarth() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(Res.string.title_down_to_earth),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicReadMoreText(
            text = stringResource(Res.string.description_down_to_earth),
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onExpandedChange = onExpandedChange,
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 2,
            readLessText = stringResource(Res.string.read_less),
        )
    }
}

@Composable
private fun Item_Hyperfocus() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(Res.string.title_hyperfocus),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicReadMoreText(
            text = stringResource(Res.string.description_hyperfocus),
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 3,
            readMoreText = stringResource(Res.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
            readLessText = stringResource(Res.string.read_less),
        )
    }
}

@Composable
private fun ItemReunion() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(Res.string.title_reunion),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicReadMoreText(
            text = stringResource(Res.string.description_reunion),
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 3,
            readMoreText = stringResource(Res.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
            readLessText = stringResource(Res.string.read_less),
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
            text = stringResource(Res.string.title_the_world_after_the_fall),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicReadMoreText(
            text = stringResource(Res.string.description_the_world_after_the_fall),
            expanded = expanded,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 3,
            readMoreText = stringResource(Res.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Black,
                textDecoration = TextDecoration.Underline,
            ),
            readLessText = stringResource(Res.string.read_less),
        )
    }
}

@Composable
private fun Item_LoreOlympus() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(Res.string.title_lore_olympus),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicReadMoreText(
            text = stringResource(Res.string.description_lore_olympus),
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onExpandedChange = onExpandedChange,
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 3,
            readMoreText = stringResource(Res.string.read_more),
            readMoreStyle = SpanStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colors.secondary,
            ),
            readMoreOverflow = ReadMoreTextOverflow.Clip,
            readLessText = stringResource(Res.string.read_less),
        )
    }
}

@Composable
private fun Item_CustomText() {
    val annotatedDescription = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = MaterialTheme.colors.surface,
                background = MaterialTheme.colors.onSurface,
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
            text = stringResource(Res.string.title_custom_text_compose),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicReadMoreText(
            text = annotatedDescription,
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = tween(durationMillis = 100)),
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 2,
            readMoreText = stringResource(Res.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.error,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
            ),
            readLessText = stringResource(Res.string.read_less),
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
                text = stringResource(Res.string.title_rtl),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp, top = 16.dp),
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            BasicReadMoreText(
                text = stringResource(Res.string.description_rtl),
                expanded = expanded,
                modifier = Modifier.fillMaxWidth(),
                onExpandedChange = onExpandedChange,
                contentPadding = PaddingValues(
                    start = 18.dp,
                    top = 5.dp,
                    end = 18.dp,
                    bottom = 18.dp,
                ),
                style = TextStyle.Default.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Normal,
                    lineHeight = 22.sp,
                    textDirection = TextDirection.Content,
                ),
                readMoreText = stringResource(Res.string.read_more_rtl),
                readMoreMaxLines = 2,
                readMoreStyle = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
                readLessText = stringResource(Res.string.read_less_rtl),
            )
        }
    }
}

@Composable
private fun Item_Emoji() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(Res.string.title_emoji),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        BasicReadMoreText(
            text = stringResource(Res.string.description_emoji),
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onExpandedChange = onExpandedChange,
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 2,
            readMoreText = stringResource(Res.string.read_more),
            readMoreStyle = SpanStyle(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
            readLessText = stringResource(Res.string.read_less),
        )
    }
}
