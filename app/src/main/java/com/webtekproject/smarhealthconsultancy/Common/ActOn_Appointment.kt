package com.webtekproject.smarhealthconsultancy.Common


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.*
import com.webtekproject.smarhealthconsultancy.Model_Classes.Dr_App_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_acton_appointment.*
import org.jetbrains.anko.toast

class ActOn_Appointment : Base_Activity(), AdapterView.OnItemSelectedListener {


    var spinner: Spinner? = null
    var doc_name: ArrayList<String> = ArrayList()
    var patient: String = ""
    var docID: String = ""
    var patID: String = ""
    var org: String = ""
    var option_cat: String = ""
    var edtName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acton_appointment)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

        val bundle: Bundle = intent.extras!!
        patID = bundle.get("pat_id")!!.toString()
        docID = bundle.get("doc_id")!!.toString()

        toast(patID + docID)

        val db = Appointment_Handler(this)
        val app_list = db.viewrequest()

        val dh = DatabaseHandler(this)
        val doc_list = dh.viewDoctors()
        val pat_list = dh.viewPatient()
        val doc_clinic = dh.viewDoc_Clinic()
        val doc_hosp = dh.viewDoc_Hosp()

        doc_name.add("Choose Doc")

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        for (i in app_list)
            if (i.Org_Type.equals("Clinic") && i.Dr_ID.equals(docID) && i.Patient_ID.equals(patID)) {
                option_cat = "Clinic"
                for (x in doc_clinic) {
                    if (x.Dr_ID.equals(i.Dr_ID) && x.Clinic_ID.equals(i.Org_ID) && i.Org_ID.equals(
                            user!!
                        )
                    ) {
                        for (j in doc_list) {
                            if (j.Dr_ID.equals(docID)) {
                                dr_name.text = j.Dr_Name
                                dr_cont.text = j.Dr_Contact.toString()
                                break
                            }
                        }

                        for (j in pat_list) {
                            if (j.Patient_ID.equals(patID)) {
                                pat_name.text = j.Patient_Name
                                pat_cont.text = j.Patient_Contact.toString()
                                break
                            }
                        }
                    }
                }
            } else {
                if (i.Dr_ID.equals(docID) && i.Patient_ID.equals(patID)) {
                    option_cat = "Hospital"
                    for (x in doc_hosp) {
                        if (x.Dr_ID.equals(i.Dr_ID) && x.Hosp_ID.equals(i.Org_ID) && i.Org_ID.equals(
                                user!!
                            )
                        ) {
                            for (j in doc_list) {
                                if (j.Dr_ID.equals(docID)) {
                                    dr_name.text = j.Dr_Name
                                    dr_cont.text = j.Dr_Contact.toString()
                                    break
                                }
                            }

                            for (j in pat_list) {
                                if (j.Patient_ID.equals(patID)) {
                                    pat_name.text = j.Patient_Name
                                    pat_cont.text = j.Patient_Contact.toString()
                                    break
                                }
                            }
                        }
                    }
                }
            }
    }
