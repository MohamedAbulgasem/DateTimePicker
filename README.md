# DateTimePicker
[![Download](https://api.bintray.com/packages/mohamedabulgasem/maven/datetimepicker/images/download.svg)](https://bintray.com/mohamedabulgasem/maven/datetimepicker/_latestVersion)

  - [Introduction](#introduction)
  - [Requirements](#requirements)
  - [Getting Started](#getting-started)
    - [Declaring dependency](#declaring-dependency)
    - [Usage](#usage)
    - [Configuration](#configuration)
  - [License](#license)

## Introduction
An easy to use Date and Time picker that leverages the OS's Material
`DatePickerDialog` & `TimePickerDialog`

## Requirements

- Minimum SDK Version 21

## Getting Started

### Declaring dependency

Add the dependency in your app or module `build.gradle` file:

```gradle
dependencies {
    implementation 'com.mohamedabulgasem:datetimepicker:0.2.0'
}
```

### Usage

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

### Configuration

Configure any of the available optional properties.

```kotlin
private var dateTimePicker: DateTimePicker? = null

fun showDateTimePicker() {
    if (dateTimePicker == null) {
        dateTimePicker = DateTimePicker.Builder(this)
            // Set a listener to be invoked with selected date and time values upon user completion.
            // NB: month is zero-based, Jan is 0; Dec is 11.
            .onDateTimeSetListener { year, month, dayOfMonth, hourOfDay, minute ->
                setAppointment(year, month, dayOfMonth, hourOfDay, minute)
            }
            // Optionally run some code when the picker is shown.
            .onShowListener { highlightAppointmentView(true) }
            // Optionally run some code when the picker is dismissed.
            .onDismissListener { highlightAppointmentView(false) }
            // Apply custom theme styling to the picker.
            // By default, the picker uses the consumer app theme values 
            // and mostly makes use of the colorAccent.
            .theme(R.style.DateTimePickerTheme)
            // Set some or all initial picker date and time values.
            // By default, initialYear, initialMonth and initialDay are set to the current date;
            // initialHour and initialMinute are set to zero.
            .initialValues(
                initialYear = 2021,
                initialMonth = 0,
                initialDay = 1,
                initialHour = 14,
                initialMinute = 30
            )
            // OR set initial values from a Calendar instance.
            .initialValues(Calendar.getInstance())
            // Indicate whether to use a 24 hour or 12 hour AM/PM view for the time picker.
            // By default, a 24 hour view is set.
            .is24HourView(false)
            // Optionally set a minimum date supported by the picker in milliseconds
            // or by specifying date values.
            .minDate(System.currentTimeMillis())
            // Optionally set a maximum date supported by the picker in milliseconds
            // or by specifying date values.
            .maxDate(
                maxYear = 2022,
                maxMonth = 11,
                maxDay = 31
            )
            .build()
    }
    dateTimePicker?.show()
}
```

## License

Copyright © 2020 Mohamed Abulgasem

   Licensed under the Apache License v 2.0. You may use this library in
   compliance with the License. A copy of the License can be obtained
   [here](http://www.apache.org/licenses/LICENSE-2.0).
