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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import com.webtoonscorp.android.readmore.foundation.ReadMoreTextOverflow
import com.webtoonscorp.android.readmore.foundation.ToggleArea
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
internal class ReadMoreTextScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Sample text for testing
    private val longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris."

    @Test
    fun readMoreText_collapsed_defaultStyle() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_expanded_defaultStyle() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = true,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_withReadMoreText() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontSize = 12.sp,
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreTextDecoration = TextDecoration.Underline,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_expanded_withReadLessText() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = true,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readLessText = "Read less",
                        readLessColor = Color.Blue,
                        readLessFontSize = 12.sp,
                        readLessFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_maxLines3() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 3,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreTextDecoration = TextDecoration.Underline,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_clipOverflow() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 3,
                        readMoreText = "Read more",
                        readMoreFontSize = 14.sp,
                        readMoreColor = Color.Red,
                        readMoreOverflow = ReadMoreTextOverflow.Clip,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_customAnnotatedText() {
        val annotatedText = buildAnnotatedString {
            withStyle(SpanStyle(color = Color.Black, background = Color.Yellow)) {
                append("Highlighted text, ")
            }
            withStyle(SpanStyle(fontSize = 12.sp)) {
                append("small text, ")
            }
            withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                append("strikethrough text, ")
            }
            withStyle(SpanStyle(color = Color.Blue)) {
                append("blue text, ")
            }
            append("normal text.")
        }

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = annotatedText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Red,
                        readMoreFontSize = 14.sp,
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreFontStyle = FontStyle.Italic,
                        readMoreTextDecoration = TextDecoration.Underline,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_expanded_customAnnotatedText() {
        val annotatedText = buildAnnotatedString {
            withStyle(SpanStyle(color = Color.Black, background = Color.Yellow)) {
                append("Highlighted text, ")
            }
            withStyle(SpanStyle(fontSize = 12.sp)) {
                append("small text, ")
            }
            withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                append("strikethrough text, ")
            }
            withStyle(SpanStyle(color = Color.Blue)) {
                append("blue text, ")
            }
            append("normal text.")
        }

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = annotatedText,
                        expanded = true,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readLessText = "Read less",
                        readLessColor = Color.Red,
                        readLessFontSize = 14.sp,
                        readLessFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_customFontStyle() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 3,
                        readMoreText = "Read more",
                        readMoreStyle = SpanStyle(
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Black,
                        ),
                        readMoreTextDecoration = TextDecoration.Underline,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_toggleAreaMore() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Red,
                        readMoreFontSize = 14.sp,
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreFontStyle = FontStyle.Italic,
                        readMoreTextDecoration = TextDecoration.Underline,
                        toggleArea = ToggleArea.More,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_emojiText() {
        val emojiText = "🎨 Colorful emojis! 🌈✨ " +
            "Testing emoji rendering with read more functionality. " +
            "🚀 More emojis here: 💫⭐🌟✨🎉🎊🎁 " +
            "This is a longer text with many emojis."

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = emojiText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreTextDecoration = TextDecoration.Underline,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_toggleAreaAll() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontSize = 12.sp,
                        readMoreFontWeight = FontWeight.Bold,
                        toggleArea = ToggleArea.All,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_expanded_toggleAreaAll() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = true,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readLessText = "Read less",
                        readLessColor = Color.Blue,
                        readLessFontSize = 12.sp,
                        readLessFontWeight = FontWeight.Bold,
                        toggleArea = ToggleArea.All,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_shortText_noCollapse() {
        val shortText = "This is a short text."

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = shortText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 3,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_maxLines1() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 1,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_maxLines4() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 4,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_maxLines5() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 5,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_rtlText() {
        val rtlText = "هذا نص تجريبي باللغة العربية. " +
            "يستخدم هذا النص لاختبار وظيفة القراءة المزيد مع النصوص من اليمين إلى اليسار. " +
            "النص العربي يتدفق من اليمين إلى اليسار ويجب أن يتم عرضه بشكل صحيح."

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = rtlText,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            textDirection = TextDirection.Rtl,
                        ),
                        readMoreMaxLines = 2,
                        readMoreText = "اقرأ المزيد",
                        readMoreColor = Color.Blue,
                        readMoreFontSize = 13.sp,
                        readMoreFontWeight = FontWeight.Bold,
                        readMoreTextDecoration = TextDecoration.Underline,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_expanded_rtlText() {
        val rtlText = "هذا نص تجريبي باللغة العربية. " +
            "يستخدم هذا النص لاختبار وظيفة القراءة المزيد مع النصوص من اليمين إلى اليسار. " +
            "النص العربي يتدفق من اليمين إلى اليسار ويجب أن يتم عرضه بشكل صحيح."

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = rtlText,
                        expanded = true,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            textDirection = TextDirection.Rtl,
                        ),
                        readMoreMaxLines = 2,
                        readMoreText = "اقرأ المزيد",
                        readLessText = "اقرأ أقل",
                        readLessColor = Color.Blue,
                        readLessFontSize = 13.sp,
                        readLessFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_noPadding() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(0.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_asymmetricPadding() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(
                            start = 8.dp,
                            top = 4.dp,
                            end = 24.dp,
                            bottom = 12.dp,
                        ),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_largePadding() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = longText,
                        expanded = false,
                        contentPadding = PaddingValues(32.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_longUnbreakableWord() {
        val textWithLongWord = "This text contains a very long word: " +
            "Supercalifragilisticexpialidocious" +
            "andanevenlongerwordwithoutanyspacestotestwordwrappingbehavior " +
            "followed by normal text that should wrap properly."

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = textWithLongWord,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun readMoreText_collapsed_urlText() {
        val textWithUrls = "Check out this website: " +
            "https://www.example.com/very/long/path/that/might/cause/wrapping/issues " +
            "and another one https://github.com/repository/name for more information."

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ReadMoreText(
                        text = textWithUrls,
                        expanded = false,
                        contentPadding = PaddingValues(16.dp),
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        readMoreMaxLines = 2,
                        readMoreText = "Read more",
                        readMoreColor = Color.Blue,
                        readMoreFontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }
}
