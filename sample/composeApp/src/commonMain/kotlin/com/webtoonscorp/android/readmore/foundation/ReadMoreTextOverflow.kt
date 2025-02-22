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

import androidx.compose.runtime.Stable
import kotlin.jvm.JvmInline

@JvmInline
public value class ReadMoreTextOverflow private constructor(internal val value: Int) {

    override fun toString(): String {
        return when (this) {
            Clip -> "Clip"
            Ellipsis -> "Ellipsis"
            else -> "Invalid"
        }
    }

    public companion object {
        /**
         * Clip the overflowing text to fix its container.
         */
        @Stable
        public val Clip: ReadMoreTextOverflow = ReadMoreTextOverflow(1)

        /**
         * Use an ellipsis to indicate that the text has overflowed.
         */
        @Stable
        public val Ellipsis: ReadMoreTextOverflow = ReadMoreTextOverflow(2)
    }
}
