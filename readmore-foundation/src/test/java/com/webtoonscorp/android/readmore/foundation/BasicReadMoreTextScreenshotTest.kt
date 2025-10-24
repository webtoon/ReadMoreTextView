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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
internal class BasicReadMoreTextScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Sample text for testing
    private val longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris."

    @Test
    fun basicReadMoreText_collapsed_defaultStyle() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_expanded_defaultStyle() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = true,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_withReadMoreText() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_expanded_withReadLessText() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = true,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readLessText = "Read less",
                readLessStyle = SpanStyle(
                    color = Color.Blue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_maxLines3() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 3,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_clipOverflow() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 3,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    fontSize = 14.sp,
                    color = Color.Red,
                ),
                readMoreOverflow = ReadMoreTextOverflow.Clip,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_customAnnotatedText() {
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
            BasicReadMoreText(
                text = annotatedText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_expanded_customAnnotatedText() {
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
            BasicReadMoreText(
                text = annotatedText,
                expanded = true,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readLessText = "Read less",
                readLessStyle = SpanStyle(
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_customFontStyle() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 3,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Black,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_toggleAreaMore() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                ),
                toggleArea = ToggleArea.More,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_emojiText() {
        val emojiText = "🎨 Colorful emojis! 🌈✨ " +
            "Testing emoji rendering with read more functionality. " +
            "🚀 More emojis here: 💫⭐🌟✨🎉🎊🎁 " +
            "This is a longer text with many emojis."

        composeTestRule.setContent {
            BasicReadMoreText(
                text = emojiText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_toggleAreaAll() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                ),
                toggleArea = ToggleArea.All,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_expanded_toggleAreaAll() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = true,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readLessText = "Read less",
                readLessStyle = SpanStyle(
                    color = Color.Blue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                ),
                toggleArea = ToggleArea.All,
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_shortText_noCollapse() {
        val shortText = "This is a short text."

        composeTestRule.setContent {
            BasicReadMoreText(
                text = shortText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 3,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_maxLines1() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 1,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_maxLines4() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 4,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_maxLines5() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 5,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_rtlText() {
        val rtlText = "هذا نص تجريبي باللغة العربية. " +
            "يستخدم هذا النص لاختبار وظيفة القراءة المزيد مع النصوص من اليمين إلى اليسار. " +
            "النص العربي يتدفق من اليمين إلى اليسار ويجب أن يتم عرضه بشكل صحيح."

        composeTestRule.setContent {
            BasicReadMoreText(
                text = rtlText,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    textDirection = TextDirection.Rtl,
                ),
                readMoreMaxLines = 2,
                readMoreText = "اقرأ المزيد",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_expanded_rtlText() {
        val rtlText = "هذا نص تجريبي باللغة العربية. " +
            "يستخدم هذا النص لاختبار وظيفة القراءة المزيد مع النصوص من اليمين إلى اليسار. " +
            "النص العربي يتدفق من اليمين إلى اليسار ويجب أن يتم عرضه بشكل صحيح."

        composeTestRule.setContent {
            BasicReadMoreText(
                text = rtlText,
                expanded = true,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    textDirection = TextDirection.Rtl,
                ),
                readMoreMaxLines = 2,
                readMoreText = "اقرأ المزيد",
                readLessText = "اقرأ أقل",
                readLessStyle = SpanStyle(
                    color = Color.Blue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_noPadding() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(0.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_asymmetricPadding() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(
                    start = 8.dp,
                    top = 4.dp,
                    end = 24.dp,
                    bottom = 12.dp,
                ),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_largePadding() {
        composeTestRule.setContent {
            BasicReadMoreText(
                text = longText,
                expanded = false,
                contentPadding = PaddingValues(32.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_longUnbreakableWord() {
        val textWithLongWord = "This text contains a very long word: " +
            "Supercalifragilisticexpialidocious" +
            "andanevenlongerwordwithoutanyspacestotestwordwrappingbehavior " +
            "followed by normal text that should wrap properly."

        composeTestRule.setContent {
            BasicReadMoreText(
                text = textWithLongWord,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }

    @Test
    fun basicReadMoreText_collapsed_urlText() {
        val textWithUrls = "Check out this website: " +
            "https://www.example.com/very/long/path/that/might/cause/wrapping/issues " +
            "and another one https://github.com/repository/name for more information."

        composeTestRule.setContent {
            BasicReadMoreText(
                text = textWithUrls,
                expanded = false,
                contentPadding = PaddingValues(16.dp),
                style = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                ),
                readMoreMaxLines = 2,
                readMoreText = "Read more",
                readMoreStyle = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        composeTestRule.onRoot()
            .captureRoboImage()
    }
}
