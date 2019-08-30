package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.webtekproject.smarhealthconsultancy.Common.ActOn_Appointment
import com.webtekproject.smarhealthconsultancy.R

class Choose_Date : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_date_time, container, false)

        val buDate = view.findViewById<Button>(R.id.button_date)

        val chooseDate: DatePicker = view.findViewById(R.id.Choosedate)

        buDate.setOnClickListener {
            this@Choose_Date.dismiss()
            val day: Int = chooseDate.dayOfMonth
            val month = chooseDate.month
            val year = chooseDate.year

            val date: String = "$day/$month/$year"

            val actonAppointment = activity as ActOn_Appointment
            actonAppointment.toastDate(date)
        }
        return view

    }
}