package com.mohamedabulgasem.datetimepicker.models

/** Callback function when the user is done selecting the date and time */
typealias OnDateTimeSetListener = (Int, Int, Int, Int, Int) -> Unit

/** Function allows running some code when the dialog is shown */
typealias OnShowListener = () -> Unit

/** Function allows running some code when the dialog is dismissed */
typealias OnDismissListener = () -> Unit