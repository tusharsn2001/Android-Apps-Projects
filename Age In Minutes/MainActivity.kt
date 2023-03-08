package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

  private  var tvSelectedDate : TextView? = null
  private var  tvAgeInMinutes : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btn)

        tvSelectedDate = findViewById(R.id.selectedDate)
        tvAgeInMinutes = findViewById(R.id.minute1)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day =  myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd =         DatePickerDialog(this,
            { _,selectedYear,selectedMonth,selectedDayOfMonth  ->

                val selected = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.setText(selected)

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selected)
                theDate?.let{
                    val selectedDateInMinutes = theDate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time/60000
                        val diffInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text = diffInMinutes.toString()
                    }

                }

            }
            ,year,month,day
        )

       dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
       dpd.show()

    }
}


