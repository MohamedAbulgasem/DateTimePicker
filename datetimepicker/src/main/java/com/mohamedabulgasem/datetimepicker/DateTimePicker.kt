package com.mohamedabulgasem.datetimepicker

import android.app.*
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.*
import androidx.fragment.app.*
import java.util.*

class DateTimePicker private constructor(
    private val context: FragmentActivity,
    private var onDateTimeSetListener: OnDateTimeSetListener? = null,
    private var onShowListener: OnShowListener? = null,
    private var onDismissListener: OnDismissListener? = null
) {

    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null

    fun show() {
        showDatePicker(
            OnDateSetListener { _, year, month, dayOfMonth ->
                showTimePicker(
                    OnTimeSetListener { _, hourOfDay, minute ->
                        onDateTimeSetListener?.invoke(
                            year, month, dayOfMonth, hourOfDay, minute
                        )
                    }
                )
            }
        )
    }

    private fun showDatePicker(listener: OnDateSetListener) {
        if (datePickerDialog == null) {
            datePickerDialog = DatePickerDialog(
                context,
                listener,
                initialYear,
                initialMonth,
                initialDay
            ).apply {
                setOnShowListener { onShowListener?.invoke() }
                setOnDismissListener { onDismissListener?.invoke() }
            }
        }
        datePickerDialog?.show()
    }

    private fun showTimePicker(listener: OnTimeSetListener) {
        if (timePickerDialog == null) {
            timePickerDialog = TimePickerDialog(
                context,
                listener,
                initialHour,
                initialMinute,
                true
            ).apply {
                setOnDismissListener { onDismissListener?.invoke() }
            }
        }
        timePickerDialog?.show()
    }

    data class Builder(
        private val context: FragmentActivity,
        private var onDateTimeSetListener: OnDateTimeSetListener? = null,
        private var onShowListener: OnShowListener? = null,
        private var onDismissListener: OnDismissListener? = null
    ) {

        fun onDateTimeSetListener(listener: OnDateTimeSetListener) = apply {
            this.onDateTimeSetListener = listener
        }

        fun onShowListener(listener: OnShowListener) = apply {
            this.onShowListener = listener
        }

        fun onDismissListener(listener: OnDismissListener) = apply {
            this.onDismissListener = listener
        }

        fun build() = DateTimePicker(
            context,
            onDateTimeSetListener,
            onShowListener,
            onDismissListener
        )

    }
}

typealias OnDateTimeSetListener = (Int, Int, Int, Int, Int) -> Unit
typealias OnShowListener = () -> Unit
typealias OnDismissListener = () -> Unit

private val initialYear: Int
    get() = Calendar.getInstance().get(Calendar.YEAR)
private val initialMonth: Int
    get() = Calendar.getInstance().get(Calendar.MONTH)
private val initialDay: Int
    get() = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
private val initialHour: Int
    get() = 0
private val initialMinute: Int
    get() = 0