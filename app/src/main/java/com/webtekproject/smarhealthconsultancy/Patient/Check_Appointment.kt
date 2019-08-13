package com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic

import android.app.Activity
import android.os.Bundle
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Appointment_Handler
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Schedule_Adapter
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_checkappointment_clinic.*
import org.jetbrains.anko.toast

class Check_Appointment : Base_Activity() {

    var doc_name: ArrayList<String> = ArrayList()
    var doc_cont: ArrayList<Int> = ArrayList()
    var org_name: ArrayList<String> = ArrayList()
    var org_loc: ArrayList<String> = ArrayList()
    var org_type: ArrayList<String> = ArrayList()
    var pat_name: ArrayList<String> = ArrayList()
    var app_id: ArrayList<String> = ArrayList()
    var start_at: ArrayList<String> = ArrayList()

    var doc_id: String = ""
    var pat_id: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_appointment)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.elevation = 4.0F

        val db = Appointment_Handler(this)
        val app_list = db.viewrequest()

        val dh = DatabaseHandler(this)
        val doc_list = dh.viewDoctors()
        val clinic_list = dh.viewClinic()
        val hospital_list = dh.viewHospital()
        val doc_app = dh.viewDoc_App()

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        for (i in app_list) {
            if (i.Patient_ID.equals(user)) {

                doc_id = i.Dr_ID
                app_id.add(i.App_ID)
                pat_name.add(user)

                for (j in doc_app)
                    if (j.Patient_ID.equals(user) && j.App_Id.equals(i.App_ID))
                        start_at.add(j.Start_at)


                for (j in doc_list)
                    if (j.Dr_ID.equals(doc_id)) {
                        doc_name.add(j.Dr_Name)
                        doc_cont.add(j.Dr_Contact)
                    }

                if (i.Org_Type.equals("Clinic")) {
                    for (j in clinic_list)
                        if (j.Clinic_ID.equals(user)) {
                            org_name.add(j.Clinic_Name)
                            org_loc.add(j.Clinic_Location)
                            org_type.add("Clinic")
                        }
                } else {
                    for (j in hospital_list)
                        if (j.Hosp_ID.equals(user)) {
                            org_name.add(j.Hosp_Name)
                            org_loc.add(j.Hosp_Location)
                            org_type.add("Hospital")
                        }
                }
            }
        }


        val myListAdapter = Schedule_Adapter(this, app_id, doc_name, doc_cont, pat_name, org_name, org_loc, org_type)

        listapp.adapter = myListAdapter

        listapp.setOnItemClickListener { adapterView, view, position, id ->

            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            toast("Please Be Present at Mentioned Time")

        }

    }
}