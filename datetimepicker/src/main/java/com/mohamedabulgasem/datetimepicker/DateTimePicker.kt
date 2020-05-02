package com.mohamedabulgasem.datetimepicker

import androidx.annotation.*
import androidx.fragment.app.*
import com.mohamedabulgasem.datetimepicker.internal.DateTimePickerImpl
import com.mohamedabulgasem.datetimepicker.models.*
import java.util.*

interface DateTimePicker {

    /** Display the picker on screen. */
    fun show()

    /** Dismiss/remove the picker from screen. */
    fun dismiss()

    /** Whether the picker is currently showing. */
    fun isShowing(): Boolean

    /** Builder used to construct the picker. */
    class Builder(context: FragmentActivity) {

        private val viewmodel = PickerViewModel(context)

        /** Set a listener to be invoked with selected date and time values upon user completion */
        fun onDateTimeSetListener(listener: OnDateTimeSetListener) = apply {
            viewmodel.onDateTimeSetListener = listener
        }

        /** Set a listener to be invoked when the picker is shown. */
        fun onShowListener(listener: OnShowListener) = apply {
            viewmodel.onShowListener = listener
        }

        /** Set a listener to be invoked when the picker is dismissed. */
        fun onDismissListener(listener: OnDismissListener) = apply {
            viewmodel.onDismissListener = listener
        }

        /**
         * Apply custom theme styling to the picker.
         * By default, the picker uses the consumer app theme values
         * and mostly makes use of the colorAccent.
         */
        fun theme(@StyleRes themeResId: Int) = apply {
            viewmodel.themeResId = themeResId
        }

        /** Set initial picker date and time values from a Calendar instance. */
        fun initialValues(calendar: Calendar) = apply {
            initialValues(
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH],
                calendar[Calendar.HOUR_OF_DAY],
                calendar[Calendar.MINUTE]
            )
        }

        /**
         * Set initial picker date and time values.
         * By default, initialYear, initialMonth and initialDay are set to the current date;
         * initialHour and initialMinute are set to zero.
         * NB: month is zero-based, Jan is 0; Dec is 11.
         */
        fun initialValues(
            initialYear: Int? = null,
            initialMonth: Int? = null,
            initialDay: Int? = null,
            initialHour: Int? = null,
            initialMinute: Int? = null
        ) = apply {
            initialYear?.let { viewmodel.initialYear = it }
            initialMonth?.let { viewmodel.initialMonth = it }
            initialDay?.let { viewmodel.initialDay = it }
            initialHour?.let { viewmodel.initialHour = it }
            initialMinute?.let { viewmodel.initialMinute = it }
        }

        /**
         * Indicate whether to use a 24 hour view or AM/PM for the time picker.
         * By default, the time picker is set to use a 24 hour view.
         */
        fun is24HourView(is24HourView: Boolean) = apply {
            viewmodel.is24HourView = is24HourView
        }

        /**
         * Set a maximum date supported by the picker.
         * NB: month is zero-based, Jan is 0; Dec is 11.
         */
        fun maxDate(
            maxYear: Int,
            maxMonth: Int,
            maxDay: Int
        ) = apply {
            maxDate(
                Calendar.getInstance().apply {
                    set(maxYear, maxMonth, maxDay)
                }.timeInMillis
            )
        }

        /** Set a maximum date supported by the picker in milliseconds. */
        fun maxDate(maxDate: Long) = apply {
            viewmodel.maxDate = maxDate
        }

        /**
         * Set a minimum date supported by the picker.
         * NB: month is zero-based, Jan is 0; Dec is 11.
         */
        fun minDate(
            minYear: Int,
            minMonth: Int,
            minDay: Int
        ) = apply {
            minDate(
                Calendar.getInstance().apply {
                    set(minYear, minMonth, minDay)
                }.timeInMillis
            )
        }

        /** Set a minimum date supported by the picker in milliseconds. */
        fun minDate(minDate: Long) = apply {
            viewmodel.minDate = minDate
        }

        /** Construct and return an instance of DateTimePicker with the specified configuration */
        fun build(): DateTimePicker = DateTimePickerImpl(viewmodel)

    }

}