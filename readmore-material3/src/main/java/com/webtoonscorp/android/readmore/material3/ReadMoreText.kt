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
package com.webtoonscorp.android.readmore.material3

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.webtoonscorp.android.readmore.foundation.BasicReadMoreText
import com.webtoonscorp.android.readmore.foundation.ReadMoreTextOverflow
import com.webtoonscorp.android.readmore.foundation.ToggleArea

/**
 * High level element that displays text with read more.
 *
 * The default [style] uses the [LocalTextStyle] provided by the [MaterialTheme] / components. If
 * you are setting your own style, you may want to consider first retrieving [LocalTextStyle],
 * and using [TextStyle.copy] to keep any theme defined attributes, only modifying the specific
 * attributes you want to override.
 *
 * For ease of use, commonly used parameters from [TextStyle] are also present here. The order of
 * precedence is as follows:
 * - If a parameter is explicitly set here (i.e, it is _not_ `null` or [TextUnit.Unspecified]),
 * then this parameter will always be used.
 * - If a parameter is _not_ set, (`null` or [TextUnit.Unspecified]), then the corresponding value
 * from [style] will be used instead.
 *
 * Additionally, for [color], if [color] is not set, and [style] does not have a color, then
 * [LocalContentColor] will be used.
 *
 * @param text The text to be displayed.
 * @param expanded whether this text is expanded or collapsed.
 * @param modifier [Modifier] to apply to this layout node.
 * @param onExpandedChange called when this text is clicked. If `null`, then this text will not be
 * interactable, unless something else handles its input events and updates its state.
 * @param contentPadding a padding around the text.
 * @param color [Color] to apply to the text. If [Color.Unspecified], and [style] has no color set,
 * this will be [LocalContentColor].
 * @param fontSize The size of glyphs to use when painting the text. See [TextStyle.fontSize].
 * @param fontStyle The typeface variant to use when drawing the letters (e.g., italic).
 * See [TextStyle.fontStyle].
 * @param fontWeight The typeface thickness to use when painting the text (e.g., [FontWeight.Bold]).
 * @param fontFamily The font family to be used when rendering the text. See [TextStyle.fontFamily].
 * @param letterSpacing The amount of space to add between each letter.
 * See [TextStyle.letterSpacing].
 * @param textDecoration The decorations to paint on the text (e.g., an underline).
 * See [TextStyle.textDecoration].
 * @param textAlign The alignment of the text within the lines of the paragraph.
 * See [TextStyle.textAlign].
 * @param lineHeight Line height for the [Paragraph] in [TextUnit] unit, e.g. SP or EM.
 * See [TextStyle.lineHeight].
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 * [readMoreOverflow] and TextAlign may have unexpected effects.
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A
 * [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details. The callback can be used to add additional decoration or
 * functionality to the text. For example, to draw selection around the text.
 * @param style Style configuration for the text such as color, font, line height etc.
 * @param readMoreText The read more text to be displayed in the collapsed state.
 * @param readMoreColor [Color] to apply to the read more text. If [Color.Unspecified], and [style]
 * has no color set, this will be [LocalContentColor].
 * @param readMoreFontSize The size of glyphs to use when painting the read more text.
 * See [TextStyle.fontSize].
 * @param readMoreFontStyle The typeface variant to use when drawing the read more letters
 * (e.g., italic). See [TextStyle.fontStyle].
 * @param readMoreFontWeight The typeface thickness to use when painting the read more text
 * (e.g., [FontWeight.Bold]).
 * @param readMoreFontFamily The font family to be used when rendering the read more text.
 * See [TextStyle.fontFamily].
 * @param readMoreTextDecoration The decorations to paint on the read more text
 * (e.g., an underline). See [TextStyle.textDecoration].
 * @param readMoreMaxLines An optional maximum number of lines for the text to span, wrapping if
 * necessary. If the text exceeds the given number of lines, it will be truncated according to
 * [readMoreOverflow]. If it is not null, then it must be greater than zero.
 * @param readMoreOverflow How visual overflow should be handled in the collapsed state.
 * @param readMoreStyle Style configuration for the read more text such as color, font, line height
 * etc.
 * @param readLessText The read less text to be displayed in the expanded state.
 * @param readLessColor [Color] to apply to the read less text. If [Color.Unspecified], and [style]
 * has no color set, this will be [LocalContentColor].
 * @param readLessFontSize The size of glyphs to use when painting the read less text.
 * See [TextStyle.fontSize].
 * @param readLessFontStyle The typeface variant to use when drawing the read less letters
 * (e.g., italic). See [TextStyle.fontStyle].
 * @param readLessFontWeight The typeface thickness to use when painting the read less text
 * (e.g., [FontWeight.Bold]).
 * @param readLessFontFamily The font family to be used when rendering the read less text.
 * See [TextStyle.fontFamily].
 * @param readLessTextDecoration The decorations to paint on the read less text
 * (e.g., an underline). See [TextStyle.textDecoration].
 * @param readLessStyle Style configuration for the read less text such as color, font, line height
 * etc.
 * @param toggleArea A clickable area of text to toggle.
 */
