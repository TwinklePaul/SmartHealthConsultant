package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.webtekproject.smarhealthconsultancy.Common.ActOn_Appointment
import com.webtekproject.smarhealthconsultancy.R

class Choose_Time : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_choose__time, container, false)
        val buTime = view.findViewById<Button>(R.id.button_time)
        val chooseTime: TimePicker = view.findViewById(R.id.ChooseTime)
        buTime.setOnClickListener {
            this@Choose_Time.dismiss()
            val time: Int = chooseTime.minute
            val actonAppointment = activity as ActOn_Appointment
            actonAppointment.toastTime(time)
        }
        return view
    }
}