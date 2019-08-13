package com.webtekproject.smarhealthconsultancy.Common


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.*
import com.webtekproject.smarhealthconsultancy.Model_Classes.App_Request_Model
import com.webtekproject.smarhealthconsultancy.Model_Classes.Dr_App_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_acton_appointment.*
import kotlinx.android.synthetic.main.delete_dialog.*
import org.jetbrains.anko.toast

class ActOn_Appointment : Base_Activity() {

    var appid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acton_appointment)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        val bundle: Bundle = intent.extras!!
        appid = bundle.get("app_id")!!.toString()

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

        val db = Appointment_Handler(this)
        val app_list = db.viewrequest()

        val dh = DatabaseHandler(this)
        val doc_list = dh.viewDoctors()
        val pat_list = dh.viewPatient()

        for (i in app_list)
            if (i.App_Id.equals(appid)) {
                appId.text = appid.toString()

                for (j in doc_list) {
                    if (j.Dr_ID.equals(i.Dr_ID)) {
                        dr_name.text = j.Dr_Name
                        dr_cont.text = j.Dr_Contact.toString()
                        break
                    }
                }

                for (j in pat_list) {
                    if (j.Patient_ID.equals(i.Patient_ID)) {
                        pat_name.text = j.Patient_Name
                        pat_cont.text = j.Patient_Contact.toString()
                        break
                    }
                }
                break
            }
    }

    //method for updating records based on user-id
    fun updateRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater

        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtID = dialogView.findViewById<EditText>(R.id.updateid)
        val edtName = dialogView.findViewById<EditText>(R.id.updatename)

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below: ")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
            val updateId = edtID.text.toString()
            val updateDoc = edtName.text.toString()

            val databaseHandler = Appointment_Handler(this)
            val app_list = databaseHandler.viewrequest()

            var patient: String = ""
            var org: String = ""
            var option_cat: String = ""


            for (i in app_list) {
                if (i.App_Id.equals(appid)) {
                    patient = i.Patient_ID
                    org = i.Org_ID
                    option_cat = i.Org_Type
                }
            }


            if (updateId.trim() != " " && updateDoc.trim() != " ") {
                val status =
                    databaseHandler.updaterequest(
                        App_Request_Model(
                            updateId,
                            patient,
                            updateDoc,
                            org,
                            option_cat
                        )
                    )

                if (status > -1) {
                    Toast.makeText(applicationContext, " Record Updated", Toast.LENGTH_LONG).show()
                    appid = updateId
                } else {
                    Toast.makeText(
                        applicationContext,
                        " App_Id or Doc_Id can't be blank ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener({ dialog, which ->
            finish()
        }))

        val b = dialogBuilder.create()
        b.show()
    }

    fun deleteRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater


        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById<EditText>(R.id.deleteid)

        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Confirm ID Below:")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
            deleteid.text = appid
            val databaseHandler = Appointment_Handler(this)

            val status = databaseHandler.deleterequest(App_Request_Model(appid, "", "", "", ""))

            if (status > -1) {
                Toast.makeText(applicationContext, " Record Deleted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    " Id or Email can't be blank ",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            finish()
        })

        val b = dialogBuilder.create()
        b.show()
    }


    fun toastDate(day: Int) {
        editText_date.text = day.toString()
    }

    fun date(view: View) {
        val fragmentManager = supportFragmentManager
        val popUpClock = Choose_Date()
        popUpClock.show(fragmentManager, "data")
    }

    fun toastTime(hms: Int) {
        editText_time.text = hms.toString()
    }

    fun time(view: View) {
        val fragmentManager = supportFragmentManager
        val popUpClock = Choose_Time()
        popUpClock.show(fragmentManager, "data")
    }

    fun Confirm(view: View) {
        var doc_id: String = ""
        var pat_id: String = ""
        var org_cat: String = ""
        val startat = "on: " + editText_date.text.toString() + ": @ " + editText_time.toString()
        var status: Long = -1

        val db = Appointment_Handler(this)
        val app_list = db.viewrequest()

        val dh = DatabaseHandler(this)
        val doc_list = dh.viewDoctors()
        val patient_list = dh.viewPatient()

        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        for (i in app_list)
            if (i.App_Id.equals(appid)) {
                doc_id = i.Dr_ID
                pat_id = i.Patient_ID
                org_cat = i.Org_Type
            }

        //var App_Id: String,
        //    val Patient_ID: String,
        //    val Dr_ID: String,
        //    val Start_at: String,
        //    val Clinic_ID: String,
        //    val Hosp_ID: String

        if (org_cat.equals("Clinic")) {
            status = dh.addDoc_App(Dr_App_Model(appid, pat_id, doc_id, startat, user, ""))
        } else {
            status = dh.addDoc_App(Dr_App_Model(appid, pat_id, doc_id, startat, "", user))
        }

        if (status > -1) {
            toast("record saved")
        }


    }

}
