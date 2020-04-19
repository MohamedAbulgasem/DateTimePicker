# DateTimePicker
---

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

To add DateTimePicker, you must add the `DateTimePicker` Maven repository to
your project.

Add the dependency in your app or module `build.gradle` file:

```gradle
dependencies {
    implementation 'com.mohamedabulgasem.datetimepicker:datetimepicker:0.1.0'
}
```

## Usage

Use DateTimePicker Builder to construct and show the picker.

```kotlin
// Pass activity reference to Builder and set your OnDateTimeSetListener
DateTimePicker.Builder(this)
    .onDateTimeSetListener { year, month, dayOfMonth, hourOfDay, minute ->
        setDateAndTime(year, month, dayOfMonth, hourOfDay, minute)
    }
    .build()
    .show()
```

## License

Copyright 2018 Mohamed Abulgasem

   Licensed under the Apache License v2, you may use this library in
   compliance with the License. A copy of the License can be obtained
   [here](http://www.apache.org/licenses/LICENSE-2.0).
