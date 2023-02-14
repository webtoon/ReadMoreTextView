# ReadMoreText for Jetpack Compose (Material3)

[![Maven Central](https://img.shields.io/maven-central/v/com.webtoonscorp.android/readmore-material3)](https://search.maven.org/artifact/com.webtoonscorp.android/readmore-material3)

A library that show 'Read more' and 'Read less' text in Jetpack Compose Text.

| Collapsed                               | Expanded                              |
| --------------------------------------- | ------------------------------------- |
| <img width="300" src="collapsed.png" /> | <img width="300" src="expanded.png" /> |

## Usage

`ReadMoreText` is based on material3 theme like `androidx.compose.material3.Text`.

```kotlin
@Composable
fun ReadMoreTextSample() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    ReadMoreText(
        text = "...",
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier.fillMaxWidth(),

        /* read more */
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
        ),

        /* read less */
        readLessText = "Read less",
        readLessColor = Color.Black,
        readLessFontSize = 15.sp,
        readLessFontStyle = FontStyle.Normal,
        readLessFontWeight = FontWeight.Bold,
        readLessFontFamily = FontFamily.Default,
        readLessTextDecoration = TextDecoration.None,
        readLessStyle = SpanStyle(
            // ...
        ),

        /* It expands and collapses by default. */
        toggleArea = ToggleArea.All,
        // If you want to allow clicks only on 'more' and 'less' text, use this.
        toggleArea = ToggleArea.More,
    )
}
```

## Download

[![Maven Central](https://img.shields.io/maven-central/v/com.webtoonscorp.android/readmore-material3)](https://search.maven.org/artifact/com.webtoonscorp.android/readmore-material3)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.webtoonscorp.android:readmore-material3:<version>"
}
```