/*
    //method for updating records based on user-id
    fun updateRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater

        val db = DatabaseHandler(this)
        val ah = Appointment_Handler(this)

        val doc_clinic = db.viewDoc_Clinic()
        val doc_list = db.viewDoctors()
        val doc_hosp = db.viewDoc_Hosp()
        val app_list = ah.viewrequest()

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        if (option_cat.equals("Clinic")) {
            for (i in doc_clinic)
                if (i.Clinic_ID.equals(user!!))
                    for (j in doc_list)
                        if (j.Dr_ID.equals(i.Dr_ID))
                            doc_name.add(j.Dr_Name)
        } else {
            for (i in doc_hosp)
                if (i.Hosp_ID.equals(user!!))
                    for (j in doc_list)
                        if (j.Dr_ID.equals(i.Dr_ID))
                            doc_name.add(j.Dr_Name)
        }


        spinner = this.ChooseDoc
        spinner!!.onItemSelectedListener = this

        val name = ArrayAdapter(this, android.R.layout.simple_spinner_item, doc_name)
        name.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner!!.adapter = name

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below: ")

        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
            var updateDoc: String = ""
            for (i in doc_list)
                if (edtName.equals(i.Dr_Name))
                    if (option_cat.equals("Clinic")) {
                        for (j in doc_clinic)
                            if (j.Clinic_ID.equals(user!!))
                                if (i.Dr_ID.equals(j.Dr_ID))
                                    updateDoc = i.Dr_ID
                    } else {
                        for (j in doc_hosp)
                            if (j.Hosp_ID.equals(user!!))
                                if (i.Dr_ID.equals(j.Dr_ID))
                                    updateDoc = i.Dr_ID
                    }

            for (i in app_list) {
                patient = i.Patient_ID
                org = i.Org_ID
                option_cat = i.Org_Type

            }

            val status =
                ah.updaterequest(
                    App_Request_Model(
                        patient,
                        updateDoc,
                        org,
                        option_cat
                    )
                )

            if (status > -1) {
                Toast.makeText(applicationContext, " Record Updated", Toast.LENGTH_LONG).show()
                //appid = updateId
            } else {
                Toast.makeText(
                    applicationContext,
                    " Choose proper Doctor Name ",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        dialogBuilder.setNegativeButton(
            "Cancel", DialogInterface.OnClickListener(
                { dialog, which ->
                    finish()
                })
        )

        val b = dialogBuilder.create()
        b.show()
    }

    fun deleteRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val ah = Appointment_Handler(this)
        val db = DatabaseHandler(this)
        val app_list = ah.viewrequest()
        val pat_list = db.viewPatient()
        val doc_list = db.viewDoctors()


        //val dltId = dialogView.findViewById<EditText>(R.id.deleteid)

        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Confirm ID Below:")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->

            for (i in app_list)
                if (i.Patient_ID.equals(patID) && i.Dr_ID.equals(docID)) {
                    for (j in pat_list)
                        if (j.Patient_ID.equals(patID))
                            Delpatient.text = j.Patient_Name
                    for (j in doc_list)
                        if (j.Dr_ID.equals(docID))
                            Deldoctor.text = j.Dr_Name
                }


            val status = ah.deleterequest(App_Request_Model(patID, docID, user, ""))

            if (status > -1) {
                Toast.makeText(applicationContext, " Record Deleted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    " Error ",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            finish()
        })

        val b = dialogBuilder.create()
        b.show()
    }*/


    fun toastDate(date: String) {
        editText_date.text = date
    }

    fun date(view: View) {
        val fragmentManager = supportFragmentManager
        val popUpDate = Choose_Date()
        popUpDate.show(fragmentManager, "data")
    }

    fun toastTime(hms: String) {
        editText_time.text = hms
    }

    fun time(view: View) {
        val fragmentManager = supportFragmentManager
        val popUpClock = Choose_Time()
        popUpClock.show(fragmentManager, "data")
    }

    fun Confirm(view: View) {
        val tm = editText_time.text.toString()
        val dt = editText_date.text.toString()
        val startat = "on: $dt : @ $tm"

        var status: Long = -1
        val Appid = appId.text.toString()

        val dh = DatabaseHandler(this)
        /*val ah= Appointment_Handler (this)

        val appointments =ah.viewrequest()*/

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        if (option_cat.equals("Clinic")) {
            status = dh.addDoc_App(Dr_App_Model(Appid, patID, docID, startat, user!!, ""))
        } else {
            status = dh.addDoc_App(Dr_App_Model(Appid, patID, docID, startat, "", user!!))
        }

        if (status > -1) {
            // toast("record saved")
            toast(startat)
            /*for (i in appointments)
                if (i.Patient_ID.equals(patID) && i.Dr_ID.equals(docID)) {
                    // val stat = ah.deleterequest(App_Request_Model(patID, docID, user, ""))
                }*/

            intent = Intent(this, SignIn_Activity::class.java)
            startActivity(intent)
        } else
            toast("Error")


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.ChooseDoc -> {
                edtName = doc_name[position]
            }
            else -> {
                toast("Choose Properly")
            }
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}


}
