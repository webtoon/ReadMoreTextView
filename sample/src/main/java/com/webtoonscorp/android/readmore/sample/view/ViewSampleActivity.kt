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
package com.webtoonscorp.android.readmore.sample.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.text.inSpans
import androidx.core.text.strikeThrough
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.webtoonscorp.android.readmore.sample.databinding.ActivitySampleBinding

class ViewSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.item01.description.setOnStateChangeListener { expanded ->
            val title = binding.item01.title.text
            binding.root.showSnackBar("'$title' " + if (expanded) "expanded!" else "collapsed!")
        }

        binding.item02.description.setOnStateChangeListener { expanded ->
            val title = binding.item02.title.text
            binding.root.showSnackBar("'$title' " + if (expanded) "expanded!" else "collapsed!")
        }

        binding.item03.root.setOnClickListener {
            binding.item03.description.toggle()
        }
        binding.item03.description.setOnStateChangeListener { expanded ->
            val title = binding.item03.title.text
            binding.root.showSnackBar("'$title' " + if (expanded) "expanded!" else "collapsed!")
        }

        binding.item04.description.setOnClickListener {
            binding.item04.description.toggle()
        }
        binding.item04.description.setOnStateChangeListener { expanded ->
            val title = binding.item04.title.text
            binding.root.showSnackBar("'$title' " + if (expanded) "expanded!" else "collapsed!")
        }

        val description = buildSpannedString {
            val color = getColorFromAttribute(
                com.google.android.material.R.attr.colorSurface
            )
            val backgroundColor = getColorFromAttribute(
                com.google.android.material.R.attr.colorOnSurface
            )
            inSpans(
                spans = arrayOf(
                    ForegroundColorSpan(color),
                    BackgroundColorSpan(backgroundColor)
                )
            ) {
                append("abcdefghijklmnopqrstuvwxyz,")
            }
            inSpans(span = AbsoluteSizeSpan(12, true)) {
                append("abcdefghijklmnopqrstuvwxyz,")
            }
            strikeThrough {
                append("abcdefghijklmnopqrstuvwxyz,")
            }
            color(Color.BLUE) {
                append("abcdefghijklmnopqrstuvwxyz,")
            }
            append("abcdefghijklmnopqrstuvwxyz.")
        }
        binding.item05.description.text = description
        binding.item05.description.setOnStateChangeListener { expanded ->
            val title = binding.item05.title.text
            binding.root.showSnackBar("'$title' " + if (expanded) "expanded!" else "collapsed!")
        }

        binding.item06.description.setOnStateChangeListener { expanded ->
            val title = binding.item06.title.text
            binding.root.showSnackBar("'$title' " + if (expanded) "expanded!" else "collapsed!")
        }
    }

    private fun View.showSnackBar(message: String) {
        Snackbar.make(this, message, LENGTH_SHORT).show()
    }

    @ColorInt
    private fun Context.getColorFromAttribute(@AttrRes resId: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(resId, typedValue, true)
        return ContextCompat.getColor(this, typedValue.resourceId)
    }
}
