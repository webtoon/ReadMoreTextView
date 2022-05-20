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
package com.webtoonscorp.android.readmore.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.webtoonscorp.android.readmore.sample.compose.foundation.ComposeFoundationSampleActivity
import com.webtoonscorp.android.readmore.sample.compose.material.ComposeMaterialSampleActivity
import com.webtoonscorp.android.readmore.sample.compose.material3.ComposeMaterial3SampleActivity
import com.webtoonscorp.android.readmore.sample.databinding.ActivityMainBinding
import com.webtoonscorp.android.readmore.sample.view.ViewSampleActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemView.setOnClickListener {
            startActivity(Intent(this, ViewSampleActivity::class.java))
        }
        binding.itemComposeFoundation.setOnClickListener {
            startActivity(Intent(this, ComposeFoundationSampleActivity::class.java))
        }
        binding.itemComposeMaterial.setOnClickListener {
            startActivity(Intent(this, ComposeMaterialSampleActivity::class.java))
        }
        binding.itemComposeMaterial3.setOnClickListener {
            startActivity(Intent(this, ComposeMaterial3SampleActivity::class.java))
        }
    }
}
