# ReadMoreTextView

[![Maven Central](https://img.shields.io/maven-central/v/com.webtoonscorp.android/readmore-view)](https://search.maven.org/artifact/com.webtoonscorp.android/readmore-view)

A library that show 'Read more' and 'Read less' text in Android TextView.

| Collapsed                               | Expanded                              |
| --------------------------------------- | ------------------------------------- |
| <img width="300" src="collapsed.png" /> | <img width="300" src="expanded.png" /> |

## Usage

```xml
<com.webtoonscorp.android.readmore.ReadMoreTextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"

    // Set maximum lines to show 'read more' text.
    app:readMoreMaxLines="3"
    app:readMoreOverflow="ellipsis"

    // Set 'read more' text and styles.
    app:readMoreText="@string/read_more"
    app:readMoreTextColor="?colorPrimary"
    app:readMoreTextFontFamily="sans-serif"
    app:readMoreTextSize="12sp"
    app:readMoreTextStyle="bold"
    app:readMoreTextUnderline="true"
    app:readMoreTypeface="normal"

    // Set textAppearance to 'read more' text.
    app:readMoreTextAppearance="@style/TextAppearance.AppCompat.Small"

    // Set 'read less' text and styles.
    app:readLessText="@string/read_less"
    app:readLessTextColor="?colorPrimary"
    app:readLessTextFontFamily="sans-serif"
    app:readLessTextSize="12sp"
    app:readLessTextStyle="bold"
    app:readLessTextUnderline="true"
    app:readLessTypeface="normal"

    // Set textAppearance to 'read less' text.
    app:readLessTextAppearance="@style/TextAppearance.AppCompat.Small"

    // It expands and collapses by default.
    app:readMoreToggleArea="all"
    // If you want to allow clicks only on 'more' and 'less' text, you must be set this attribute to more.
    app:readMoreToggleArea="more"
    // If you want to use custom OnClickListener, you must be set this attribute to none.
    app:readMoreToggleArea="none"
    />
```

## Download

[![Maven Central](https://img.shields.io/maven-central/v/com.webtoonscorp.android/readmore-view)](https://search.maven.org/artifact/com.webtoonscorp.android/readmore-view)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.webtoonscorp.android:readmore-view:<version>"
}
```
