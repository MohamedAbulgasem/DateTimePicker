package com.mohamedabulgasem.datetimepicker.internal

import android.app.*
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import androidx.fragment.app.*
import com.mohamedabulgasem.datetimepicker.*
import com.mohamedabulgasem.datetimepicker.DateTimePicker.*
import com.mohamedabulgasem.datetimepicker.models.*

internal class DateTimePickerImpl(private val builder: Builder) : DateTimePicker {

    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null

    override fun show() {
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

    override fun dismiss() {
        datePickerDialog?.dismiss()
        timePickerDialog?.dismiss()
    }

    override fun isShowing(): Boolean {
        return datePickerDialog?.isShowing == true || timePickerDialog?.isShowing == true
    }

    private fun showDatePicker(listener: OnDateSetListener) {
        if (datePickerDialog == null) {
            datePickerDialog = DatePickerDialog(
                builder.context,
                builder.themeResId,
                listener,
                builder.initialYear,
                builder.initialMonth,
                builder.initialDay
            ).apply {
                setOnShowListener { builder.onShowListener?.invoke() }
                setOnCancelListener { builder.onDismissListener?.invoke() }
                builder.maxDate?.let { datePicker.maxDate = it }
                builder.minDate?.let { datePicker.minDate = it }
            }
        }
        datePickerDialog?.show()
    }

    private fun showTimePicker(listener: OnTimeSetListener) {
        if (timePickerDialog == null) {
            timePickerDialog = TimePickerDialog(
                builder.context,
                builder.themeResId,
                listener,
                builder.initialHour,
                builder.initialMinute,
                builder.is24HourView
            ).apply {
                setOnDismissListener { builder.onDismissListener?.invoke() }
            }
        }
        timePickerDialog?.show()
    }

}