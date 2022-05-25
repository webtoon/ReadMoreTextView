# ReadMoreText for Jetpack Compose (Material)

A library that shows 'Read more' text in Jetpack Compose Text.

| Collapsed                               | Expanded                              |
| --------------------------------------- | ------------------------------------- |
| <img width="300" src="collapsed.png" /> | <img width="300" src="expanded.png" /> |

## Usage

`ReadMoreText` is based on material theme like `androidx.compose.material.Text`.

```kotlin
@Composable
fun ReadMoreTextSample() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    ReadMoreText(
        text = "...",
        expanded = expanded,
        modifier = Modifier
            .clickable { onExpandedChange(!expanded) }
            .fillMaxWidth(),
        readMoreText = "Read more",
        readMoreColor = Color.Black,
        readMoreFontSize = 15.sp,
        readMoreFontStyle = FontStyle.Normal,
        readMoreFontWeight = FontWeight.Bold,
        readMoreFontFamily = FontFamily.Default,
        readMoreTextDecoration = TextDecoration.None,
        readMoreMaxLines = 2,
        readMoreOverflow = ReadMoreTextOverflow.Ellipsis,
        readMoreStyle = SpanStyle(
            // ...
        )
    )
}
```

## Download

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.webtoonscorp.android:readmore-material:<version>"
}
```
