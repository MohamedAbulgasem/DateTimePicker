# DateTimePicker
[![Download](https://api.bintray.com/packages/mohamedabulgasem/maven/datetimepicker/images/download.svg)](https://bintray.com/mohamedabulgasem/maven/datetimepicker/_latestVersion)

- [Introduction](#introduction)
  - [Requirements](#requirements)
  - [Getting Started](#getting-started)
    -   [Declaring dependency](#declaring-dependency)
  - [Usage](#usage)
  - [License](#license)

# Introduction 
An easy to use Date and Time picker that leverages the OS's Material
`DatePickerDialog` & `TimePickerDialog`

## Requirements

- Minimum SDK Version 21

## Getting Started

#### Declaring dependency

Add the dependency in your app or module `build.gradle` file:

```gradle
dependencies {
    implementation 'com.mohamedabulgasem:datetimepicker:0.2.0'
}
```

## Usage

Use DateTimePicker Builder to construct and show the picker.

```kotlin
// Pass activity reference to Builder and set your OnDateTimeSetListener
DateTimePicker.Builder(this)
    .onDateTimeSetListener { year, month, dayOfMonth, hourOfDay, minute ->
        // Use selected date and time values
    }
    .build()
    .show()
```

## License

Copyright © 2020 Mohamed Abulgasem

   Licensed under the Apache License v2, you may use this library in
   compliance with the License. A copy of the License can be obtained
   [here](http://www.apache.org/licenses/LICENSE-2.0).
