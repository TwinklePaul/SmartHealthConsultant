package com.webtekproject.smarhealthconsultancy.Authorities.control.Hospital

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.webtekproject.smarhealthconsultancy.Common.ActOn_Appointment
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.AppointmentAdapter
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Appointment_Handler
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_checkappointment_clinic.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class CheckAppointment_Hospital : Base_Activity() {

    var doc_name: ArrayList<String> = ArrayList()
    var doc_cont: ArrayList<Int> = ArrayList()
    var pat_name: ArrayList<String> = ArrayList()
    var pat_cont: ArrayList<Int> = ArrayList()
    var app_id: ArrayList<String> = ArrayList()

    var doc_id: String = ""
    var pat_id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkappointment_clinic)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.elevation = 4.0F

        val db = Appointment_Handler(this)
        val app_list = db.viewrequest()

        val dh = DatabaseHandler(this)
        val doc_list = dh.viewDoctors()
        val patient_list = dh.viewPatient()

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        if (app_list.isEmpty()) {
            listapp.visibility = View.GONE
            longToast("No Appointments Yet!!")
        } else {
            org_confirm_app!!.visibility = View.GONE
        }

        for (i in app_list) {
            if (i.Org_Type.equals("Hospital") && i.Org_ID.equals(user)) {

                doc_id = i.Dr_ID
                pat_id = i.Patient_ID

                for (j in doc_list)
                    if (j.Dr_ID.equals(doc_id)) {
                        doc_name.add(j.Dr_Name)
                        doc_cont.add(j.Dr_Contact)
                    }


                for (j in patient_list)
                    if (j.Patient_ID.equals(pat_id)) {
                        pat_name.add(j.Patient_Name)
                        pat_cont.add(j.Patient_Contact)
                    }

            }
        }


        val myListAdapter = AppointmentAdapter(this, doc_name, doc_cont, pat_name, pat_cont)

        listapp.adapter = myListAdapter

        listapp.setOnItemClickListener { adapterView, view, position, id ->

            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            toast("Click on Item at $itemAtPos, its patient ID at $pat_id, its doctor Id is: $doc_id")

            intent = Intent(this, ActOn_Appointment::class.java)
            intent.putExtra("pat_id", pat_id)
            intent.putExtra("doc_id", doc_id)
            startActivity(intent)
        }

    }
}