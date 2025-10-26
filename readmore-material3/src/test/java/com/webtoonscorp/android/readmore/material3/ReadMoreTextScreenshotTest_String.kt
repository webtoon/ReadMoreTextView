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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.github.takahirom.roborazzi.RoborazziRule
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import java.io.File

internal data class StringScreenshotTestCase(
    val description: String,
    val expanded: Boolean,
    val text: String,
    val readMoreText: String = "",
    val readLessText: String = "",
) {
    override fun toString(): String = description
}

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
internal class ReadMoreTextScreenshotTest_String(
    private val testCase: StringScreenshotTestCase,
) {
    companion object Companion {

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun data(): Collection<Array<StringScreenshotTestCase>> {
            val shortText = "Lorem ipsum dolor sit amet."
            val longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris."
            val rtlText =
                "ليال، فتاة تسلك درباً موحشاً بحثاً عن حلٍ لجنونها. فكيف ستنتهي مغامرتها عندما لا تستطيع التفريق بين الوهم و الحقيقة و بين العدو و الصديق. هذه الرحلة ستضعها في مواجهة أسوأ مخاوفها، فماذا ستكون تلك الكوابيس؟ من سيقابلها في الطريق أم من يسكن عقلها الفارغ؟"
            val emojiText =
                "😀😃😄😁😆😅😂🤣🥲☺️.😊😇🙂🙃😉😌😍🥰👪👨‍👩‍👦.👨‍👩‍👧👨‍👩‍👧‍👦👨‍👩‍👦‍👦👨‍👩‍👧‍👧👨‍👨‍👦👨‍👨‍👧👨‍👨‍👧‍👦👨‍👨‍👦‍👦👨‍👨‍👧‍👧👩‍👩‍👦.👩‍👩‍👧👩‍👩‍👧‍👦👩‍👩‍👦‍👦👩‍👩‍👧‍👧👨‍👦👨‍👦‍👦👨‍👧👨‍👧‍👦👨‍👧‍👧👩‍👦.👩‍👦‍👦👩‍👧👩‍👧‍👦👩‍👧‍👧😘😗😙😚😋😛.😝😜🤪🤨🧐🤓😎🥸🤩🥳.😏😒😞😔😟😕🙁☹️😣😖.😫😩🥺😢😭😤😠😡🤬🤯😳🥵🥶😱😨😰😥😓🤗🤔🤭🤫🤥😶😐😑😬🙄😯😦😧😮😲🥱😴🤤😪😵🤐🥴🤢🤮🤧😷🤒🤕🤑🤠😈👿👹👺🤡💩👻💀☠️👽👾🤖🎃😺😸😹😻😼😽🙀😿😾"

            return listOf(
                /* Default */
                StringScreenshotTestCase(
                    description = "collapsed_shortText_default",
                    expanded = false,
                    text = shortText,
                ),
                StringScreenshotTestCase(
                    description = "expanded_shortText_default",
                    expanded = true,
                    text = shortText,
                ),
                StringScreenshotTestCase(
                    description = "collapsed_longText_default",
                    expanded = false,
                    text = longText,
                ),
                StringScreenshotTestCase(
                    description = "expanded_longText_default",
                    expanded = true,
                    text = longText,
                ),

                /* ReadMoreText */
                StringScreenshotTestCase(
                    description = "collapsed_shortText_withReadMoreText",
                    expanded = false,
                    text = shortText,
                    readMoreText = "Read more",
                ),
                StringScreenshotTestCase(
                    description = "expanded_shortText_withReadMoreText",
                    expanded = true,
                    text = shortText,
                    readMoreText = "Read more",
                ),
                StringScreenshotTestCase(
                    description = "collapsed_longText_withReadMoreText",
                    expanded = false,
                    text = longText,
                    readMoreText = "Read more",
                ),
                StringScreenshotTestCase(
                    description = "expanded_longText_withReadMoreText",
                    expanded = true,
                    text = longText,
                    readMoreText = "Read more",
                ),

                /* ReadLessText */
                StringScreenshotTestCase(
                    description = "collapsed_shortText_withReadLessText",
                    expanded = false,
                    text = shortText,
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    description = "expanded_shortText_withReadLessText",
                    expanded = true,
                    text = shortText,
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    description = "collapsed_longText_withReadLessText",
                    expanded = false,
                    text = longText,
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    description = "expanded_longText_withReadLessText",
                    expanded = true,
                    text = longText,
                    readLessText = "Read less",
                ),

                /* ReadMoreText and ReadLessText */
                StringScreenshotTestCase(
                    description = "collapsed_shortText_withReadMoreLessText",
                    expanded = false,
                    text = shortText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    description = "expanded_shortText_withReadMoreLessText",
                    expanded = true,
                    text = shortText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    description = "collapsed_longText_withReadMoreLessText",
                    expanded = false,
                    text = longText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    description = "expanded_longText_withReadMoreLessText",
                    expanded = true,
                    text = longText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),

                /* RTL */
                StringScreenshotTestCase(
                    description = "collapsed_rtlText_withReadMoreLessText",
                    expanded = false,
                    text = rtlText,
                    readMoreText = "اقرأ المزيد",
                    readLessText = "اقرأ أقل",
                ),
                StringScreenshotTestCase(
                    description = "expanded_rtlText_withReadMoreLessText",
                    expanded = true,
                    text = rtlText,
                    readMoreText = "اقرأ المزيد",
                    readLessText = "اقرأ أقل",
                ),

                /* Emoji */
                StringScreenshotTestCase(
                    description = "collapsed_emojiText_withReadMoreLessText",
                    expanded = false,
                    text = emojiText,
                    readMoreText = "Read more",
                    readLessText = "Read less",
                ),
                StringScreenshotTestCase(
                    description = "expanded_emojiText_withReadMoreLessText",
                    expanded = true,
                    text = emojiText,
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
