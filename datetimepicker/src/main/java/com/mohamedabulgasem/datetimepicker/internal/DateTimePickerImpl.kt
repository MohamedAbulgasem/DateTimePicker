package com.mohamedabulgasem.datetimepicker.internal

import android.app.*
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import com.mohamedabulgasem.datetimepicker.*
import com.mohamedabulgasem.datetimepicker.models.*

internal class DateTimePickerImpl(
    private val pickerProps: PickerProperties
) : DateTimePicker {

    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null

    override fun show() {
        showDatePicker(
            OnDateSetListener { _, year, month, dayOfMonth ->
                showTimePicker(
                    OnTimeSetListener { _, hourOfDay, minute ->
                        pickerProps.onDateTimeSetListener?.invoke(
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
                pickerProps.context,
                pickerProps.themeResId,
                listener,
                pickerProps.initialYear,
                pickerProps.initialMonth,
                pickerProps.initialDay
            ).apply {
                setOnShowListener { pickerProps.onShowListener?.invoke() }
                setOnCancelListener { pickerProps.onDismissListener?.invoke() }
                pickerProps.maxDate?.let { datePicker.maxDate = it }
                pickerProps.minDate?.let { datePicker.minDate = it }
            }
        }
        datePickerDialog?.show()
    }

    private fun showTimePicker(listener: OnTimeSetListener) {
        if (timePickerDialog == null) {
            timePickerDialog = TimePickerDialog(
                pickerProps.context,
                pickerProps.themeResId,
                listener,
                pickerProps.initialHour,
                pickerProps.initialMinute,
                pickerProps.is24HourView
            ).apply {
                setOnDismissListener { pickerProps.onDismissListener?.invoke() }
            }
        }
        timePickerDialog?.show()
    }

}