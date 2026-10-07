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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
internal class BasicReadMoreTextLayoutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var textLayoutResult: TextLayoutResult? = null
    private var layoutCount = 0
    private val onTextLayout: (TextLayoutResult) -> Unit = {
        textLayoutResult = it
        layoutCount++
    }

    @Test
    fun collapsesText_whenParentQueriesIntrinsicHeight() {
        composeTestRule.setContent {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                Column(modifier = Modifier.width(292.dp).fillMaxHeight()) {
                    BasicReadMoreText(
                        text = LongText,
                        expanded = false,
                        onTextLayout = onTextLayout,
                        readMoreText = ReadMoreText,
                        readMoreMaxLines = 3,
                    )
                }
            }
        }

        assertCollapsed(maxLines = 3)
    }

    @Test
    fun keepsAvailableWidth_whenParentQueriesIntrinsicWidth() {
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            Box(modifier = Modifier.width(300.dp)) {
                Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                    BasicReadMoreText(
                        text = LongText,
                        expanded = false,
                        onTextLayout = onTextLayout,
                        readMoreText = ReadMoreText,
                        readMoreMaxLines = 1,
                    )
                }
            }
        }
        assertLayoutSettled()
        val maxWidth = assertCollapsed(maxLines = 1).layoutInput.constraints.maxWidth
        assertEquals(with(composeTestRule.density) { 300.dp.roundToPx() }, maxWidth)
    }

    @Test
    fun settlesLayout_whenParentQueriesIntrinsicWidthOfExpandedText() {
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            Box(modifier = Modifier.width(300.dp)) {
                Column(modifier = Modifier.width(IntrinsicSize.Min)) {
                    BasicReadMoreText(
                        text = "abcdefghij klmnop",
                        expanded = true,
                        onTextLayout = onTextLayout,
                        readMoreMaxLines = 1,
                        readLessText = "LESSLESSLESSLESSLESSLESS",
                    )
                }
            }
        }

        assertLayoutSettled()
    }

    @Test
    fun recalculatesCollapsedText_whenWidthChanges() {
        var width by mutableStateOf(300.dp)
        composeTestRule.setContent {
            BasicReadMoreText(
                text = LongText,
                expanded = false,
                modifier = Modifier.width(width),
                onTextLayout = onTextLayout,
                readMoreText = ReadMoreText,
                readMoreMaxLines = 3,
            )
        }

        val collapsedTexts = listOf(300.dp, 200.dp, 320.dp).map {
            width = it
            composeTestRule.waitForIdle()
            assertCollapsed(maxLines = 3).layoutInput.text.text
        }

        assertNotEquals(collapsedTexts[0], collapsedTexts[1])
        assertNotEquals(collapsedTexts[1], collapsedTexts[2])
    }

    @Test
    fun separatesOverflowAndReadMoreText_whenReadMoreTextChanges() {
        var readMoreText by mutableStateOf("")
        composeTestRule.setContent {
            BasicReadMoreText(
                text = LongText,
                expanded = false,
                modifier = Modifier.width(300.dp),
                onTextLayout = onTextLayout,
                readMoreText = readMoreText,
                readMoreMaxLines = 3,
            )
        }

        readMoreText = ReadMoreText
        composeTestRule.waitForIdle()

        val text = assertCollapsed(maxLines = 3).layoutInput.text.text
        assertTrue(text.endsWith("${Typography.ellipsis}${Typography.nbsp}$ReadMoreText"))
    }

    private fun assertLayoutSettled() {
        repeat(10) { composeTestRule.mainClock.advanceTimeByFrame() }
        val settledLayoutCount = layoutCount
        repeat(10) { composeTestRule.mainClock.advanceTimeByFrame() }
        assertEquals(settledLayoutCount, layoutCount)
    }

    private fun assertCollapsed(maxLines: Int): TextLayoutResult {
        val result = checkNotNull(textLayoutResult)
        assertTrue(result.layoutInput.text.text.endsWith(ReadMoreText))
        assertFalse(result.hasVisualOverflow)
        assertEquals(maxLines, result.lineCount)
        return result
    }

    private companion object {
        const val ReadMoreText = "more"
        const val LongText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    }
}
