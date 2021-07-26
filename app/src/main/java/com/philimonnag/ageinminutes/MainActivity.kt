package com.philimonnag.ageinminutes

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener {view->
            DatePiker(view)
             }
    }
    fun DatePiker(view: View){
        val myCalendar = Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val  month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, year, month, dayOfMonth ->
                val selectedDate="$dayOfMonth/${month+1}/$year"
                selectedDateTv.setText(selectedDate)
                val sdf= SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                val ageInMinutes=theDate!!.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes=currentDate!!.time/60000
                val ages=currentDateToMinutes-ageInMinutes
                AgeInMinutesTv.setText(ages.toString())

            },year
            ,month
            ,day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }
}