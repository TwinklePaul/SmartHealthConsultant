package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.webtekproject.smarhealthconsultancy.R

class Schedule_Adapter(
    private val context: Activity,
    private val app_id: ArrayList<String>,
    private val docname: ArrayList<String>,
    private val doccont: ArrayList<Int>,
    private val patname: ArrayList<String>,
    private val orgname: ArrayList<String>,
    private val orgloc: ArrayList<String>,
    private val orgtype: ArrayList<String>,
    private val startat: ArrayList<String>
) :
    ArrayAdapter<String>(context, R.layout.layout_confirmed, docname) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.layout_confirmed, null, true)

        val id = rowView.findViewById<TextView>(R.id.appid)
        val dname = rowView.findViewById<TextView>(R.id.dr_name)
        val dcont = rowView.findViewById<TextView>(R.id.dr_cont)
        val pname = rowView.findViewById<TextView>(R.id.pat_name)
        val otype = rowView.findViewById<TextView>(R.id.text_orgCat)
        val oname = rowView.findViewById<TextView>(R.id.text_orgName)
        val olocate = rowView.findViewById<TextView>(R.id.text_orgLoc)
        val start = rowView.findViewById<TextView>(R.id.editText_date)


        id.text = app_id[position]
        dname.text = docname[position]
        dcont.text = doccont[position].toString()
        pname.text = patname[position]
        otype.text = orgtype[position]
        oname.text = orgname[position]
        olocate.text = orgloc[position]
        start.text = startat[position]

        return rowView
    }

}