@Composable
public fun ReadMoreText(
    text: String,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign = TextAlign.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    readMoreText: String = "",
    readMoreColor: Color = Color.Unspecified,
    readMoreFontSize: TextUnit = TextUnit.Unspecified,
    readMoreFontStyle: FontStyle? = null,
    readMoreFontWeight: FontWeight? = null,
    readMoreFontFamily: FontFamily? = null,
    readMoreTextDecoration: TextDecoration? = null,
    readMoreMaxLines: Int = 2,
    readMoreOverflow: ReadMoreTextOverflow = ReadMoreTextOverflow.Ellipsis,
    readMoreStyle: SpanStyle = style.toSpanStyle(),
    readLessText: String = "",
    readLessColor: Color = readMoreColor,
    readLessFontSize: TextUnit = readMoreFontSize,
    readLessFontStyle: FontStyle? = readMoreFontStyle,
    readLessFontWeight: FontWeight? = readMoreFontWeight,
    readLessFontFamily: FontFamily? = readMoreFontFamily,
    readLessTextDecoration: TextDecoration? = readMoreTextDecoration,
    readLessStyle: SpanStyle = readMoreStyle,
    toggleArea: ToggleArea = ToggleArea.All,
) {
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current
        }
    }
    // NOTE(text-perf-review): It might be worthwhile writing a bespoke merge implementation that
    // will avoid reallocating if all of the options here are the defaults
    val mergedStyle = style.merge(
        TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            lineHeight = lineHeight,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing
        )
    )
    val mergedReadMoreStyle = mergedStyle.toSpanStyle()
        .merge(readMoreStyle)
        .merge(
            SpanStyle(
                color = readMoreColor,
                fontSize = readMoreFontSize,
                fontWeight = readMoreFontWeight,
                fontFamily = readMoreFontFamily,
                textDecoration = readMoreTextDecoration,
                fontStyle = readMoreFontStyle
            )
        )
    val mergedReadLessStyle = mergedStyle.toSpanStyle()
        .merge(readLessStyle)
        .merge(
            SpanStyle(
                color = readLessColor,
                fontSize = readLessFontSize,
                fontWeight = readLessFontWeight,
                fontFamily = readLessFontFamily,
                textDecoration = readLessTextDecoration,
                fontStyle = readLessFontStyle
            )
        )
    BasicReadMoreText(
        text = text,
        expanded = expanded,
        modifier = modifier,
        onExpandedChange = onExpandedChange,
        contentPadding = contentPadding,
        style = mergedStyle,
        onTextLayout = onTextLayout,
        softWrap = softWrap,
        readMoreText = readMoreText,
        readMoreMaxLines = readMoreMaxLines,
        readMoreOverflow = readMoreOverflow,
        readMoreStyle = mergedReadMoreStyle,
        readLessText = readLessText,
        readLessStyle = mergedReadLessStyle,
        toggleArea = toggleArea,
    )
}

