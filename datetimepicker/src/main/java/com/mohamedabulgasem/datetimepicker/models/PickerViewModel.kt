package com.mohamedabulgasem.datetimepicker.models

import androidx.fragment.app.*
import java.util.*

internal class PickerViewModel(
    val context: FragmentActivity
) {
    var onDateTimeSetListener: OnDateTimeSetListener? = null
    var onShowListener: OnShowListener? = null
    var onDismissListener: OnDismissListener? = null
    var themeResId: Int = 0
    var initialYear: Int = Calendar.getInstance()[Calendar.YEAR]
    var initialMonth: Int = Calendar.getInstance()[Calendar.MONTH]
    var initialDay: Int = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
    var initialHour: Int = 0
    var initialMinute: Int = 0
    var is24HourView: Boolean = true
    var maxDate: Long? = null
    var minDate: Long? = null
}