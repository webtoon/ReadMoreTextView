/*
 * Copyright 2023 NAVER Webtoon
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

public class ToggleArea private constructor(internal val value: Int) {

    override fun toString(): String {
        return when (this) {
            All -> "All"
            More -> "More"
            else -> "Invalid"
        }
    }

    public companion object {
        /**
         * All area of the text is clickable to toggle.
         */
        @Stable
        public val All: ToggleArea = ToggleArea(1)

        /**
         * 'More' and 'Less' area of the text is clickable to toggle.
         */
        @Stable
        public val More: ToggleArea = ToggleArea(2)
    }
}
