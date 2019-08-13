package com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.webtekproject.smarhealthconsultancy.Authorities.control.Profile_Page_Authority
import com.webtekproject.smarhealthconsultancy.Common.SignIn_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.Model_Classes.Doc_Clinic_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_clinic_settings.*
import org.jetbrains.anko.toast

class Clinic_Settings : Base_Activity(), AdapterView.OnItemSelectedListener {

    val docName: ArrayList<String> = ArrayList<String>()
    val docspecial: ArrayList<String> = ArrayList<String>()
    val docqualif: ArrayList<String> = ArrayList<String>()

    var option_sp: String = ""
    var option_qu: String = ""
    var option_nm: String = ""

    var spinnerselect: Spinner? = null
    var spinnerselect1: Spinner? = null
    var spinnerselect2: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic_settings)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

        docName.add("Select Doctor")
        docqualif.add("Select Qualification")
        docspecial.add("Select Speciality")

        val db: DatabaseHandler = DatabaseHandler(this)
        val doc_list = db.viewDoctors()

        for (i in doc_list) {
            docspecial.add(i.Dr_Speciality)
        }

        spinnerselect = this.Speciality
        spinnerselect!!.onItemSelectedListener = this

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, docspecial)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerselect!!.adapter = aa

        spinnerselect1 = this.Qualification
        spinnerselect1!!.onItemSelectedListener = this

        val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, docqualif)
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerselect1!!.adapter = ab

        spinnerselect2 = this.Name
        spinnerselect2!!.onItemSelectedListener = this

        val ac = ArrayAdapter(this, android.R.layout.simple_spinner_item, docName)
        ac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerselect2!!.adapter = ac
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settingsmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        return when (item.itemId) {
            R.id.settings -> {
                intent = Intent(this, Profile_Page_Authority::class.java)
                startActivity(intent)
                return true
            }

            R.id.logout -> {

                val editor = pref.edit()
                editor.putInt("logged", 0)
                val stat = editor.commit()
                if (stat) {
                    this@Clinic_Settings.finish()
                    intent = Intent(this, SignIn_Activity::class.java)
                    startActivity(intent)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

        val db: DatabaseHandler = DatabaseHandler(this)
        val doc_list = db.viewDoctors()

        when (parent.id) {
            R.id.Speciality -> {
                option_sp = docspecial[position]
                toast("Showing By:${option_sp}")

                docqualif.clear()
                docqualif.add("Select Qualification")
                docName.clear()
                docName.add("Select Name")

                for (i in doc_list) {
                    if (i.Dr_Speciality.equals(option_sp))
                        docqualif.add(i.Dr_Qualification)
                }
            }

            R.id.Qualification -> {
                option_qu = docqualif[position]
                toast("Showing By:${option_qu}")

                docName.clear()
                docName.add("Select Name")

                for (i in doc_list) {
                    if (i.Dr_Speciality.equals(option_sp) && i.Dr_Qualification.equals(option_qu))
                        docName.add(i.Dr_Name)
                }
            }

            R.id.Name -> {
                option_nm = docName[position]
                toast("Selected: ${option_nm}")
            }
            else -> {

                toast("Choose Properly")
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    fun saveDoctorRecord(view: View) {
        val db: DatabaseHandler = DatabaseHandler(this)
        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)

        val doc_list = db.viewDoctors()

        for (i in doc_list) {
            if (i.Dr_Speciality.equals(option_sp) && i.Dr_Qualification.equals(option_qu) && i.Dr_Name.equals(option_nm)) {
                val status = db.addDoc_Clinic(Doc_Clinic_Model(i.Dr_ID, user!!))
                if (status > -1) {
                    toast(" Record Saved")
                    intent = Intent(this, Profile_Page_Authority::class.java)
                    startActivity(intent)
                }
                break
            } else
                toast("Record Not Found !!")
        }

    }

}