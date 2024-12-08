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
package com.webtoonscorp.android.readmore.foundation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.ResolvedTextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * Basic element that displays text with read more.
 * Typically you will instead want to use [com.webtoonscorp.android.readmore.material.ReadMoreText],
 * which is a higher level Text element that contains semantics and consumes style information from
 * a theme.
 *
 * @param text The text to be displayed.
 * @param expanded whether this text is expanded or collapsed.
 * @param modifier [Modifier] to apply to this layout node.
 * @param onExpandedChange called when this text is clicked. If `null`, then this text will not be
 * interactable, unless something else handles its input events and updates its state.
 * @param contentPadding a padding around the text.
 * @param style Style configuration for the text such as color, font, line height etc.
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A
 * [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details. The callback can be used to add additional decoration or
 * functionality to the text. For example, to draw selection around the text.
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 * [readMoreOverflow] and TextAlign may have unexpected effects.
 * @param readMoreText The read more text to be displayed in the collapsed state.
 * @param readMoreMaxLines An optional maximum number of lines for the text to span, wrapping if
 * necessary. If the text exceeds the given number of lines, it will be truncated according to
 * [readMoreOverflow]. If it is not null, then it must be greater than zero.
 * @param readMoreOverflow How visual overflow should be handled in the collapsed state.
 * @param readMoreStyle Style configuration for the read more text such as color, font, line height
 * etc.
 * @param readLessText The read less text to be displayed in the expanded state.
 * @param readLessStyle Style configuration for the read less text such as color, font, line height
 * etc.
 * @param toggleArea A clickable area of text to toggle.
 */
@Composable
public fun BasicReadMoreText(
    text: String,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    style: TextStyle = TextStyle.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    softWrap: Boolean = true,
    readMoreText: String = "",
    readMoreMaxLines: Int = 2,
    readMoreOverflow: ReadMoreTextOverflow = ReadMoreTextOverflow.Ellipsis,
    readMoreStyle: SpanStyle = style.toSpanStyle(),
    readLessText: String = "",
    readLessStyle: SpanStyle = readMoreStyle,
    toggleArea: ToggleArea = ToggleArea.All,
) {
    CoreReadMoreText(
        text = AnnotatedString(text),
        expanded = expanded,
        modifier = modifier,
        onExpandedChange = onExpandedChange,
        contentPadding = contentPadding,
        style = style,
        onTextLayout = onTextLayout,
        softWrap = softWrap,
        readMoreText = readMoreText,
        readMoreMaxLines = readMoreMaxLines,
        readMoreOverflow = readMoreOverflow,
        readMoreStyle = readMoreStyle,
        readLessText = readLessText,
        readLessStyle = readLessStyle,
        toggleArea = toggleArea,
    )
}

/**
 * Basic element that displays text with read more.
 * Typically you will instead want to use [com.webtoonscorp.android.readmore.material.ReadMoreText],
 * which is a higher level Text element that contains semantics and consumes style information from
 * a theme.
 *
 * @param text The text to be displayed.
 * @param expanded whether this text is expanded or collapsed.
 * @param modifier [Modifier] to apply to this layout node.
 * @param onExpandedChange called when this text is clicked. If `null`, then this text will not be
 * interactable, unless something else handles its input events and updates its state.
 * @param contentPadding a padding around the text.
 * @param style Style configuration for the text such as color, font, line height etc.
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A
 * [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details. The callback can be used to add additional decoration or
 * functionality to the text. For example, to draw selection around the text.
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 * [readMoreOverflow] and TextAlign may have unexpected effects.
 * @param readMoreText The read more text to be displayed in the collapsed state.
 * @param readMoreMaxLines An optional maximum number of lines for the text to span, wrapping if
 * necessary. If the text exceeds the given number of lines, it will be truncated according to
 * [readMoreOverflow]. If it is not null, then it must be greater than zero.
 * @param readMoreOverflow How visual overflow should be handled in the collapsed state.
 * @param readMoreStyle Style configuration for the read more text such as color, font, line height
 * etc.
 * @param readLessText The read less text to be displayed in the expanded state.
 * @param readLessStyle Style configuration for the read less text such as color, font, line height
 * etc.
 * @param toggleArea A clickable area of text to toggle.
 */
@Composable
public fun BasicReadMoreText(
    text: AnnotatedString,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    style: TextStyle = TextStyle.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    softWrap: Boolean = true,
    readMoreText: String = "",
    readMoreMaxLines: Int = 2,
    readMoreOverflow: ReadMoreTextOverflow = ReadMoreTextOverflow.Ellipsis,
    readMoreStyle: SpanStyle = style.toSpanStyle(),
    readLessText: String = "",
    readLessStyle: SpanStyle = readMoreStyle,
    toggleArea: ToggleArea = ToggleArea.All,
) {
    CoreReadMoreText(
        text = text,
        expanded = expanded,
        modifier = modifier,
        onExpandedChange = onExpandedChange,
        contentPadding = contentPadding,
        style = style,
        onTextLayout = onTextLayout,
        softWrap = softWrap,
        readMoreText = readMoreText,
        readMoreMaxLines = readMoreMaxLines,
        readMoreOverflow = readMoreOverflow,
        readMoreStyle = readMoreStyle,
        readLessText = readLessText,
        readLessStyle = readLessStyle,
        toggleArea = toggleArea,
    )
}

// ////////////////////////////////////
// CoreReadMoreText
// ////////////////////////////////////

private const val ReadMoreTag = "read_more"
private const val ReadLessTag = "read_less"

@Composable
private fun CoreReadMoreText(
    text: AnnotatedString,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    style: TextStyle = TextStyle.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    softWrap: Boolean = true,
    readMoreText: String = "",
    readMoreMaxLines: Int = 2,
    readMoreOverflow: ReadMoreTextOverflow = ReadMoreTextOverflow.Ellipsis,
    readMoreStyle: SpanStyle = style.toSpanStyle(),
    readLessText: String = "",
    readLessStyle: SpanStyle = readMoreStyle,
    toggleArea: ToggleArea = ToggleArea.All,
) {
    require(readMoreMaxLines > 0) { "readMoreMaxLines should be greater than 0" }

    val overflowText: String = remember(readMoreOverflow) {
        buildString {
            when (readMoreOverflow) {
                ReadMoreTextOverflow.Clip -> {
                }
                ReadMoreTextOverflow.Ellipsis -> {
                    append(Typography.ellipsis)
                }
            }
            if (readMoreText.isNotEmpty()) {
                append(Typography.nbsp)
            }
        }
    }
    val readMoreTextWithStyle: AnnotatedString = remember(readMoreText, readMoreStyle) {
        buildAnnotatedString {
            if (readMoreText.isNotEmpty()) {
                withStyle(readMoreStyle) {
                    append(readMoreText.replace(' ', Typography.nbsp))
                }
            }
        }
    }
    val readLessTextWithStyle: AnnotatedString = remember(readLessText, readLessStyle) {
        buildAnnotatedString {
            if (readLessText.isNotEmpty()) {
                withStyle(readLessStyle) {
                    append(readLessText)
                }
            }
        }
    }

    val state = remember(text, readMoreMaxLines) {
        ReadMoreState(
            originalText = text,
            readMoreMaxLines = readMoreMaxLines,
        )
    }
    val currentText = buildAnnotatedString {
        if (expanded) {
            append(text)
            if (readLessTextWithStyle.isNotEmpty()) {
                append(' ')
                withLink(
                    LinkAnnotation.Clickable(tag = ReadLessTag) {
                        onExpandedChange?.invoke(false)
                    },
                ) {
                    append(readLessTextWithStyle)
                }
            }
        } else {
            val collapsedText = state.collapsedText
            if (collapsedText.isNotEmpty()) {
                append(collapsedText)
                append(overflowText)

                withLink(
                    LinkAnnotation.Clickable(tag = ReadMoreTag) {
                        onExpandedChange?.invoke(true)
                    },
                ) {
                    append(readMoreTextWithStyle)
                }
            } else {
                append(text)
            }
        }
    }
    val toggleableModifier = if (onExpandedChange != null && toggleArea == ToggleArea.All) {
        Modifier.clickable(
            enabled = state.isCollapsible,
            onClick = { onExpandedChange(!expanded) },
        )
    } else {
        Modifier
    }
    Box(
        modifier = modifier
            .then(toggleableModifier)
            .padding(contentPadding),
    ) {
        if (toggleArea == ToggleArea.More) {
            BasicText(
                text = currentText,
                modifier = Modifier,
                style = style,
                onTextLayout = {
                    state.onTextLayout(it)
                    onTextLayout(it)
                },
                overflow = TextOverflow.Ellipsis,
                softWrap = softWrap,
                maxLines = if (expanded) Int.MAX_VALUE else readMoreMaxLines,
            )
        } else {
            BasicText(
                text = currentText,
                modifier = Modifier,
                style = style,
                onTextLayout = {
                    state.onTextLayout(it)
                    onTextLayout(it)
                },
                overflow = TextOverflow.Ellipsis,
                softWrap = softWrap,
                maxLines = if (expanded) Int.MAX_VALUE else readMoreMaxLines,
            )
        }
        if (expanded.not()) {
            BasicText(
                text = overflowText,
                onTextLayout = { state.onOverflowTextLayout(it) },
                modifier = Modifier.notDraw(),
                style = style,
            )
            BasicText(
                text = readMoreTextWithStyle,
                onTextLayout = { state.onReadMoreTextLayout(it) },
                modifier = Modifier.notDraw(),
                style = style.merge(readMoreStyle),
            )
        }
    }
}

private fun Modifier.notDraw(): Modifier {
    return then(NotDrawModifier)
}

private object NotDrawModifier : DrawModifier {

    override fun ContentDrawScope.draw() {
        // not draws content.
    }
}

// ////////////////////////////////////
// ReadMoreState
// ////////////////////////////////////

private const val DebugLog = false
private const val Tag = "ReadMoreState"

@Stable
private class ReadMoreState(
    private val originalText: AnnotatedString,
    private val readMoreMaxLines: Int,
) {
    private var textLayout: TextLayoutResult? = null
    private var overflowTextLayout: TextLayoutResult? = null
    private var readMoreTextLayout: TextLayoutResult? = null

    private var _collapsedText: AnnotatedString by mutableStateOf(AnnotatedString(""))

    var collapsedText: AnnotatedString
        get() = _collapsedText
        internal set(value) {
            if (value != _collapsedText) {
                _collapsedText = value
                if (DebugLog) {
                    Log.d(Tag, "collapsedText changed: $_collapsedText")
                }
            }
        }

    val isCollapsible: Boolean
        get() = collapsedText.isNotEmpty()

    fun onTextLayout(result: TextLayoutResult) {
        val lastLineIndex = readMoreMaxLines - 1
        val previous = textLayout
        val old = previous != null &&
            previous.lineCount >= readMoreMaxLines &&
            previous.isLineEllipsized(lastLineIndex)
        val new = result.lineCount >= readMoreMaxLines &&
            result.isLineEllipsized(lastLineIndex)
        val changed = previous != result && old != new
        if (changed) {
            if (DebugLog) {
                Log.d(Tag, "onTextLayout:")
            }
            textLayout = result
            updateCollapsedText()
        }
    }

    fun onOverflowTextLayout(result: TextLayoutResult) {
        val changed = overflowTextLayout?.size?.width != result.size.width
        if (changed) {
            if (DebugLog) {
                Log.d(Tag, "onOverflowTextLayout:")
            }
            overflowTextLayout = result
            updateCollapsedText()
        }
    }

    fun onReadMoreTextLayout(result: TextLayoutResult) {
        val changed = readMoreTextLayout?.size?.width != result.size.width
        if (changed) {
            if (DebugLog) {
                Log.d(Tag, "onReadMoreTextLayout:")
            }
            readMoreTextLayout = result
            updateCollapsedText()
        }
    }

    private fun updateCollapsedText() {
        val lastLineIndex = readMoreMaxLines - 1
        val textLayout = textLayout
        val overflowTextLayout = overflowTextLayout
        val readMoreTextLayout = readMoreTextLayout
        if (textLayout != null &&
            overflowTextLayout != null &&
            readMoreTextLayout != null &&
            textLayout.lineCount >= readMoreMaxLines &&
            textLayout.isLineEllipsized(lastLineIndex)
        ) {
            val countUntilMaxLine = textLayout.getLineEnd(readMoreMaxLines - 1, visibleEnd = true)
            val countUntilMaxLineExceptNewline: Int =
                if (originalText.getOrNull(countUntilMaxLine) == '\n') {
                    // Workaround:
                    // If the last character of the sentence is a newline char('\n'),
                    // calculates excluding the last newline char('\n').
                    countUntilMaxLine - 1
                } else {
                    countUntilMaxLine
                }
            val readMoreWidth = overflowTextLayout.size.width + readMoreTextLayout.size.width

            val firstCharOffsetOfLastLine = textLayout.getLineStart(readMoreMaxLines - 1)
            val isRtl = textLayout.getParagraphDirection(firstCharOffsetOfLastLine) == ResolvedTextDirection.Rtl
            if (isRtl) {
                val minimumWidth = readMoreWidth
                var replacedEndIndex = countUntilMaxLineExceptNewline + 1
                var currentTextBounds: Rect
                do {
                    replacedEndIndex -= 1
                    currentTextBounds = textLayout.getCursorRect(replacedEndIndex)
                } while (currentTextBounds.right < minimumWidth)
                collapsedText = originalText.subSequence(startIndex = 0, endIndex = replacedEndIndex)
            } else {
                val maximumWidth = max(0, textLayout.layoutInput.constraints.maxWidth - readMoreWidth)
                var replacedEndIndex = countUntilMaxLineExceptNewline + 1
                var currentTextBounds: Rect
                do {
                    replacedEndIndex -= 1
                    currentTextBounds = textLayout.getCursorRect(replacedEndIndex)
                } while (currentTextBounds.left > maximumWidth)
                collapsedText = originalText.subSequence(startIndex = 0, endIndex = replacedEndIndex)
            }
            if (DebugLog) {
                Log.d(Tag, "updateCollapsedText: collapsedText=$collapsedText")
            }
        }
    }

    override fun toString(): String {
        return "ReadMoreState(" +
            "originalText=$originalText, " +
            "readMoreMaxLines=$readMoreMaxLines, " +
            "collapsedText=$collapsedText" +
            ")"
    }
}
