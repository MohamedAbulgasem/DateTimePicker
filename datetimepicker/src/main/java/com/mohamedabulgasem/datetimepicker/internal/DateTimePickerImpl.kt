package com.mohamedabulgasem.datetimepicker.internal

import android.app.*
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import com.mohamedabulgasem.datetimepicker.*
import com.mohamedabulgasem.datetimepicker.models.*

internal class DateTimePickerImpl(
    private val viewmodel: PickerViewModel
) : DateTimePicker {

    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null

    override fun show() {
        showDatePicker(OnDateSetListener { _, year, month, dayOfMonth ->
            showTimePicker(OnTimeSetListener { _, hourOfDay, minute ->
                viewmodel.onDateTimeSetListener?.invoke(
                    year, month, dayOfMonth, hourOfDay, minute
                )
            })
        })
    }

    override fun dismiss() {
        datePickerDialog?.dismiss()
        timePickerDialog?.dismiss()
    }

    override fun isShowing(): Boolean {
        return datePickerDialog?.isShowing == true ||
                timePickerDialog?.isShowing == true
    }

    private fun showDatePicker(listener: OnDateSetListener) {
        if (datePickerDialog == null) {
            datePickerDialog = DatePickerDialog(
                viewmodel.context,
                viewmodel.themeResId,
                listener,
                viewmodel.initialYear,
                viewmodel.initialMonth,
                viewmodel.initialDay
            ).apply {
                setOnShowListener { viewmodel.onShowListener?.invoke() }
                setOnCancelListener { viewmodel.onDismissListener?.invoke() }
                viewmodel.maxDate?.let { datePicker.maxDate = it }
                viewmodel.minDate?.let { datePicker.minDate = it }
            }
        }
        datePickerDialog?.show()
    }

    private fun showTimePicker(listener: OnTimeSetListener) {
        timePickerDialog = TimePickerDialog(
            viewmodel.context,
            viewmodel.themeResId,
            listener,
            viewmodel.initialHour,
            viewmodel.initialMinute,
            viewmodel.is24HourView
        ).apply {
            setOnDismissListener { viewmodel.onDismissListener?.invoke() }
        }
        timePickerDialog?.show()
    }

}