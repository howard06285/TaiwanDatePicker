# Taiwan Date Picker

A custom Android date picker dialog for selecting dates in the Taiwanese calendar format (民國年).

[![](https://jitpack.io/v/howard06285/TaiwanDatePicker.svg)](https://jitpack.io/#howard06285/TaiwanDatePicker)
[![GitHub Sponsor](https://img.shields.io/static/v1?label=Sponsor&message=%E2%9D%A4&logo=GitHub&color=%23fe8e86)](https://github.com/sponsors/howard06285)

## Features

- 🇹🇼 Support for Taiwanese calendar (民國年) format
- 🌓 Dark theme support with automatic adaptation
- 📱 Material Design components
- 🎨 Customizable appearance
- 📦 Easy integration via JitPack

## Screenshots

*Add screenshots here when available*

## Installation

Add JitPack repository to your root `build.gradle` file:

```gradle
allprojects {
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the dependency to your app-level `build.gradle.kts` file:

```gradle
dependencies {
    implementation("com.github.howard06285:TaiwanDatePicker:1.0.5")
}
```

## Usage

### Basic Usage

```kotlin
import com.howard06285.taiwandatepicker.TaiwanDatePickerDialog
import com.howard06285.taiwandatepicker.SimpleDate

// Show Taiwan Date Picker with 民國年 format
TaiwanDatePickerDialog.show(
    fragmentManager = supportFragmentManager,
    title = "選擇日期",
    useADYearFormat = false,            // false: 民國年       
    initialDate = SimpleDate.now()
) { selectedDate ->
    val date = "${selectedDate.year}.${selectedDate.month}.${selectedDate.day}"
    binding.textview.text = date
}
```

### Advanced Usage

```kotlin

// Show with Western year format
TaiwanDatePickerDialog.show(
    fragmentManager = supportFragmentManager,
    title = "Select date",
    useADYearFormat = true,            // true: Western year format
    initialDate = SimpleDate.now()
) { selectedDate ->
    val date = "${selectedDate.year}.${selectedDate.month}.${selectedDate.day}"
    binding.textview.text = date
}
```

## Parameters

| Parameter | Type | Description | Default |
|-----------|------|-------------|---------|
| `fragmentManager` | FragmentManager | Fragment manager for showing dialog | Required |
| `title` | String? | Dialog title | "" |
| `useADYearFormat` | Boolean | true: 西元年, false: 民國年 | Required |
| `initialDate` | SimpleDate | Initial date to display | SimpleDate.now() |
| `onDateSelected` | (SimpleDate) -> Unit | Callback when date is selected | Required |

## SimpleDate API

The `SimpleDate` class provides a simple, API level 24+ compatible date representation:

| Property/Method | Type | Description |
|----------------|------|-------------|
| `year` | Int | Year (e.g., 2024) |
| `month` | Int | Month (1-12) |
| `day` | Int | Day of month (1-31) |
| `taiwanYear` | Int | Taiwan year (民國年) = year - 1911 |
| `now()` | SimpleDate | Creates current date |
| `daysInMonth()` | Int | Returns days in this month |
| `toString()` | String | Format: "2024-01-15" |

## Dark Theme Support

The Taiwan Date Picker automatically adapts to your app's dark theme settings:

- Light theme: White background with dark text
- Dark theme: Dark background with light text

## Requirements

- Android API 24+ (Android 7.0)
- AndroidX support libraries
- Kotlin
- **No desugaring required** - Uses standard Calendar API for maximum compatibility

## Sample App

Check out the sample app in this repository for a complete implementation example.

## License

```
MIT License

Copyright (c) 2025 Howard

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Support This Project

If you find this library helpful, please consider supporting its development:

## 支持開發

如果這個庫對您有幫助，您可以通過以下方式支持開發：

### ⭐ Star this project
給我一個星星是最好的支持！


### ☕ Buy me a coffee
[![Buy Me A Coffee](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-donate-yellow.svg?style=for-the-badge&logo=buy-me-a-coffee)](https://www.buymeacoffee.com/howard06285)

### 💝 Sponsor on GitHub
[![GitHub Sponsors](https://img.shields.io/badge/GitHub%20Sponsors-sponsor-pink.svg?style=for-the-badge&logo=github)](https://github.com/sponsors/howard06285)


🙏 **感謝您的支持！您的贊助將幫助我持續改進和維護這個專案。**   
🙏 Your support helps maintain and improve this project!

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Changelog

### 1.0.0
- Initial release
- Support for Taiwan calendar format (民國年)
- Dark theme support
- Material Design components
