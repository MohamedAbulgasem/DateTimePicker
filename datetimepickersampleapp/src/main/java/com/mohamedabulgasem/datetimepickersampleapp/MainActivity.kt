package com.mohamedabulgasem.datetimepickersampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.mohamedabulgasem.datetimepicker.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            DateTimePicker.Builder(this)
                .onDateTimeSetListener { year, month, dayOfMonth, hourOfDay, minute ->
                    setDateAndTime(year, month, dayOfMonth, hourOfDay, minute)
                }
                .is24HourView(true)
                .initialValues(
                    initialYear = 2021,
                    initialMonth = 5,
                    initialDay = 19,
                    initialHour = 18,
                    initialMinute = 30
                )
                .minDate(System.currentTimeMillis())
                .onShowListener { /* Optionally run some code when the dialog is shown */ }
                .onDismissListener { /* Optionally run some code when the dialog is dismissed */ }
                .build()
                .show()
        }
    }

    private fun setDateAndTime(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        hourOfDay: Int,
        minute: Int
    ) {
        show(
            "Year: $year\n" +
                    "Month: $month\n" +
                    "Day: $dayOfMonth\n" +
                    "Hour: $hourOfDay\n" +
                    "Minute: $minute"
        )
    }

    private fun show(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}
