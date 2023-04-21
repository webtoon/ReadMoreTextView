package com.webtoonscorp.android.readmore.shared

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webtoonscorp.android.readmore.shared.foundation.BasicReadMoreTextDemo

private enum class Destination {
    Home,
    Foundation,
    Material,
    Material3,
}

@Composable
internal fun App() {
    val (destination, onDestinationChanged) = rememberSaveable {
        mutableStateOf(Destination.Home)
    }
    Crossfade(destination) {
        when (it) {
            Destination.Home -> Home(onClick = onDestinationChanged)
            Destination.Foundation ->
                BasicReadMoreTextDemo(upPress = { onDestinationChanged(Destination.Home) })
            Destination.Material ->
                com.webtoonscorp.android.readmore.shared.material.ReadMoreTextDemo(
                    upPress = { onDestinationChanged(Destination.Home) }
                )
            Destination.Material3 ->
                com.webtoonscorp.android.readmore.shared.material3.ReadMoreTextDemo(
                    upPress = { onDestinationChanged(Destination.Home) }
                )
        }
    }
}

@Composable
private fun Home(onClick: (Destination) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = StringResources.app_name)
            })
        }
    ) {
        Column {
            Item(
                title = StringResources.compose_foundation_title,
                description = StringResources.compose_foundation_description,
                onClick = {
                    onClick(Destination.Foundation)
                },
            )
            Divider()
            Item(
                title = StringResources.compose_material_title,
                description = StringResources.compose_material_description,
                onClick = {
                    onClick(Destination.Material)
                },
            )
            Divider()
            Item(
                title = StringResources.compose_material3_title,
                description = StringResources.compose_material3_description,
                onClick = {
                    onClick(Destination.Material3)
                },
            )
            Divider()
        }
    }
}

@Composable
private fun Item(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(start = 18.dp, top = 16.dp, end = 18.dp, bottom = 18.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = description,
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
        )
    }
}
