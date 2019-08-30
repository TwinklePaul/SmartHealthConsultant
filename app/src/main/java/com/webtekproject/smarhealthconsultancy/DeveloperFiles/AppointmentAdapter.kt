package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.webtekproject.smarhealthconsultancy.R

class AppointmentAdapter(
    private val context: Activity,
    private val docname: ArrayList<String>,
    private val doccont: ArrayList<Int>,
    private val patname: ArrayList<String>,
    private val patcont: ArrayList<Int>
) :
    ArrayAdapter<String>(context, R.layout.layout_appointment, docname) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.layout_appointment, null, true)


        val dname = rowView.findViewById<TextView>(R.id.dr_name)
        val dcont = rowView.findViewById<TextView>(R.id.dr_cont)
        val pname = rowView.findViewById<TextView>(R.id.pat_name)
        val pcont = rowView.findViewById<TextView>(R.id.pat_cont)

        dname.text = docname[position]
        dcont.text = doccont[position].toString()
        pname.text = patname[position]
        pcont.text = patcont[position].toString()

        return rowView
    }

}
