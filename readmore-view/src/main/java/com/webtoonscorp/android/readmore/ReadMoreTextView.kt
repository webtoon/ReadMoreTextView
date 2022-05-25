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
package com.webtoonscorp.android.readmore

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.Typeface
import android.text.Layout
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.TextAppearanceSpan
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import androidx.annotation.StyleableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.use
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.webtoonscorp.android.readmore.internal.StaticLayoutCompat
import kotlin.text.Typography.ellipsis
import kotlin.text.Typography.nbsp

public class ReadMoreTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.readMoreTextViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var readMoreMaxLines: Int = 2
    private var readMoreOverflow: Overflow = Overflow.Ellipsis
    private var readMoreText: String? = null
    private var readMoreTextSize: Int = -1
    private var readMoreTextColor: ColorStateList? = null
    private var readMoreTextStyle: Int = Typeface.NORMAL
    private var readMoreFontFamily: String? = null
    private var readMoreTextUnderline: Boolean = false
    private var readMoreToggleEnabled: Boolean = true

    private var bufferType: BufferType? = null
    private var expanded: Boolean = false
    private var originalText: CharSequence? = null
    private var collapseText: CharSequence? = null

    private var listener: OnStateChangeListener? = null

    public fun interface OnStateChangeListener {
        public fun onStateChanged(expanded: Boolean)
    }

    init {
        context.obtainStyledAttributes(
            attrs, R.styleable.ReadMoreTextView, defStyleAttr, 0
        ).use { ta ->
            readMoreMaxLines = ta.getInt(
                R.styleable.ReadMoreTextView_readMoreMaxLines,
                readMoreMaxLines
            )
            val readMoreOverflowValue = ta.getInt(
                R.styleable.ReadMoreTextView_readMoreOverflow,
                readMoreOverflow.value
            )
            readMoreOverflow = Overflow.values().first { it.value == readMoreOverflowValue }
            readMoreText = ta.getString(R.styleable.ReadMoreTextView_readMoreText)
                ?.replace(' ', nbsp)

            readAttributesFromReadMoreTextAppearance(ta)

            readMoreTextSize = ta.getDimensionPixelSize(
                R.styleable.ReadMoreTextView_readMoreTextSize,
                readMoreTextSize
            )
            readMoreTextColor = ta.getColorStateList(
                R.styleable.ReadMoreTextView_readMoreTextColor
            ) ?: readMoreTextColor
            readMoreTextStyle = ta.getInt(
                R.styleable.ReadMoreTextView_readMoreTextStyle,
                readMoreTextStyle
            )
            readMoreFontFamily =
                ta.getString(R.styleable.ReadMoreTextView_readMoreFontFamily)
                    ?: ta.getFontFamilyFromTypeface(R.styleable.ReadMoreTextView_readMoreTypeface)
                    ?: readMoreFontFamily
            readMoreTextUnderline = ta.getBoolean(
                R.styleable.ReadMoreTextView_readMoreTextUnderline,
                readMoreTextUnderline
            )
            readMoreToggleEnabled = ta.getBoolean(
                R.styleable.ReadMoreTextView_readMoreToggleEnabled,
                readMoreToggleEnabled
            )
        }
        if (readMoreToggleEnabled) {
            if (hasOnClickListeners()) {
                throw IllegalStateException("The app:readMoreToggleEnabled attribute must be set to false to use custom OnClickListener")
            }
            super.setOnClickListener { toggle() }
        }

        if (originalText != null) {
            invalidateText()
        }
    }

    override fun setLines(lines: Int) {
        throw IllegalStateException(
            "The android:singleLine attribute and android:lines attribute" +
                " and setLines(int lines) function are not supported." +
                " If you want to change maximum lines in the collapsed state," +
                " please use the app:readMoreMaxLines attribute."
        )
    }

    override fun setMaxLines(maxLines: Int) {
        throw IllegalStateException(
            "The android:maxLines attribute and setMaxLines(int maxLines) function are not" +
                " supported. If you want to change maximum lines in the collapsed state," +
                " please use the app:readMoreMaxLines attribute."
        )
    }

    override fun setEllipsize(where: TextUtils.TruncateAt?) {
        throw IllegalStateException(
            "The android:ellipsize attribute and setEllipsize(TextUtils.TruncateAt where) function" +
                " are not supported. If you want to change ellipsize in the collapsed state," +
                " please use the app:readMoreOverflow attribute."
        )
    }

    @SuppressLint("CustomViewStyleable", "PrivateResource")
    private fun readAttributesFromReadMoreTextAppearance(appearance: TypedArray) {
        val readMoreTextAppearance = appearance.getResourceId(
            R.styleable.ReadMoreTextView_readMoreTextAppearance,
            ResourcesCompat.ID_NULL
        )
        if (readMoreTextAppearance != ResourcesCompat.ID_NULL) {
            context.obtainStyledAttributes(
                readMoreTextAppearance,
                androidx.appcompat.R.styleable.TextAppearance
            ).use { ta ->
                readMoreTextSize = ta.getDimensionPixelSize(
                    androidx.appcompat.R.styleable.TextAppearance_android_textSize,
                    readMoreTextSize
                )
                readMoreTextColor = ta.getColorStateList(
                    androidx.appcompat.R.styleable.TextAppearance_android_textColor
                ) ?: readMoreTextColor
                readMoreTextStyle = ta.getInt(
                    androidx.appcompat.R.styleable.TextAppearance_android_textStyle,
                    readMoreTextStyle
                )
                readMoreFontFamily =
                    ta.getString(androidx.appcompat.R.styleable.TextAppearance_android_fontFamily)
                        ?: ta.getFontFamilyFromTypeface(androidx.appcompat.R.styleable.TextAppearance_android_typeface)
            }
        }
    }

    private fun TypedArray.getFontFamilyFromTypeface(@StyleableRes index: Int): String? {
        return when (getInt(index, 0)) {
            1 -> "sans"
            2 -> "serif"
            3 -> "monospace"
            else -> null
        }
    }

    public fun toggle() {
        setExpanded(!expanded)
    }

    public fun isExpanded(): Boolean {
        return this.expanded
    }

    public fun setExpanded(expanded: Boolean) {
        if (this.expanded != expanded) {
            this.expanded = expanded

            invalidateText()
            listener?.onStateChanged(expanded)
        }
    }

    public fun setOnStateChangeListener(listener: OnStateChangeListener) {
        this.listener = listener
    }

    override fun setOnClickListener(l: OnClickListener?) {
        if (readMoreToggleEnabled) {
            throw IllegalStateException("The app:readMoreToggleEnabled attribute must be set to false to use custom OnClickListener")
        }
        super.setOnClickListener(l)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w != oldw) {
            originalText?.let { originalText ->
                updateText(originalText, w)
            }
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        this.originalText = text
        this.bufferType = type
        updateText(text ?: "", width)
    }

    private fun updateText(text: CharSequence, width: Int) {
        val maximumTextWidth = width - (paddingLeft + paddingRight)
        val readMoreMaxLines = readMoreMaxLines
        if (maximumTextWidth > 0 && readMoreMaxLines > 0) {
            val layout = StaticLayoutCompat.Builder(text, paint, maximumTextWidth)
                .setLineSpacing(lineSpacingExtra, lineSpacingMultiplier)
                .setIncludePad(includeFontPadding)
                .build()
            if (layout.lineCount <= readMoreMaxLines) {
                this.collapseText = text
            } else {
                this.collapseText = buildSpannedString {
                    val countUntilMaxLine = layout.getLineVisibleEnd(readMoreMaxLines - 1)
                    if (text.length <= countUntilMaxLine) {
                        append(text)
                    } else {
                        val overflowText = buildOverflowText()
                        val overflowTextWidth = StaticLayoutCompat
                            .Builder(overflowText, paint, maximumTextWidth)
                            .build()
                            .getLineWidth(0).toInt()

                        val textAppearanceSpan = buildTextAppearanceSpan()
                        val underlineSpan = if (readMoreTextUnderline) UnderlineSpan() else null
                        val spans = listOfNotNull(
                            textAppearanceSpan,
                            underlineSpan
                        )
                        val readMoreTextWithStyle = buildReadMoreText(spans = spans.toTypedArray())
                        val readMorePaint = TextPaint().apply {
                            set(paint)
                            textAppearanceSpan.updateMeasureState(this)
                        }
                        val readMoreTextWidth = StaticLayoutCompat
                            .Builder(readMoreTextWithStyle, readMorePaint, maximumTextWidth)
                            .build()
                            .getLineWidth(0).toInt()
                        val readMoreWidth = overflowTextWidth + readMoreTextWidth

                        val replaceCount = text
                            .substringOf(layout, line = readMoreMaxLines)
                            .calculateReplaceCountToBeSingleLineWith(maximumTextWidth - readMoreWidth)
                        append(text.subSequence(0, countUntilMaxLine - replaceCount).trimEnd())
                        append(overflowText)
                        append(readMoreTextWithStyle)
                    }
                }
            }
        } else {
            this.collapseText = text
        }
        invalidateText()
    }

    private fun buildTextAppearanceSpan(
        textSize: Int = readMoreTextSize.coerceAtMost(getTextSize().toInt()),
        textColors: ColorStateList? = readMoreTextColor,
        textStyle: Int = readMoreTextStyle,
        fontFamily: String? = readMoreFontFamily
    ): TextAppearanceSpan {
        return TextAppearanceSpan(fontFamily, textStyle, textSize, textColors, null)
    }

    private fun buildOverflowText(
        text: String? = readMoreText,
        overflow: Overflow = readMoreOverflow,
    ): String {
        return buildString {
            when (overflow) {
                Overflow.Clip -> {
                }
                Overflow.Ellipsis -> {
                    append(ellipsis)
                }
            }
            if (text.isNullOrEmpty().not()) {
                append(nbsp)
            }
        }
    }

    private fun buildReadMoreText(
        text: String? = readMoreText,
        vararg spans: Any
    ): CharSequence {
        return buildSpannedString {
            if (text.isNullOrEmpty().not()) {
                inSpans(spans = spans) {
                    append(text)
                }
            }
        }
    }

    private fun CharSequence.substringOf(layout: Layout, line: Int): CharSequence {
        val lastLineStartIndex = layout.getLineStart(line - 1)
        val lastLineEndIndex = layout.getLineEnd(line - 1)
        return subSequence(lastLineStartIndex, lastLineEndIndex)
    }

    private fun CharSequence.calculateReplaceCountToBeSingleLineWith(
        maximumTextWidth: Int
    ): Int {
        val currentTextBounds = Rect()
        var replacedCount = -1
        do {
            replacedCount++
            val replacedText = substring(0, this.length - replacedCount)
            paint.getTextBounds(replacedText, 0, replacedText.length, currentTextBounds)
        } while (replacedCount < this.length && currentTextBounds.width() >= maximumTextWidth)

        val lastVisibleChar: Char? = this.getOrNull(this.length - replacedCount - 1)
        val firstOverflowChar: Char? = this.getOrNull(this.length - replacedCount)
        if (lastVisibleChar?.isSurrogate() == true && firstOverflowChar?.isHighSurrogate() == false) {
            val subText = substring(0, this.length - replacedCount)
            if (subText.isNotEmpty()) {
                return length - subText.indexOfLast { it.isHighSurrogate() }
            }
        }
        return replacedCount
    }

    private fun invalidateText() {
        if (expanded) {
            super.setText(originalText, bufferType)
            super.setMaxLines(NO_LIMIT_LINES)
        } else {
            super.setText(collapseText, bufferType)
            super.setMaxLines(readMoreMaxLines)
        }
    }

    private enum class Overflow(val value: Int) {
        Clip(OVERFLOW_CLIP),
        Ellipsis(OVERFLOW_ELLIPSIS),
    }

    private companion object {
        private const val NO_LIMIT_LINES = Integer.MAX_VALUE

        const val OVERFLOW_CLIP: Int = 1
        const val OVERFLOW_ELLIPSIS: Int = 2
    }
}
