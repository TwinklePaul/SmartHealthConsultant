package com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Schedule_Adapter
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_check_appointment.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class CheckAppointment_Doctor : Base_Activity() {

    var doc_name: ArrayList<String> = ArrayList()
    var pat_cont: ArrayList<Int> = ArrayList()
    var org_name: ArrayList<String> = ArrayList()
    var org_loc: ArrayList<String> = ArrayList()
    var org_type: ArrayList<String> = ArrayList()
    var pat_name: ArrayList<String> = ArrayList()
    var app_id: ArrayList<String> = ArrayList()
    var start_at: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_appointment)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.elevation = 4.0F

        val dh = DatabaseHandler(this)
        val pat_list = dh.viewPatient()
        val clinic_list = dh.viewClinic()
        val hospital_list = dh.viewHospital()
        val app_list = dh.viewDoc_App()

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        if (app_list.isEmpty()) {
            listconfirmed.visibility = View.GONE
            longToast("No Appointments Yet!!")
        } else {
            doc_confirm_app!!.visibility = View.GONE
        }


        for (i in app_list) {
            if (i.Dr_ID.equals(user)) {
                app_id.add(i.App_Id)
                doc_name.add(user)
                start_at.add(i.Start_at)

                for (j in pat_list)
                    if (j.Patient_ID.equals(i.Patient_ID)) {
                        pat_name.add(i.Patient_ID)
                        pat_cont.add(j.Patient_Contact)
                    }



                if (i.Hosp_ID.equals("")) {
                    for (j in clinic_list)
                        if (j.Clinic_ID.equals(i.Clinic_ID)) {
                            org_name.add(j.Clinic_Name)
                            org_loc.add(j.Clinic_Location)
                            org_type.add("Clinic")
                        }
                } else {
                    for (j in hospital_list)
                        if (j.Hosp_ID.equals(j.Hosp_ID)) {
                            org_name.add(j.Hosp_Name)
                            org_loc.add(j.Hosp_Location)
                            org_type.add("Hospital")
                        }
                }
            }
        }


        val myListAdapter =
            Schedule_Adapter(
                this,
                app_id,
                doc_name,
                pat_cont,
                pat_name,
                org_name,
                org_loc,
                org_type,
                start_at
            )

        listconfirmed.adapter = myListAdapter

        listconfirmed.setOnItemClickListener { adapterView, view, position, id ->

            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            toast("Please Be Present at Mentioned Time")

        }

    }
}