/**
 * High level element that displays text with read more.
 *
 * The default [style] uses the [LocalTextStyle] provided by the [MaterialTheme] / components. If
 * you are setting your own style, you may want to consider first retrieving [LocalTextStyle],
 * and using [TextStyle.copy] to keep any theme defined attributes, only modifying the specific
 * attributes you want to override.
 *
 * For ease of use, commonly used parameters from [TextStyle] are also present here. The order of
 * precedence is as follows:
 * - If a parameter is explicitly set here (i.e, it is _not_ `null` or [TextUnit.Unspecified]),
 * then this parameter will always be used.
 * - If a parameter is _not_ set, (`null` or [TextUnit.Unspecified]), then the corresponding value
 * from [style] will be used instead.
 *
 * Additionally, for [color], if [color] is not set, and [style] does not have a color, then
 * [LocalContentColor] will be used.
 *
 * @param text The text to be displayed.
 * @param expanded whether this text is expanded or collapsed.
 * @param modifier [Modifier] to apply to this layout node.
 * @param onExpandedChange called when this text is clicked. If `null`, then this text will not be
 * interactable, unless something else handles its input events and updates its state.
 * @param contentPadding a padding around the text.
 * @param color [Color] to apply to the text. If [Color.Unspecified], and [style] has no color set,
 * this will be [LocalContentColor].
 * @param fontSize The size of glyphs to use when painting the text. See [TextStyle.fontSize].
 * @param fontStyle The typeface variant to use when drawing the letters (e.g., italic).
 * See [TextStyle.fontStyle].
 * @param fontWeight The typeface thickness to use when painting the text (e.g., [FontWeight.Bold]).
 * @param fontFamily The font family to be used when rendering the text. See [TextStyle.fontFamily].
 * @param letterSpacing The amount of space to add between each letter.
 * See [TextStyle.letterSpacing].
 * @param textDecoration The decorations to paint on the text (e.g., an underline).
 * See [TextStyle.textDecoration].
 * @param textAlign The alignment of the text within the lines of the paragraph.
 * See [TextStyle.textAlign].
 * @param lineHeight Line height for the [Paragraph] in [TextUnit] unit, e.g. SP or EM.
 * See [TextStyle.lineHeight].
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 * [readMoreOverflow] and TextAlign may have unexpected effects.
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A
 * [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details. The callback can be used to add additional decoration or
 * functionality to the text. For example, to draw selection around the text.
 * @param style Style configuration for the text such as color, font, line height etc.
 * @param readMoreText The read more text to be displayed in the collapsed state.
 * @param readMoreColor [Color] to apply to the read more text. If [Color.Unspecified], and [style]
 * has no color set, this will be [LocalContentColor].
 * @param readMoreFontSize The size of glyphs to use when painting the read more text.
 * See [TextStyle.fontSize].
 * @param readMoreFontStyle The typeface variant to use when drawing the read more letters
 * (e.g., italic). See [TextStyle.fontStyle].
 * @param readMoreFontWeight The typeface thickness to use when painting the read more text
 * (e.g., [FontWeight.Bold]).
 * @param readMoreFontFamily The font family to be used when rendering the read more text.
 * See [TextStyle.fontFamily].
 * @param readMoreTextDecoration The decorations to paint on the read more text
 * (e.g., an underline). See [TextStyle.textDecoration].
 * @param readMoreMaxLines An optional maximum number of lines for the text to span, wrapping if
 * necessary. If the text exceeds the given number of lines, it will be truncated according to
 * [readMoreOverflow]. If it is not null, then it must be greater than zero.
 * @param readMoreOverflow How visual overflow should be handled in the collapsed state.
 * @param readMoreStyle Style configuration for the read more text such as color, font, line height
 * etc.
 * @param readLessText The read less text to be displayed in the expanded state.
 * @param readLessColor [Color] to apply to the read less text. If [Color.Unspecified], and [style]
 * has no color set, this will be [LocalContentColor].
 * @param readLessFontSize The size of glyphs to use when painting the read less text.
 * See [TextStyle.fontSize].
 * @param readLessFontStyle The typeface variant to use when drawing the read less letters
 * (e.g., italic). See [TextStyle.fontStyle].
 * @param readLessFontWeight The typeface thickness to use when painting the read less text
 * (e.g., [FontWeight.Bold]).
 * @param readLessFontFamily The font family to be used when rendering the read less text.
 * See [TextStyle.fontFamily].
 * @param readLessTextDecoration The decorations to paint on the read less text
 * (e.g., an underline). See [TextStyle.textDecoration].
 * @param readLessStyle Style configuration for the read less text such as color, font, line height
 * etc.
 * @param toggleArea A clickable area of text to toggle.
 */
