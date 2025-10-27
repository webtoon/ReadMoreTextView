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

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

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
 * @param inlineContent A map store composables that replaces certain ranges of the text. It's used
 * to insert composables into text layout. Check [InlineTextContent] for more information.
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
    inlineContent: Map<String, InlineTextContent> = mapOf(),
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
        inlineContent = inlineContent,
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

@SuppressLint("UnusedBoxWithConstraintsScope")
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
    inlineContent: Map<String, InlineTextContent> = mapOf(),
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

    val textMeasurer = rememberTextMeasurer()
    val state = remember { ReadMoreState() }

    val currentText = buildAnnotatedString {
        if (expanded) {
            append(text)
            if (state.isCollapsible && readLessTextWithStyle.isNotEmpty()) {
                append(' ')
                if (toggleArea == ToggleArea.More) {
                    withLink(
                        LinkAnnotation.Clickable(tag = ReadLessTag) {
                            onExpandedChange?.invoke(false)
                        },
                    ) {
                        append(readLessTextWithStyle)
                    }
                } else {
                    append(readLessTextWithStyle)
                }
            }
        } else {
            val collapsedText = state.collapsedText
            if (collapsedText.isNotEmpty()) {
                append(collapsedText)
                append(overflowText)

                if (toggleArea == ToggleArea.More) {
                    withLink(
                        LinkAnnotation.Clickable(tag = ReadMoreTag) {
                            onExpandedChange?.invoke(true)
                        },
                    ) {
                        append(readMoreTextWithStyle)
                    }
                } else {
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
    BoxWithConstraints(
        modifier = modifier
            .then(toggleableModifier)
            .padding(contentPadding),
    ) {
        BasicText(
            text = currentText,
            modifier = Modifier,
            style = style,
            onTextLayout = onTextLayout,
            overflow = TextOverflow.Ellipsis,
            softWrap = softWrap,
            maxLines = if (expanded) Int.MAX_VALUE else readMoreMaxLines,
            inlineContent = inlineContent,
        )

        val constraints = Constraints(maxWidth = constraints.maxWidth)
        LaunchedEffect(
            textMeasurer,
            constraints,
            overflowText,
            readMoreTextWithStyle,
            style,
            readMoreStyle,
            text,
            readMoreMaxLines,
            softWrap,
            inlineContent,
        ) {
            state.applyCollapsedText(
                textMeasurer = textMeasurer,
                constraints = constraints,
                overflowText = overflowText,
                readMoreTextWithStyle = readMoreTextWithStyle,
                style = style,
                readMoreStyle = readMoreStyle,
                text = text,
                readMoreMaxLines = readMoreMaxLines,
                softWrap = softWrap,
                inlineContent = inlineContent,
            )
        }
    }
}

// ////////////////////////////////////
// ReadMoreState
// ////////////////////////////////////

private const val DebugLog = false
private const val Tag = "ReadMoreState"

@Stable
private class ReadMoreState {
    private var _collapsedText: AnnotatedString by mutableStateOf(AnnotatedString(""))

    var collapsedText: AnnotatedString
        get() = _collapsedText
        private set(value) {
            if (value != _collapsedText) {
                _collapsedText = value
                if (DebugLog) {
                    Log.d(Tag, "collapsedText changed: $_collapsedText")
                }
            }
        }

    val isCollapsible: Boolean
        get() = collapsedText.isNotEmpty()

    fun applyCollapsedText(
        textMeasurer: TextMeasurer,
        constraints: Constraints,
        overflowText: String,
        readMoreTextWithStyle: AnnotatedString,
        style: TextStyle,
        readMoreStyle: SpanStyle,
        text: AnnotatedString,
        readMoreMaxLines: Int,
        softWrap: Boolean,
        inlineContent: Map<String, InlineTextContent>,
    ) {
        val overflowTextWidth = if (overflowText.isNotEmpty()) {
            textMeasurer.measure(
                text = overflowText,
                style = style,
            ).size.width
        } else {
            0
        }
        val readMoreTextWidth = if (readMoreTextWithStyle.isNotEmpty()) {
            textMeasurer.measure(
                text = readMoreTextWithStyle,
                style = style.merge(readMoreStyle),
            ).size.width
        } else {
            0
        }
        val textLayout = textMeasurer.measure(
            text = text,
            style = style,
            maxLines = readMoreMaxLines,
            overflow = TextOverflow.Clip,
            softWrap = softWrap,
            constraints = constraints,
            placeholders = extractPlaceholders(text, inlineContent),
        )

        val clipTextCount = textLayout.getLineEnd(lineIndex = textLayout.lineCount - 1)
        val isLineClipped = text.count() > clipTextCount
        if (isLineClipped) {
            val countUntilMaxLine =
                textLayout.getLineEnd(readMoreMaxLines - 1, visibleEnd = true)

            val decorationWidth = overflowTextWidth + readMoreTextWidth
            val replaceCount = text
                .substringOf(textLayout, line = readMoreMaxLines)
                .calculateReplaceCountToBeSingleLineWith(
                    maximumTextWidth = constraints.maxWidth - decorationWidth,
                    measureTextWidth = { subText ->
                        textMeasurer.measure(
                            text = subText,
                            style = style,
                            softWrap = softWrap,
                            placeholders = extractPlaceholders(subText, inlineContent),
                        ).size.width
                    },
                )
            collapsedText = text.subSequence(0, countUntilMaxLine - replaceCount)
        } else {
            collapsedText = AnnotatedString("")
        }
        if (DebugLog) {
            Log.d(Tag, "applyCollapsedText: collapsedText=$collapsedText")
        }
    }

    private fun AnnotatedString.substringOf(layout: TextLayoutResult, line: Int): AnnotatedString {
        val lastLineStartIndex = layout.getLineStart(line - 1)
        val lastLineEndIndex = layout.getLineEnd(line - 1, visibleEnd = true)
        return subSequence(lastLineStartIndex, lastLineEndIndex)
    }

    private inline fun AnnotatedString.calculateReplaceCountToBeSingleLineWith(
        maximumTextWidth: Int,
        measureTextWidth: (subText: AnnotatedString) -> Int,
    ): Int {
        var replacedTextWidth: Int
        var replacedCount = -1
        do {
            replacedCount++
            replacedTextWidth = measureTextWidth(
                subSequence(0, this.length - replacedCount),
            )
        } while (replacedCount < this.length && replacedTextWidth >= maximumTextWidth)

        val lastVisibleChar: Char? = this.getOrNull(this.length - replacedCount - 1)
        val firstOverflowChar: Char? = this.getOrNull(this.length - replacedCount)
        if (lastVisibleChar?.isSurrogate() == true && firstOverflowChar?.isHighSurrogate() == false) {
            val subText = subSequence(0, this.length - replacedCount)
            if (subText.isNotEmpty()) {
                return length - subText.indexOfLast { it.isHighSurrogate() }
            }
        }
        return replacedCount
    }

    /**
     * Converts AnnotatedString and inlineContent map to placeholders for use with TextMeasurer.
     *
     * @param text The annotated string containing inline content annotations
     * @param inlineContent Map of inline content IDs to their corresponding InlineTextContent
     * @return List of Range<Placeholder> objects representing the inline content positions
     */
    private fun extractPlaceholders(
        text: AnnotatedString,
        inlineContent: Map<String, InlineTextContent>,
    ): List<AnnotatedString.Range<Placeholder>> {
        if (inlineContent.isEmpty()) {
            return emptyList()
        }

        // Get all string annotations with the "androidx.compose.foundation.text.inlineContent" tag
        val inlineContentAnnotations = text.getStringAnnotations(
            tag = "androidx.compose.foundation.text.inlineContent",
            start = 0,
            end = text.length,
        )

        // Map each annotation to a Range<Placeholder> if it exists in the inlineContent map
        return inlineContentAnnotations.mapNotNull { annotation ->
            inlineContent[annotation.item]?.let { content ->
                AnnotatedString.Range(
                    item = content.placeholder,
                    start = annotation.start,
                    end = annotation.end,
                )
            }
        }
    }

    override fun toString(): String {
        return "ReadMoreState(" +
            "collapsedText=$collapsedText" +
            ")"
    }
}
