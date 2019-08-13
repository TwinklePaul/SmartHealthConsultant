package com.webtekproject.smarhealthconsultancy.Authorities.control

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic.Clinic_Settings
import com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic.SignUp_Clinic
import com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor.Doc_Settings
import com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor.SignUp_Doctor
import com.webtekproject.smarhealthconsultancy.Authorities.control.Hospital.Hospital_Settings
import com.webtekproject.smarhealthconsultancy.Authorities.control.Hospital.SignUp_Hospital
/*import com.webtekproject.smarhealthconsultancy.Authorities.control.Pharmacy.Pharma_Settings
import com.webtekproject.smarhealthconsultancy.Authorities.control.Pharmacy.SignUp_Pharmacy*/
import com.webtekproject.smarhealthconsultancy.Common.SignIn_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_profile_authority.*

class Profile_Page_Authority : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_authority)

        var pref = getSharedPreferences("user_details", MODE_PRIVATE)
        val userID = pref.getString("userid", null)
        var info: String = ""

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

        val db = DatabaseHandler(this)
        val doc_list = db.viewDoctors()
        val doc_hosp_list = db.viewDoc_Hosp()
        val doc_clinic_list = db.viewDoc_Clinic()
        val hosp_list = db.viewHospital()
        val room_list = db.viewHosp_Room()
        //val bed_list = db.viewHosp_Bed()
        val clinic_list = db.viewClinic()

        if (pref.getString("category", null) == "Doctor") {

            for (i in doc_list) {
                if ((i.Dr_ID).equals(userID) && (i.Dr_Current_Status).equals(1)) {

                    showID.text = i.Dr_ID
                    showName.text = i.Dr_Name
                    showcont.text = i.Dr_Contact.toString()
                    var hospid = ""
                    var clinicid = ""

                    var str =
                        "Speciality: " + i.Dr_Speciality + "\n\nQualification: " + i.Dr_Qualification + "\n\nHospitals Attending:\n"

                    for (j in doc_hosp_list) {
                        if ((j.Dr_ID).equals(userID)) {
                            hospid = j.Hosp_ID

                            for (k in hosp_list) {
                                if ((k.Hosp_ID).equals(hospid)) {
                                    str =
                                        str + "Name: \t" + k.Hosp_Name + " \t\tLocation: \t" + k.Hosp_Location + "\n"

                                    break
                                }
                            }
                        }
                    }

                    str = str + "\nClinics Attending:\n"

                    for (j in doc_clinic_list) {
                        if ((j.Dr_ID).equals(userID)) {
                            clinicid = j.Clinic_ID

                            for (k in clinic_list) {
                                if ((k.Clinic_ID).equals(clinicid)) {
                                    str =
                                        str + "Name: \t" + k.Clinic_Name + " \t\tLocation: \t" + k.Clinic_Location + "\n"
                                    break
                                }
                            }
                        }
                    }
                    showinfo.text = str
                    break
                }
            }
            intent = Intent(this, SignUp_Doctor::class.java)
        }

        if (pref.getString("category", null) == "Hospital") {

            for (i in hosp_list) {

                if ((i.Hosp_ID).equals(userID)) {
                    showID.text = i.Hosp_ID
                    showName.text = i.Hosp_Name
                    showcont.text = i.Hosp_Contact.toString()

                    var str =
                        "Location: " + i.Hosp_Location + "\n\nBeds::\nBooked:  " + i.Booked_Beds + " \tAvailable: " + i.Available_Beds + "\n\nOTs::\nBooked: " + i.Booked_OT + " \t Available: " + i.Available_OT + "\n\n" + "Available Rooms::\n "

                    for (j in room_list) {
                        if ((j.Hosp_ID).equals(userID)) {
                            str = str + j.Room_ID + " \t " + "Beds: " + j.Beds_Available + "\n"
                        }
                    }

                    str = str + "\n\n\nAvailable Doctors:\n"

                    for (j in doc_hosp_list) {
                        if ((j.Hosp_ID).equals(userID)) {
                            val doc = j.Dr_ID

                            for (k in doc_list) {
                                if ((k.Dr_ID).equals(doc)) {
                                    str =
                                        str + "Name: " + k.Dr_Name + "\nSpeciality: " + k.Dr_Speciality + "\nQualification: " + k.Dr_Qualification + "\nContact: " + k.Dr_Contact + "\n\n"
                                    break
                                }
                            }
                        }
                    }
                    showinfo.text = str
                }
            }

            intent = Intent(this, SignUp_Hospital::class.java)
        }

        if (pref.getString("category", null) == "Clinic") {

            for (i in clinic_list) {
                if ((i.Clinic_ID).equals(userID)) {

                    showID.text = i.Clinic_ID
                    showName.text = i.Clinic_Name
                    showcont.text = i.Clinic_Contact.toString()

                    val str =
                        "Location: " + i.Clinic_Location
                    showinfo.text = str
                    break
                }
            }
            intent = Intent(this, SignUp_Clinic::class.java)
        }

        /*if (pref.getString("category", null) == "Pharmacy") {

            val db = DatabaseHandler(this)
            val pharma_list = db.viewPharmacy()

            for (i in pharma_list) {
                if ((i.Pharma_ID).equals(userID)) {

                    showID.text = i.Pharma_ID
                    showName.text = i.Pharma_Name
                    showcont.text = i.Pharma_Contact.toString()

                    val str =
                        "Location: " + i.Pharma_Location
                    showinfo.text = str
                    break
                }
            }
            intent = Intent(this, SignUp_Pharmacy::class.java)
        }*/

        button_editTexts.setOnClickListener {
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settingsmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var pref = getSharedPreferences("user_details", MODE_PRIVATE)
        return when (item.itemId) {

            R.id.settings -> {
                if (pref.getString("category", null) == "Doctor") {
                    intent = Intent(this, Doc_Settings::class.java)
                    startActivity(intent)
                } else
                    if (pref.getString("category", null) == "Hospital") {
                        intent = Intent(this, Hospital_Settings::class.java)
                        startActivity(intent)
                    } else
                        if (pref.getString("category", null) == "Clinic") {
                            intent = Intent(this, Clinic_Settings::class.java)
                            startActivity(intent)
                        }/* else
                            if (pref.getString("category", null) == "Pharmacy") {
                                intent = Intent(this, Pharma_Settings::class.java)
                                startActivity(intent)
                            }*/
                return true
            }

            R.id.logout -> {
                val editor = pref.edit()
                editor.putInt("logged", 0)
                val stat = editor.commit()
                if (stat) {
                    this@Profile_Page_Authority.finish()
                    intent = Intent(this, SignIn_Activity::class.java)
                    startActivity(intent)
                }
                return true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

}