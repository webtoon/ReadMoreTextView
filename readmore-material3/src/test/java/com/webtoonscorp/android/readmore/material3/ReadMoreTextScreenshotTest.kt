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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
internal class ReadMoreTextScreenshotTest(
    private val testCase: ScreenshotTestCase,
) {
    companion object {

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun data(): List<ScreenshotTestCase> {
            return listOf(
                StringScreenshotTestCase(
                    name = "String_short",
                    text = "Lorem ipsum dolor sit amet.",
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    name = "String_long",
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    name = "RTL",
                    text = "ليال، فتاة تسلك درباً موحشاً بحثاً عن حلٍ لجنونها. فكيف ستنتهي مغامرتها عندما لا تستطيع التفريق بين الوهم و الحقيقة و بين العدو و الصديق. هذه الرحلة ستضعها في مواجهة أسوأ مخاوفها، فماذا ستكون تلك الكوابيس؟ من سيقابلها في الطريق أم من يسكن عقلها الفارغ؟",
                    readMoreText = "اقرأ المزيد",
                    readLessText = "اقرأ أقل",
                    isRtl = true,
                ),
                StringScreenshotTestCase(
                    name = "emoji",
                    text = "😀😃😄😁😆😅😂🤣🥲☺️.😊😇🙂🙃😉😌😍🥰👪👨‍👩‍👦.👨‍👩‍👧👨‍👩‍👧‍👦👨‍👩‍👦‍👦👨‍👩‍👧‍👧👨‍👨‍👦👨‍👨‍👧👨‍👨‍👧‍👦👨‍👨‍👦‍👦👨‍👨‍👧‍👧👩‍👩‍👦.👩‍👩‍👧👩‍👩‍👧‍👦👩‍👩‍👦‍👦👩‍👩‍👧‍👧👨‍👦👨‍👦‍👦👨‍👧👨‍👧‍👦👨‍👧‍👧👩‍👦.👩‍👦‍👦👩‍👧👩‍👧‍👦👩‍👧‍👧😘😗😙😚😋😛.😝😜🤪🤨🧐🤓😎🥸🤩🥳.😏😒😞😔😟😕🙁☹️😣😖.😫😩🥺😢😭😤😠😡🤬🤯😳🥵🥶😱😨😰😥😓🤗🤔🤭🤫🤥😶😐😑😬🙄😯😦😧😮😲🥱😴🤤😪😵🤐🥴🤢🤮🤧😷🤒🤕🤑🤠😈👿👹👺🤡💩👻💀☠️👽👾🤖🎃😺😸😹😻😼😽🙀😿😾",
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    name = "AnnotatedString_short",
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = Color.White,
                                background = Color.Black,
                            ),
                        ) {
                            append("abcdefghijklmnopqrstuvwxyz,")
                        }
                    },
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    name = "AnnotatedString_long",
                    text = buildAnnotatedString {
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
                    },
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                AnnotatedStringScreenshotTestCase(
                    name = "InlineTextContent",
                    text = buildAnnotatedString {
                        appendInlineContent("start")
                        append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                        appendInlineContent("middle")
                        append("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                        appendInlineContent("end")
                    },
                    inlineContent = mapOf(
                        "start" to InlineTextContent(
                            Placeholder(
                                width = 55.sp,
                                height = 15.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                            ),
                        ) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = Color.Red,
                                contentColor = Color.White,
                                modifier = Modifier
                                    .padding(end = 5.dp)
                                    .fillMaxSize(),
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    Text(
                                        text = "START",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 14.sp,
                                    )
                                }
                            }
                        },
                        "middle" to InlineTextContent(
                            Placeholder(
                                width = 70.sp,
                                height = 15.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                            ),
                        ) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = Color.Blue,
                                contentColor = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .fillMaxSize(),
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    Text(
                                        text = "MIDDLE",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 14.sp,
                                    )
                                }
                            }
                        },
                        "end" to InlineTextContent(
                            Placeholder(
                                width = 35.sp,
                                height = 15.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                            ),
                        ) {
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = Color.Green,
                                contentColor = Color.White,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .fillMaxSize(),
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    Text(
                                        text = "END",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 14.sp,
                                    )
                                }
                            }
                        },
                    ),
                    maxLines = 4,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
            )
        }
    }

    @get:Rule
    val roborazziRule = RoborazziRule(
        options = RoborazziRule.Options(
            outputFileProvider = { description, outputDirectory, fileExtension ->
                // Remove parameters from method name
                // e.g. "method_name[parameter]" -> "method_name"
                val methodName = description.methodName.replace("""\[.*]$""".toRegex(), "")
                File(
                    outputDirectory,
                    "${description.testClass.simpleName}.${testCase.name}.$methodName.$fileExtension",
                )
            },
        ),
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun default_none() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = false,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun expanded_none() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = true,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = false,
                readMoreText = testCase.readMoreText,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_expanded() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = true,
                readMoreText = testCase.readMoreText,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readLessText_collapsed() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = false,
                readLessText = testCase.readLessText,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readLessText_expanded() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = true,
                readLessText = testCase.readLessText,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun all_collapsed() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = false,
                readMoreText = testCase.readMoreText,
                readLessText = testCase.readLessText,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun all_expanded() {
        composeTestRule.setContent {
            Screenshot(
                testCase = testCase,
                expanded = true,
                readMoreText = testCase.readMoreText,
                readLessText = testCase.readLessText,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Composable
    private fun Screenshot(
        testCase: ScreenshotTestCase,
        expanded: Boolean,
        readMoreText: String = "",
        readLessText: String = "",
    ) {
        val layoutDirection = if (testCase.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
        CompositionLocalProvider(
            LocalLayoutDirection provides layoutDirection,
        ) {
            when (testCase) {
                is StringScreenshotTestCase -> {
                    ReadMoreText(
                        text = testCase.text,
                        expanded = expanded,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal,
                        lineHeight = 22.sp,
                        readMoreText = readMoreText,
                        readMoreColor = Color.Blue,
                        readMoreFontSize = 14.sp,
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreTextDecoration = TextDecoration.Underline,
                        readLessText = readLessText,
                        readLessColor = Color.Red,
                        readLessFontSize = 12.sp,
                        readLessFontWeight = FontWeight.Bold,
                        readLessFontStyle = FontStyle.Italic,
                    )
                }

                is AnnotatedStringScreenshotTestCase -> {
                    ReadMoreText(
                        text = testCase.text,
                        inlineContent = testCase.inlineContent,
                        expanded = expanded,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal,
                        lineHeight = 22.sp,
                        readMoreMaxLines = testCase.maxLines,
                        readMoreText = readMoreText,
                        readMoreColor = Color.Blue,
                        readMoreFontSize = 14.sp,
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreTextDecoration = TextDecoration.Underline,
                        readLessText = readLessText,
                        readLessColor = Color.Red,
                        readLessFontSize = 12.sp,
                        readLessFontWeight = FontWeight.Bold,
                        readLessFontStyle = FontStyle.Italic,
                    )
                }
            }
        }
    }
}

@Stable
internal sealed interface ScreenshotTestCase {
    val name: String
    val readMoreText: String
    val readLessText: String
    val isRtl: Boolean
}

internal data class StringScreenshotTestCase(
    override val name: String,
    val text: String,
    override val readMoreText: String,
    override val readLessText: String,
    override val isRtl: Boolean = false,
) : ScreenshotTestCase {
    override fun toString(): String = name
}

internal data class AnnotatedStringScreenshotTestCase(
    override val name: String,
    val text: AnnotatedString,
    val inlineContent: Map<String, InlineTextContent> = mapOf(),
    val maxLines: Int = 2,
    override val readMoreText: String,
    override val readLessText: String,
    override val isRtl: Boolean = false,
) : ScreenshotTestCase {
    override fun toString(): String = name
}
