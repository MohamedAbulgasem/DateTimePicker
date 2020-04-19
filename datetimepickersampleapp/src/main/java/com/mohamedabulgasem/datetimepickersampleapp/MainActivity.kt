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
                .onShowListener { /* Optionally set code to run onShow */ }
                .onDismissListener { /* Optionally set code to run onDismiss */ }
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
