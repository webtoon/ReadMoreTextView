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
package com.webtoonscorp.android.readmore.internal

import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import androidx.annotation.FloatRange
import androidx.annotation.IntRange

internal class StaticLayoutCompat {

    class Builder(
        private val text: CharSequence,
        private val start: Int,
        private val end: Int,
        private val paint: TextPaint,
        private val width: Int
    ) {
        constructor(text: CharSequence, paint: TextPaint, width: Int) :
            this(text, 0, text.length, paint, width)

        private var alignment: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL
        private var spacingMult = 1f
        private var spacingAdd = 0f
        private var includePad = true
        private var ellipsizedWidth = width
        private var ellipsize: TextUtils.TruncateAt? = null
        private var maxLines = Integer.MAX_VALUE

        fun setAlignment(alignment: Layout.Alignment): Builder {
            this.alignment = alignment
            return this
        }

        fun setLineSpacing(
            spacingAdd: Float,
            @FloatRange(from = 0.0) spacingMult: Float
        ): Builder {
            this.spacingAdd = spacingAdd
            this.spacingMult = spacingMult
            return this
        }

        fun setIncludePad(includePad: Boolean): Builder {
            this.includePad = includePad
            return this
        }

        fun setEllipsize(ellipsize: TextUtils.TruncateAt?): Builder {
            this.ellipsize = ellipsize
            return this
        }

        fun setEllipsizedWidth(@IntRange(from = 0) ellipsizedWidth: Int): Builder {
            this.ellipsizedWidth = ellipsizedWidth
            return this
        }

        fun setMaxLines(@IntRange(from = 0) maxLines: Int): Builder {
            this.maxLines = maxLines
            return this
        }

        @Suppress("DEPRECATION")
        fun build(): StaticLayout {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StaticLayout.Builder
                    .obtain(text, start, end, paint, width)
                    .setAlignment(alignment)
                    .setLineSpacing(spacingAdd, spacingMult)
                    .setIncludePad(includePad)
                    .setEllipsize(ellipsize)
                    .setEllipsizedWidth(ellipsizedWidth)
                    .setMaxLines(maxLines)
                    .build()
            } else {
                val layout = StaticLayout(
                    text, start, end, paint, width,
                    alignment,
                    spacingMult, spacingAdd,
                    includePad,
                    ellipsize, ellipsizedWidth
                )
                return if (layout.lineCount <= maxLines) {
                    layout
                } else {
                    if (ellipsize == null) {
                        val maxLineEndOffset = layout.getLineVisibleEnd(maxLines)
                        val ellipsizedText = text.subSequence(start, maxLineEndOffset)
                        StaticLayout(
                            ellipsizedText, 0, ellipsizedText.length, paint, width,
                            alignment,
                            spacingMult, spacingAdd,
                            includePad,
                            ellipsize, ellipsizedWidth
                        )
                    } else {
                        val maxLineStartOffset = layout.getLineStart(maxLines - 1)
                        val ellipsizedText = buildString {
                            append(text.subSequence(start, maxLineStartOffset))
                            append(
                                TextUtils.ellipsize(
                                    text.subSequence(maxLineStartOffset, text.length),
                                    paint,
                                    width.toFloat(),
                                    ellipsize
                                )
                            )
                        }
                        StaticLayout(
                            ellipsizedText, 0, ellipsizedText.length, paint, width,
                            alignment,
                            spacingMult, spacingAdd,
                            includePad,
                            ellipsize, ellipsizedWidth
                        )
                    }
                }
            }
        }
    }
}
