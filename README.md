# ReadMoreTextView

<a href="https://opensource.org/licenses/Apache-2.0"><img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
<a href='https://developer.android.com'><img src='http://img.shields.io/badge/platform-android-green.svg'/></a>

This library provides collapsible Text widgets with 'Read more' text. (Including [Jetpack Compose][compose])

| **Collapsed** | <img width="300" src="docs/collapsed.png" /> |
| ------------- | -------------------------------------------- |
| **Expanded**  | <img width="300" src="docs/expanded.png" />  |


## Libraries

### [ReadMore-View](./readmore-view/)
A library that provides collapsible `TextView` with 'Read more' text. (for Android View System)

### [ReadMore-Foundation](./readmore-foundation/)
A library that provides collapsible `BasicText` with 'Read more' text. (for [Jetpack Compose][compose])

### [ReadMore-Material](./readmore-material/)
A library that provides collapsible `Text` with 'Read more' text based on Material Theme. (for [Jetpack Compose][compose])

### [ReadMore-Material3](./readmore-material3/)
A library that provides collapsible `Text` with 'Read more' text based on Material3 Theme. (for [Jetpack Compose][compose])


## Attributes

### `readMoreMaxLines`

| **2 (default)** | <img width="300" src="docs/readMoreMaxLines_default.png" /> |
| --------------- | ------------------------------------------------------------ |
| **`"1"`**       | <img width="300" src="docs/readMoreMaxLines_custom.png" /> |

### `readMoreOverflow`

| **Ellipsis (default)** | <img width="300" src="docs/readMoreOverflow_default.png" /> |
| ---------------------- | ------------------------------------------------------------ |
| **Clip**               | <img width="300" src="docs/readMoreOverflow_clip.png" /> |

### `readMoreText`

| **"" (default)**  | <img width="300" src="docs/readMoreText_default.png" /> |
| ----------------- | ------------------------------------------------------------ |
| **`"Read more"`** | <img width="300" src="docs/readMoreText_custom.png" /> |

### `readMoreTextSize`

| **Same with `textSize` (default)** | <img width="300" src="docs/readMoreTextSize_default.png" /> |
| ---------------------------------- | ------------------------------------------------------------ |
| **`"11sp"`**                       | <img width="300" src="docs/readMoreTextSize_custom.png" /> |

### `readMoreTextColor`

| **Same with `textColor` (default)** | <img width="300" src="docs/readMoreTextColor_default.png" /> |
| ----------------------------------- | ------------------------------------------------------------ |
| **`"#FF0000"`**                     | <img width="300" src="docs/readMoreTextColor_custom.png" /> |

### `readMoreTextUnderline`

| **false (default)** | <img width="300" src="docs/readMoreTextUnderline_default.png" /> |
| ------------------- | ------------------------------------------------------------ |
| **true**            | <img width="300" src="docs/readMoreTextUnderline_true.png" /> |

### `readMoreTextStyle`

| **Normal (default)** | <img width="300" src="docs/readMoreTextStyle_default.png" /> |
| -------------------- | ------------------------------------------------------------ |
| **Bold**             | <img width="300" src="docs/readMoreTextStyle_bold.png" /> |
| **Italic**           | <img width="300" src="docs/readMoreTextStyle_italic.png" /> |


## License

```
Copyright 2022 NAVER Webtoon

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[compose]: https://developer.android.com/jetpack/compose
