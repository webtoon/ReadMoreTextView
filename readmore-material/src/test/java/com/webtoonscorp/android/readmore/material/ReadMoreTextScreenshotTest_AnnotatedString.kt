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
package com.webtoonscorp.android.readmore.material

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import java.io.File

internal data class AnnotatedStringScreenshotTestCase(
    val description: String,
    val expanded: Boolean,
    val text: AnnotatedString,
    val readMoreText: String = "",
    val readLessText: String = "",
) {
    override fun toString(): String = description
}

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
internal class ReadMoreTextScreenshotTest_AnnotatedString(
    private val testCase: AnnotatedStringScreenshotTestCase,
) {
    companion object Companion {

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun data(): Collection<Array<AnnotatedStringScreenshotTestCase>> {
            val shortText = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.White,
                        background = Color.Black,
                    ),
                ) {
                    append("abcdefghijklmnopqrstuvwxyz,")
                }
            }
            val longText = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.White,
                        background = Color.Black,
                    ),
                ) {
                    append("abcdefghijklmnopqrstuvwxyz,")
                }
                withStyle(SpanStyle(fontSize = 12.sp)) {
                    append("abcdefghijklmnopqrstuvwxyz,")
                }
                withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                    append("abcdefghijklmnopqrstuvwxyz,")
                }
                withStyle(SpanStyle(color = Color.Magenta)) {
                    append("abcdefghijklmnopqrstuvwxyz,")
                }
                append("abcdefghijklmnopqrstuvwxyz.")
            }

            return listOf(
                /* Default */
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_shortText_default",
                    expanded = false,
                    text = shortText,
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_shortText_default",
                    expanded = true,
                    text = shortText,
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_longText_default",
                    expanded = false,
                    text = longText,
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_longText_default",
                    expanded = true,
                    text = longText,
                ),

                /* ReadMoreText */
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_shortText_withReadMoreText",
                    expanded = false,
                    text = shortText,
                    readMoreText = "Read more",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_shortText_withReadMoreText",
                    expanded = true,
                    text = shortText,
                    readMoreText = "Read more",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_longText_withReadMoreText",
                    expanded = false,
                    text = longText,
                    readMoreText = "Read more",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_longText_withReadMoreText",
                    expanded = true,
                    text = longText,
                    readMoreText = "Read more",
                ),

                /* ReadLessText */
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_shortText_withReadLessText",
                    expanded = false,
                    text = shortText,
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_shortText_withReadLessText",
                    expanded = true,
                    text = shortText,
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_longText_withReadLessText",
                    expanded = false,
                    text = longText,
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_longText_withReadLessText",
                    expanded = true,
                    text = longText,
                    readLessText = "Read less",
                ),

                /* ReadMoreText and ReadLessText */
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_shortText_withReadMoreLessText",
                    expanded = false,
                    text = shortText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_shortText_withReadMoreLessText",
                    expanded = true,
                    text = shortText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "collapsed_longText_withReadMoreLessText",
                    expanded = false,
                    text = longText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    description = "expanded_longText_withReadMoreLessText",
                    expanded = true,
                    text = longText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
            ).map { arrayOf(it) }
        }
    }

    @get:Rule
    val roborazziRule = RoborazziRule(
        options = RoborazziRule.Options(
            outputFileProvider = { description, outputDirectory, fileExtension ->
                File(
                    outputDirectory,
                    "${description.testClass.simpleName}.${testCase.description}.$fileExtension"
                )
            }
        ),
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun capture_screenshot() {
        composeTestRule.setContent {
            ReadMoreText(
                expanded = testCase.expanded,
                text = testCase.text,
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                lineHeight = 22.sp,
                readMoreText = testCase.readMoreText,
                readMoreColor = Color.Blue,
                readMoreFontSize = 14.sp,
                readMoreFontWeight = FontWeight.Bold,
                readMoreTextDecoration = TextDecoration.Underline,
                readLessText = testCase.readLessText,
                readLessColor = Color.Red,
                readLessFontSize = 12.sp,
                readLessFontWeight = FontWeight.Bold,
                readLessFontStyle = FontStyle.Italic,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }
}
