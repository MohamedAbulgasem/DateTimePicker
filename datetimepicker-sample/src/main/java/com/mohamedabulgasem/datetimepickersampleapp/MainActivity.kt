package com.mohamedabulgasem.datetimepickersampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.mohamedabulgasem.datetimepicker.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dateTimePicker: DateTimePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Appointment details"
        button.setOnClickListener { showDateTimePicker() }
    }

    private fun showDateTimePicker() {
        if (dateTimePicker == null) {
            dateTimePicker = DateTimePicker.Builder(this)
                // Set a listener to be invoked with selected date and time values upon user completion.
                // NB: month is zero-based, Jan is 0; Dec is 11.
                .onDateTimeSetListener { year, month, dayOfMonth, hourOfDay, minute ->
                    scheduleAppointment(year, month, dayOfMonth, hourOfDay, minute)
                }
                // Optionally run some code when the picker is shown.
                .onShowListener { highlightAppointmentView(true) }
                // Optionally run some code when the picker is dismissed.
                .onDismissListener { highlightAppointmentView(false) }
                // Apply custom theme styling to the picker.
                // By default, the picker uses the consumer app theme values
                // and mostly makes use of the colorAccent.
                //.theme(R.style.DateTimePickerTheme)
                // Set some or all initial picker date and time values.
                // By default, initialYear, initialMonth and initialDay are set to the current date;
                // initialHour and initialMinute are set to zero.
                .initialValues(
                    initialYear = 2021,
                    initialMonth = 0,
                    initialDay = 1,
                    initialHour = 14,
                    initialMinute = 30
                )
                // OR set initial values from a Calendar instance.
                .initialValues(Calendar.getInstance())
                // Indicate whether to use a 24 hour or 12 hour AM/PM view for the time picker.
                // By default, a 24 hour view is set.
                //.is24HourView(false)
                // Optionally set a minimum date supported by the picker in milliseconds
                // or by specifying date values.
                .minDate(System.currentTimeMillis())
                // Optionally set a maximum date supported by the picker in milliseconds
                // or by specifying date values.
                .maxDate(
                    maxYear = 2022,
                    maxMonth = 11,
                    maxDay = 31
                )
                .build()
        }
        dateTimePicker?.show()
    }

    fun highlightAppointmentView(highlight: Boolean) {}

    private fun scheduleAppointment(
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

    private fun show(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

}
