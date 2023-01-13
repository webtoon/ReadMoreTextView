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
package com.webtoonscorp.android.readmore.sample.compose.foundation

import android.content.res.Configuration
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webtoonscorp.android.readmore.foundation.BasicReadMoreText
import com.webtoonscorp.android.readmore.foundation.ReadMoreTextOverflow
import com.webtoonscorp.android.readmore.sample.R

@Composable
fun BasicReadMoreTextDemo() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.compose_foundation_title))
            })
        },
        content = {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
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
                Item_Emoji()
                Divider()
            }
        }
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
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        BasicReadMoreText(
            text = stringResource(id = R.string.description_down_to_earth),
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
            readMoreMaxLines = 2
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
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        BasicReadMoreText(
            text = stringResource(id = R.string.description_hyperfocus),
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
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
        )
    }
}

@Composable
private fun ItemReunion() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_reunion),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        BasicReadMoreText(
            text = stringResource(id = R.string.description_reunion),
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
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
        )
    }
}

@Composable
private fun Item_TheWorldAfterTheFall() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.clickable { onExpandedChange(!expanded) }
    ) {
        Text(
            text = stringResource(id = R.string.title_the_world_after_the_fall),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        BasicReadMoreText(
            text = stringResource(id = R.string.description_the_world_after_the_fall),
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
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Black,
                textDecoration = TextDecoration.Underline
            ),
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
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        BasicReadMoreText(
            text = stringResource(id = R.string.description_lore_olympus),
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
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colors.secondary,
            ),
            readMoreOverflow = ReadMoreTextOverflow.Clip
        )
    }
}

@Composable
private fun Item_CustomText() {
    val annotatedDescription = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = MaterialTheme.colors.surface,
                background = MaterialTheme.colors.onSurface
            )
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
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        BasicReadMoreText(
            text = annotatedDescription,
            expanded = expanded,
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = tween(durationMillis = 100)),
            onExpandedChange = onExpandedChange,
            contentPadding = PaddingValues(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            style = TextStyle.Default.copy(
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
            ),
            readMoreMaxLines = 2,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.error,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline
            ),
        )
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
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        BasicReadMoreText(
            text = stringResource(id = R.string.description_emoji),
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
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            ),
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MaterialTheme {
        BasicReadMoreTextDemo()
    }
}
