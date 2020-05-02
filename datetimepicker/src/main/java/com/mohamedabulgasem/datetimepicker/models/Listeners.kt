package com.mohamedabulgasem.datetimepicker.models

/**
 * Callback function when the user is done selecting the date and time with the params
 * in the following order: (year, month, dayOfMonth, hourOfDay, minute).
 * NB: month is zero-based, Jan is 0; Dec is 11.
 */
typealias OnDateTimeSetListener = (Int, Int, Int, Int, Int) -> Unit

/** Function allows running some code when the dialog is shown. */
typealias OnShowListener = () -> Unit

/** Function allows running some code when the dialog is dismissed. */
typealias OnDismissListener = () -> Unit