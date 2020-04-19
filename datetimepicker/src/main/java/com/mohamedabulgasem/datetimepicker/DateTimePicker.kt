package com.mohamedabulgasem.datetimepicker

import android.app.*
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.*
import androidx.fragment.app.*
import java.util.*

class DateTimePicker private constructor(
    private val builder: Builder
) {

    private var datePickerDialog: DatePickerDialog? = null
    private val timePickerDialog: TimePickerDialog? = null

    fun show() {
        showDatePicker(
            OnDateSetListener { _, year, month, dayOfMonth ->
                showTimePicker(
                    OnTimeSetListener { _, hourOfDay, minute ->
                        builder.onDateTimeSetListener?.invoke(
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
                builder.context,
                listener,
                initialYear,
                initialMonth,
                initialDay
            ).apply {
                setOnShowListener { builder.onShowListener?.invoke() }
                setOnDismissListener { builder.onDismissListener?.invoke() }
            }
        }
        datePickerDialog?.show()
    }

    private fun showTimePicker(listener: OnTimeSetListener) {
        if (timePickerDialog == null) {
            TimePickerDialog(
                builder.context,
                listener,
                initialHour,
                initialMinute,
                true
            ).apply {
                setOnDismissListener { builder.onDismissListener?.invoke() }
            }
        }
        timePickerDialog?.show()
    }

    data class Builder(
        val context: FragmentActivity,
        var onDateTimeSetListener: OnDateTimeSetListener? = null,
        var onShowListener: OnShowListener? = null,
        var onDismissListener: OnDismissListener? = null
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

        fun build() = DateTimePicker(this)

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