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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
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
internal class BasicReadMoreTextHyperlinkTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var textLayoutResult: TextLayoutResult? = null
    private val expandedChanges = mutableListOf<Boolean>()
    private var linkClicks = 0
    private var padding = Offset.Zero

    @Test
    fun togglesOnReadMore_whenTruncatedLinkSpansTwoLines() {
        setContent(link = TwoLineLink, readMoreMaxLines = 2)

        assertTap(readMorePosition(), toggles = true, opensLink = false)
        assertTap(introPosition(), toggles = true, opensLink = false)
        assertTap(linkPosition(), toggles = false, opensLink = true)
    }

    @Test
    fun togglesOnReadMore_whenTruncatedLinkSpansThreeLines() {
        setContent(link = ThreeLineLink, readMoreMaxLines = 3)

        assertTap(readMorePosition(), toggles = true, opensLink = false)
        assertTap(introPosition(), toggles = true, opensLink = false)
        assertTap(linkPosition(), toggles = false, opensLink = true)
    }

    @Test
    fun togglesOnReadMore_whenShortLinkEndsRightBeforeOverflow() {
        setContent(
            intro = "Intro text that is long enough to fill up most of the first line of text here and ",
            link = "go",
            tail = " and more tail text after the short link that is cut by read more and continues beyond.",
            readMoreMaxLines = 2,
        )

        assertTap(readMorePosition(), toggles = true, opensLink = false)
        assertTap(linkPosition(), toggles = false, opensLink = true)
    }

    @Test
    fun togglesOnContentPadding_whenTextHasLink() {
        setContent(link = TwoLineLink, readMoreMaxLines = 2, contentPadding = 16.dp)

        assertTap(paddingPosition(), toggles = true, opensLink = false)
        assertTap(readMorePosition(), toggles = true, opensLink = false)
        assertTap(linkPosition(), toggles = false, opensLink = true)
    }

    private fun setContent(
        intro: String = "Intro ",
        link: String,
        tail: String = " and some tail text after the link.",
        readMoreMaxLines: Int,
        contentPadding: Dp = 0.dp,
    ) {
        val text = buildAnnotatedString {
            append(intro)
            withLink(LinkAnnotation.Clickable(tag = LinkTag) { linkClicks++ }) {
                append(link)
            }
            append(tail)
        }
        composeTestRule.setContent {
            Box(modifier = Modifier.width(300.dp)) {
                BasicReadMoreText(
                    text = text,
                    expanded = false,
                    modifier = Modifier.testTag(TestTag),
                    onExpandedChange = { expandedChanges += it },
                    contentPadding = PaddingValues(contentPadding),
                    style = TextStyle(fontSize = 14.sp),
                    onTextLayout = { textLayoutResult = it },
                    readMoreText = "Read more",
                    readMoreMaxLines = readMoreMaxLines,
                    toggleArea = ToggleArea.All,
                )
            }
        }
        composeTestRule.waitForIdle()
        padding = with(composeTestRule.density) { Offset(contentPadding.toPx(), contentPadding.toPx()) }

        val layout = layout()
        assertTrue(layout.layoutInput.text.text.endsWith(ReadMoreLaidOut))
        assertEquals(readMoreMaxLines, layout.lineCount)
    }

    private fun assertTap(position: Offset, toggles: Boolean, opensLink: Boolean) {
        expandedChanges.clear()
        linkClicks = 0
        composeTestRule.onNodeWithTag(TestTag).performTouchInput { click(position) }
        composeTestRule.waitForIdle()

        assertEquals("toggle at $position", if (toggles) listOf(true) else emptyList(), expandedChanges)
        assertEquals("link at $position", if (opensLink) 1 else 0, linkClicks)
    }

    private fun layout(): TextLayoutResult = checkNotNull(textLayoutResult)

    private fun readMorePosition(): Offset {
        val layout = layout()
        val index = layout.layoutInput.text.text.lastIndexOf(ReadMoreLaidOut) + "Read${Typography.nbsp}m".length
        return layout.getBoundingBox(index).center + padding
    }

    private fun introPosition(): Offset = layout().getBoundingBox(1).center + padding

    private fun linkPosition(): Offset {
        val layout = layout()
        val link = layout.layoutInput.text.getLinkAnnotations(0, layout.layoutInput.text.length)
            .first { (it.item as? LinkAnnotation.Clickable)?.tag == LinkTag }
        return layout.getBoundingBox((link.start + link.end) / 2).center + padding
    }

    private fun paddingPosition(): Offset {
        val layout = layout()
        return Offset(layout.size.width / 2f, layout.size.height + padding.y / 2f) + padding
    }

    private companion object {
        const val TestTag = "read_more_text"
        const val LinkTag = "link"
        val ReadMoreLaidOut = "Read${Typography.nbsp}more"
        const val TwoLineLink = "this is a very long hyperlink text that keeps going and going and going beyond the second line for sure"
        const val ThreeLineLink = "$TwoLineLink and the third line too and even more text"
    }
}