@Composable
public fun ReadMoreText(
    text: AnnotatedString,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign = TextAlign.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    readMoreText: String = "",
    readMoreColor: Color = Color.Unspecified,
    readMoreFontSize: TextUnit = TextUnit.Unspecified,
    readMoreFontStyle: FontStyle? = null,
    readMoreFontWeight: FontWeight? = null,
    readMoreFontFamily: FontFamily? = null,
    readMoreTextDecoration: TextDecoration? = null,
    readMoreMaxLines: Int = 2,
    readMoreOverflow: ReadMoreTextOverflow = ReadMoreTextOverflow.Ellipsis,
    readMoreStyle: SpanStyle = style.toSpanStyle(),
    readLessText: String = "",
    readLessColor: Color = readMoreColor,
    readLessFontSize: TextUnit = readMoreFontSize,
    readLessFontStyle: FontStyle? = readMoreFontStyle,
    readLessFontWeight: FontWeight? = readMoreFontWeight,
    readLessFontFamily: FontFamily? = readMoreFontFamily,
    readLessTextDecoration: TextDecoration? = readMoreTextDecoration,
    readLessStyle: SpanStyle = readMoreStyle,
    toggleArea: ToggleArea = ToggleArea.All,
) {
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current
        }
    }
    // NOTE(text-perf-review): It might be worthwhile writing a bespoke merge implementation that
    // will avoid reallocating if all of the options here are the defaults
    val mergedStyle = style.merge(
        TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            lineHeight = lineHeight,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing
        )
    )
    val mergedReadMoreStyle = mergedStyle.toSpanStyle()
        .merge(readMoreStyle)
        .merge(
            SpanStyle(
                color = readMoreColor,
                fontSize = readMoreFontSize,
                fontWeight = readMoreFontWeight,
                fontFamily = readMoreFontFamily,
                textDecoration = readMoreTextDecoration,
                fontStyle = readMoreFontStyle
            )
        )
    val mergedReadLessStyle = mergedStyle.toSpanStyle()
        .merge(readLessStyle)
        .merge(
            SpanStyle(
                color = readLessColor,
                fontSize = readLessFontSize,
                fontWeight = readLessFontWeight,
                fontFamily = readLessFontFamily,
                textDecoration = readLessTextDecoration,
                fontStyle = readLessFontStyle
            )
        )
    BasicReadMoreText(
        text = text,
        expanded = expanded,
        modifier = modifier,
        onExpandedChange = onExpandedChange,
        contentPadding = contentPadding,
        style = mergedStyle,
        onTextLayout = onTextLayout,
        softWrap = softWrap,
        readMoreText = readMoreText,
        readMoreMaxLines = readMoreMaxLines,
        readMoreOverflow = readMoreOverflow,
        readMoreStyle = mergedReadMoreStyle,
        readLessText = readLessText,
        readLessStyle = mergedReadLessStyle,
        toggleArea = toggleArea,
    )
}
