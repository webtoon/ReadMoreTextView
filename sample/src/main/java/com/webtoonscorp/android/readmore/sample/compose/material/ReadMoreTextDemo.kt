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
package com.webtoonscorp.android.readmore.sample.compose.material

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webtoonscorp.android.readmore.foundation.ReadMoreTextOverflow
import com.webtoonscorp.android.readmore.material.ReadMoreText
import com.webtoonscorp.android.readmore.sample.R

@Composable
fun ReadMoreTextDemo() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.compose_material_title))
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
                Item01()
                Divider()
                Item02()
                Divider()
                Item03()
                Divider()
                Item04()
                Divider()
                Item05()
                Divider()
                Item06()
                Divider()
            }
        }
    )
}

@Composable
private fun Item01() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_01),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_01),
            expanded = expanded,
            modifier = Modifier
                .clickable { onExpandedChange(!expanded) }
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 2
        )
    }
}

@Composable
private fun Item02() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_02),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_02),
            expanded = expanded,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp)
                .clickable { onExpandedChange(!expanded) },
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 3,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreColor = MaterialTheme.colors.primary,
            readMoreFontSize = 12.sp,
            readMoreFontWeight = FontWeight.Bold,
            readMoreTextDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun Item03() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.clickable { onExpandedChange(!expanded) }
    ) {
        Text(
            text = stringResource(id = R.string.title_03),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_03),
            expanded = expanded,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 22.sp,
            readMoreMaxLines = 3,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreStyle = SpanStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Black
            ),
            readMoreTextDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun Item04() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_04),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .clickable { onExpandedChange(!expanded) }
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp)
        ) {
            ReadMoreText(
                text = stringResource(id = R.string.description_04),
                expanded = expanded,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
                readMoreMaxLines = 3,
                readMoreFontSize = 14.sp,
                readMoreText = stringResource(id = R.string.read_more),
                readMoreColor = MaterialTheme.colors.secondary,
                readMoreOverflow = ReadMoreTextOverflow.Clip
            )
        }
    }
}

@Composable
private fun Item05() {
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
            text = stringResource(id = R.string.title_05_compose),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        ReadMoreText(
            text = annotatedDescription,
            expanded = expanded,
            modifier = Modifier
                .clickable { onExpandedChange(!expanded) }
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp)
                .animateContentSize(animationSpec = tween(durationMillis = 100)),
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 2,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreColor = MaterialTheme.colors.error,
            readMoreFontSize = 14.sp,
            readMoreFontWeight = FontWeight.Bold,
            readMoreFontStyle = FontStyle.Italic,
            readMoreTextDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun Item06() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.title_06),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        ReadMoreText(
            text = stringResource(id = R.string.description_06),
            expanded = expanded,
            modifier = Modifier
                .clickable { onExpandedChange(!expanded) }
                .fillMaxWidth()
                .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            lineHeight = 22.sp,
            readMoreMaxLines = 2,
            readMoreText = stringResource(id = R.string.read_more),
            readMoreFontWeight = FontWeight.Bold,
            readMoreTextDecoration = TextDecoration.Underline,
        )
    }
}

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    MaterialTheme {
        ReadMoreTextDemo()
    }
}
