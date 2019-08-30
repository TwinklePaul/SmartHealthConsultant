package com.webtekproject.smarhealthconsultancy.Patient

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Appointment_Handler
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.Model_Classes.App_Request_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_patientappointment.*
import org.jetbrains.anko.toast

class Get_Appointment : Base_Activity(), AdapterView.OnItemSelectedListener {

    var doc_spl: ArrayList<String> = ArrayList()
    val org_option = arrayOf<String>("Choose By: ", "Clinic", "Hospital")
    var org_loc: ArrayList<String> = ArrayList()
    var doc_qualif: ArrayList<String> = ArrayList()
    var doc_name: ArrayList<String> = ArrayList()
    var org_name: ArrayList<String> = ArrayList()

    var option_spl: String = ""
    var option_cat: String = ""
    var option_loc: String = ""
    var option_qualif: String = ""
    var option_name: String = ""
    var option_org: String = ""
    var doc: String = ""
    var org: String = ""

    var spinner: Spinner? = null
    var spinner1: Spinner? = null
    var spinner2: Spinner? = null
    var spinner3: Spinner? = null
    var spinner4: Spinner? = null
    var spinner5: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patientappointment)
        //Setting ActionBar

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val db = DatabaseHandler(this)
        val doc_list = db.viewDoctors()

        doc_spl.add("Choose Specialty: ")
        org_loc.add("Choose Location: ")
        doc_qualif.add("Choose Qualification: ")
        doc_name.add("Choose Doctor: ")
        org_name.add("Choose Organisation:")

        for (i in doc_list) {
            doc_spl.add(i.Dr_Speciality)
        }

        // Set toolbar title/app title

        //Category Spinner SetUp
        spinner = this.ChooseSpecialty
        spinner!!.onItemSelectedListener = this

        val spl = ArrayAdapter(this, android.R.layout.simple_spinner_item, doc_spl)
        spl.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner!!.adapter = spl


        spinner1 = this.ChooseLocation
        spinner1!!.onItemSelectedListener = this

        val loc = ArrayAdapter(this, android.R.layout.simple_spinner_item, org_loc)
        loc.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner1!!.adapter = loc


        spinner2 = this.Organisation
        spinner2!!.onItemSelectedListener = this

        val org = ArrayAdapter(this, android.R.layout.simple_spinner_item, org_option)
        org.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner2!!.adapter = org


        spinner3 = this.ChooseQualification
        spinner3!!.onItemSelectedListener = this

        val qualif = ArrayAdapter(this, android.R.layout.simple_spinner_item, doc_qualif)
        qualif.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner3!!.adapter = qualif


        spinner4 = this.ChooseName
        spinner4!!.onItemSelectedListener = this

        val name = ArrayAdapter(this, android.R.layout.simple_spinner_item, doc_name)
        name.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner4!!.adapter = name

        spinner5 = this.ChooseOrg
        spinner5!!.onItemSelectedListener = this

        val org_name = ArrayAdapter(this, android.R.layout.simple_spinner_item, org_name)
        org_name.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner5!!.adapter = org_name

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val db = DatabaseHandler(this)
        val hosp_list = db.viewHospital()
        val clinic_list = db.viewClinic()
        val doc_list = db.viewDoctors()
        val doc_clinic = db.viewDoc_Clinic()
        val doc_hosp = db.viewDoc_Hosp()
        var drflag = 0
        var orgid: String = ""

        when (parent?.id) {
            R.id.ChooseSpecialty -> {

                org_loc.clear()
                org_loc.add("Choose Location: ")
                doc_qualif.clear()
                doc_qualif.add("Choose Qualification: ")
                doc_name.clear()
                doc_name.add("Choose Doctor: ")
                org_name.clear()
                org_name.add("Choose Organisation:")

                option_spl = doc_spl[position]

                for (i in doc_list) {
                    if (i.Dr_Speciality.equals(option_spl)) {

                        for (j in doc_clinic) {
                            if (i.Dr_ID.equals(j.Dr_ID)) {
                                orgid = j.Clinic_ID

                                for (k in clinic_list) {
                                    if (k.Clinic_ID.equals(orgid)) {
                                        org_loc.add(k.Clinic_Location)
                                    }
                                }
                            }
                        }

                        for (j in doc_hosp) {
                            if (i.Dr_ID.equals(j.Dr_ID)) {
                                orgid = j.Hosp_ID

                                for (k in hosp_list) {
                                    if (k.Hosp_ID.equals(orgid)) {
                                        org_loc.add(k.Hosp_Location)
                                    }
                                }
                            }
                        }
                    }
                }

                spinner1 = this.ChooseLocation
                spinner1!!.onItemSelectedListener = this

                val loc = ArrayAdapter(this, android.R.layout.simple_spinner_item, org_loc)
                loc.setDropDownViewResource(android.R.layout.simple_spinner_item)
                spinner1!!.adapter = loc

                if (drflag == 0)
                    toast("Doctors Unavailable")
                else
                    toast("Showing By:${option_spl}")
            }

            R.id.ChooseLocation -> {

                doc_qualif.clear()
                doc_qualif.add("Choose Qualification: ")
                doc_name.clear()
                doc_name.add("Choose Doctor: ")

                option_loc = org_loc[position]
                toast("Showing By:${option_loc}")
            }

            R.id.Organisation -> {
                doc_qualif.clear()
                doc_qualif.add("Choose Qualification: ")

                option_cat = org_option[position]
                toast("Showing By:${option_cat}")

                if (option_cat.equals("Clinic")) {
                    for (i in clinic_list) {
                        if (i.Clinic_Location.equals(option_loc)) {

                            for (j in doc_clinic) {
                                if (i.Clinic_ID.equals(j.Clinic_ID)) {

                                    for (k in doc_list) {
                                        if (k.Dr_ID.equals(j.Dr_ID)) {
                                            doc_qualif.add(k.Dr_Qualification)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                if (option_cat.equals("Hospital")) {
                    for (i in hosp_list)
                        if (i.Hosp_Location.equals(option_loc))

                            for (j in doc_hosp)
                                if (i.Hosp_ID.equals(j.Hosp_ID))

                                    for (k in doc_list)
                                        if (k.Dr_ID.equals(j.Dr_ID))
                                            doc_qualif.add(k.Dr_Qualification)
                }

                spinner3 = this.ChooseQualification
                spinner3!!.onItemSelectedListener = this

                val qualif = ArrayAdapter(this, android.R.layout.simple_spinner_item, doc_qualif)
                qualif.setDropDownViewResource(android.R.layout.simple_spinner_item)
                spinner3!!.adapter = qualif
            }

            R.id.ChooseQualification -> {

                doc_name.clear()
                doc_name.add("Choose Doctor: ")

                option_qualif = doc_qualif[position]
                toast("Showing By:${option_cat}")

                for (i in doc_list)
                    if (i.Dr_Qualification.equals(option_qualif))
                        doc_name.add(i.Dr_Name)

                spinner4 = this.ChooseName
                spinner4!!.onItemSelectedListener = this

                val name = ArrayAdapter(this, android.R.layout.simple_spinner_item, doc_name)
                name.setDropDownViewResource(android.R.layout.simple_spinner_item)
                spinner4!!.adapter = name
            }

            R.id.ChooseName -> {

                org_name.clear()
                org_name.add("Choose Organisation:")

                option_name = doc_name[position]
                toast("Chosen: Dr.${option_name}")
                for (i in doc_list)
                    if (i.Dr_Name.equals(option_name))
                        doc = i.Dr_ID

                if (option_cat.equals("Clinic")) {
                    for (i in doc_clinic)
                        if (i.Dr_ID.equals(doc))
                            for (j in clinic_list)
                                if (j.Clinic_ID.equals(i.Clinic_ID))
                                    org_name.add(j.Clinic_Name)
                } else
                    for (i in doc_hosp)
                        if (i.Dr_ID.equals(doc))
                            for (j in hosp_list)
                                if (j.Hosp_ID.equals(i.Hosp_ID))
                                    org_name.add(j.Hosp_Name)

                spinner5 = this.ChooseOrg
                spinner5!!.onItemSelectedListener = this

                val orgname = ArrayAdapter(this, android.R.layout.simple_spinner_item, org_name)
                orgname.setDropDownViewResource(android.R.layout.simple_spinner_item)
                spinner5!!.adapter = orgname
            }

            R.id.ChooseOrg -> {
                option_org = org_name[position]
                toast("Chosen: .${option_org}")

                if (option_cat.equals("Clinic")) {
                    for (i in clinic_list)
                        if (i.Clinic_Name.equals(option_org))
                            org = i.Clinic_ID

                } else
                    for (i in hosp_list)
                        if (i.Hosp_Name.equals(option_org))
                            org = i.Hosp_ID

            }
            else -> {
                toast("Choose Properly")
            }
        }

    }

    fun bookApp(view: View?) {

        val db = Appointment_Handler(this)
        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)
        //val visitnum = visit.text.toString()
        //val app_id = "${user}_${doc}_${org}_${visitnum}"
        //toast(app_id)

        val status = db.addRequest(App_Request_Model(user!!, doc, org, option_cat))
        //toast(user +" " +doc +" "+ org)

        if (status > -1) {
            toast("Request For Your Appointment Has Been Sent To: $option_org, $option_cat")
            intent = Intent(this, SignIn_Patient::class.java)
            startActivity(intent)
        } else
            toast("Sorry!! Some Error Occured. Please Retry!")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}