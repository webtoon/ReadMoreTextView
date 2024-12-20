package com.webtoonscorp.android.readmore.sample.compose.material

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun CustomTheme(
    darkTheme:Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) {
            darkColors()
        } else {
            lightColors()
        },
        content = content,
    )
}
