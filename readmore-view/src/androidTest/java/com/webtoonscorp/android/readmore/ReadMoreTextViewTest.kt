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

import android.content.res.Resources
import android.text.TextUtils
import android.view.InflateException
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.webtoonscorp.android.readmore.test.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertFailsWith

@RunWith(AndroidJUnit4::class)
@LargeTest
internal class ReadMoreTextViewTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(ReadMoreTextViewActivity::class.java)

    private lateinit var container: ViewGroup

    private lateinit var activity: ReadMoreTextViewActivity
    private lateinit var resources: Resources

    @Before
    fun setUp() {
        activity = activityTestRule.activity
        container = activity.findViewById(R.id.container)
        resources = activity.resources
    }

    @Test
    @UiThreadTest
    fun testSetMaxLines_throwsException() {
        val readMoreTextView: ReadMoreTextView =
            container.findViewById(R.id.readmore_textview_simple)

        assertFailsWith<IllegalStateException> {
            readMoreTextView.maxLines = 1
        }
        assertFailsWith<InflateException> {
            inflate(R.layout.readmore_textview_max_lines)
        }
    }

    @Test
    @UiThreadTest
    fun testSetLines_throwsException() {
        val readMoreTextView: ReadMoreTextView =
            container.findViewById(R.id.readmore_textview_simple)

        assertFailsWith<IllegalStateException> {
            readMoreTextView.setLines(1)
        }
        assertFailsWith<InflateException> {
            inflate(R.layout.readmore_textview_lines)
        }
    }

    @Test
    @UiThreadTest
    fun testSetSingleLine_throwsException() {
        val readMoreTextView: ReadMoreTextView =
            container.findViewById(R.id.readmore_textview_simple)

        assertFailsWith<IllegalStateException> {
            readMoreTextView.setSingleLine()
        }
        assertFailsWith<InflateException> {
            inflate(R.layout.readmore_textview_single_line)
        }
    }

    @Test
    @UiThreadTest
    fun testSetEllipsize_throwsException() {
        val readMoreTextView: ReadMoreTextView =
            container.findViewById(R.id.readmore_textview_simple)

        assertFailsWith<IllegalStateException> {
            readMoreTextView.ellipsize = TextUtils.TruncateAt.END
        }
        assertFailsWith<InflateException> {
            inflate(R.layout.readmore_textview_ellipsize)
        }
    }

    private fun inflate(
        @LayoutRes resource: Int,
        root: ViewGroup? = null,
        attachToRoot: Boolean = false
    ) {
        LayoutInflater.from(activity).inflate(resource, root, attachToRoot)
    }
}
