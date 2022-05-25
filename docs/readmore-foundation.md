# ReadMoreText for Jetpack Compose

A library that shows 'Read more' text in Jetpack Compose BasicText.

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
        text = description,
        expanded = expanded,
        modifier = Modifier
            .clickable { onExpandedChange(!expanded) }
            .fillMaxWidth(),
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
    implementation "com.webtoonscorp.android:readmore-foundation:<version>"
}
```
