# ReadMoreText for Jetpack Compose

[![Maven Central](https://img.shields.io/maven-central/v/com.webtoonscorp.android/readmore-foundation)](https://search.maven.org/artifact/com.webtoonscorp.android/readmore-foundation)

A library that show 'Read more' and 'Read less' text in Jetpack Compose BasicText.

| Collapsed                               | Expanded                              |
| --------------------------------------- | ------------------------------------- |
| <img width="300" src="collapsed.png" /> | <img width="300" src="expanded.png" /> |

## Usage

`BasicReadMoreText` is a basic element like `androidx.compose.foundation.text.BasicText`.

```kotlin
@Composable
fun BasicReadMoreTextSample() {
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    BasicReadMoreText(
        text = "...",
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier.fillMaxWidth(),

        /* read more */
        readMoreText = "Read more",
        readMoreMaxLines = 2,
        readMoreOverflow = ReadMoreTextOverflow.Ellipsis,
        readMoreStyle = SpanStyle(
            color = Color.Black,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textDecoration = TextDecoration.None
        ),

        /* read less */
        readLessText = "Read more",
        readLessStyle = SpanStyle(
            color = Color.Black,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            textDecoration = TextDecoration.None
        ),

        /* It expands and collapses by default. */
        toggleArea = ToggleArea.All,
        // If you want to allow clicks only on 'more' and 'less' text, use this.
        toggleArea = ToggleArea.More,
    )
}
```

## Download

[![Maven Central](https://img.shields.io/maven-central/v/com.webtoonscorp.android/readmore-foundation)](https://search.maven.org/artifact/com.webtoonscorp.android/readmore-foundation)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.webtoonscorp.android:readmore-foundation:<version>"
}
```
