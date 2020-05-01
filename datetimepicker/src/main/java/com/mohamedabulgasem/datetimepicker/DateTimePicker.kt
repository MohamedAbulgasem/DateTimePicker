package com.mohamedabulgasem.datetimepicker

import androidx.annotation.*
import androidx.fragment.app.*
import com.mohamedabulgasem.datetimepicker.internal.DateTimePickerImpl
import com.mohamedabulgasem.datetimepicker.models.*
import java.util.*

interface DateTimePicker {

    fun show()
    fun dismiss()
    fun isShowing(): Boolean

    class Builder(internal val context: FragmentActivity) {

        internal var onDateTimeSetListener: OnDateTimeSetListener? = null
        internal var onShowListener: OnShowListener? = null
        internal var onDismissListener: OnDismissListener? = null
        internal var themeResId: Int = 0
        internal var initialYear: Int = Calendar.getInstance()[Calendar.YEAR]
        internal var initialMonth: Int = Calendar.getInstance()[Calendar.MONTH]
        internal var initialDay: Int = Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        internal var initialHour: Int = 0
        internal var initialMinute: Int = 0
        internal var is24HourView: Boolean = true
        internal var maxDate: Long? = null
        internal var minDate: Long? = null

        fun onDateTimeSetListener(listener: OnDateTimeSetListener) = apply {
            this.onDateTimeSetListener = listener
        }

        fun onShowListener(listener: OnShowListener) = apply {
            this.onShowListener = listener
        }

        fun onDismissListener(listener: OnDismissListener) = apply {
            this.onDismissListener = listener
        }

        fun theme(@StyleRes themeResId: Int) = apply {
            this.themeResId = themeResId
        }

        fun initialValues(
            initialYear: Int = this.initialYear,
            initialMonth: Int = this.initialMonth,
            initialDay: Int = this.initialDay,
            initialHour: Int = this.initialHour,
            initialMinute: Int = this.initialMinute
        ) = apply {
            this.initialYear = initialYear
            this.initialMonth = initialMonth
            this.initialDay = initialDay
            this.initialHour = initialHour
            this.initialMinute = initialMinute
        }

        fun is24HourView(is24HourView: Boolean) = apply {
            this.is24HourView = is24HourView
        }

        fun maxDate(maxDate: Long) = apply {
            this.maxDate = maxDate
        }

        fun minDate(minDate: Long) = apply {
            this.minDate = minDate
        }

        fun build(): DateTimePicker = DateTimePickerImpl(this)

    }

}