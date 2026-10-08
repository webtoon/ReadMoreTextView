/*
 * Copyright 2026 NAVER Webtoon
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
package com.webtoonscorp.android.readmore.foundation

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
internal class BasicReadMoreTextRelayoutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var layoutCount = 0
    private val onTextLayout: (TextLayoutResult) -> Unit = { layoutCount++ }

    @Test
    fun keepsTextLayout_whenOnlyColorChanges_withToggleAreaMore() {
        var color by mutableStateOf(Color.Black)
        composeTestRule.setContent {
            BasicReadMoreText(
                text = LongText,
                expanded = false,
                modifier = Modifier.width(300.dp),
                onExpandedChange = {},
                style = TextStyle(color = color, fontSize = 14.sp),
                onTextLayout = onTextLayout,
                readMoreText = "more",
                readMoreMaxLines = 2,
                readMoreStyle = SpanStyle(color = Color.Blue),
                toggleArea = ToggleArea.More,
            )
        }
        composeTestRule.waitForIdle()
        val settledLayoutCount = layoutCount
        assertTrue(settledLayoutCount > 0)

        color = Color.Red
        composeTestRule.waitForIdle()

        assertEquals(settledLayoutCount, layoutCount)
    }

    private companion object {
        const val LongText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    }
}
