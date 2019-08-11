package com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.Authorities.control.Profile_Page_Authority
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.Common.SignIn_Activity
import com.webtekproject.smarhealthconsultancy.Model_Classes.Doc_Clinic_Model
import com.webtekproject.smarhealthconsultancy.Model_Classes.Doc_Hosp_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_doc__settings.*
import org.jetbrains.anko.toast


class Doc_Settings : Base_Activity(), AdapterView.OnItemSelectedListener {

    val hospName: ArrayList<String> = ArrayList<String>()
    val hosploc: ArrayList<String> = ArrayList<String>()

    val clinicName: ArrayList<String> = ArrayList<String>()
    val clinicloc: ArrayList<String> = ArrayList<String>()

    var spinnerselect: Spinner? = null
    var spinnerselect1: Spinner? = null
    var spinnerselect2: Spinner? = null

    var spinneroption: Spinner? = null
    var spinneroption1: Spinner? = null
    var spinneroption2: Spinner? = null

    var hname: String = ""
    var hloc: String = ""
    var hid: String = ""
    var option: String = ""

    var cname: String = ""
    var cloc: String = ""
    var cid: String = ""
    var coption: String = ""

    var selectval = arrayOf("Select By: ", "Location", "Name")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc__settings)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

        hospName.add("Select Hospital")
        hosploc.add("Select Location")

        spinnerselect = this.SelectBy
        spinnerselect!!.setOnItemSelectedListener(this)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, selectval)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerselect!!.setAdapter(aa)

        spinnerselect1 = this.SelectH1
        spinnerselect1!!.setOnItemSelectedListener(this)

        val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, hospName)
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerselect1!!.setAdapter(ab)

        spinnerselect2 = this.SelectH2
        spinnerselect2!!.setOnItemSelectedListener(this)

        val ac = ArrayAdapter(this, android.R.layout.simple_spinner_item, hosploc)
        ac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerselect2!!.setAdapter(ac)



        clinicName.add("Select Clinic")
        clinicloc.add("Select Location")

        spinneroption = this.Option
        spinneroption!!.setOnItemSelectedListener(this)

        val ba = ArrayAdapter(this, android.R.layout.simple_spinner_item, selectval)
        ba.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinneroption!!.setAdapter(ba)

        spinneroption1 = this.Option1
        spinneroption1!!.setOnItemSelectedListener(this)

        val bb = ArrayAdapter(this, android.R.layout.simple_spinner_item, clinicName)
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinneroption1!!.setAdapter(bb)

        spinneroption2 = this.Option2
        spinneroption2!!.setOnItemSelectedListener(this)

        val bc = ArrayAdapter(this, android.R.layout.simple_spinner_item, clinicloc)
        bc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinneroption2!!.setAdapter(bc)

    }

    fun saveHospitalRecord(view: View) {
        val db: DatabaseHandler = DatabaseHandler(this)
        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)
        var flag = 0


        val doc_hosp_list = db.viewDoc_Hosp()

        for (i in doc_hosp_list) {
            if (i.Dr_ID.equals(user) && i.Hosp_ID.equals(hid)) {
                Toast.makeText(applicationContext, " Hospital Record Exists. Retry", Toast.LENGTH_LONG).show()
                flag = 1
                break
            }
        }

        if (flag == 0) {
            val status = db.addDoc_Hosp(Doc_Hosp_Model(user!!, hid))
            if (status > -1) {
                Toast.makeText(applicationContext, " Record Saved", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun saveClinicRecord(view: View) {
        val db: DatabaseHandler = DatabaseHandler(this)
        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
        val user = pref.getString("userid", null)
        var cflag = 0

        val doc_clinic_list = db.viewDoc_Clinic()


        for (i in doc_clinic_list) {
            if (i.Dr_ID.equals(user) && i.Clinic_ID.equals(cid)) {
                Toast.makeText(applicationContext, "Clinic Record Exists. Retry", Toast.LENGTH_LONG).show()
                cflag = 1
                break
            }
        }

        if (cflag == 0) {
            val status = db.addDoc_Clinic(Doc_Clinic_Model(user!!, cid))
            if (status > -1) {
                Toast.makeText(applicationContext, " Record Saved", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

        val db: DatabaseHandler = DatabaseHandler(this)
        val hosp_list = db.viewHospital()
        val clinic_list = db.viewClinic()

        when (parent?.id) {
            R.id.SelectBy -> {
                option = selectval[position]
                toast("Showing By:${option}")

                if (option.equals("Name")) {

                    hospName.clear()
                    hospName.add("Select Name")
                    hosploc.clear()
                    hosploc.add("Select Location")

                    for (i in hosp_list) {
                        hospName.add(i.Hosp_Name)
                    }

                    spinnerselect = this.SelectH1
                    spinnerselect!!.setOnItemSelectedListener(this)

                    val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, hospName)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    spinnerselect!!.setAdapter(aa)

                } else

                    if (option.equals("Location")) {

                        hospName.clear()
                        hospName.add("Select Name")
                        hosploc.clear()
                        hosploc.add("Select Location")

                        for (i in hosp_list) {

                            hosploc.add(i.Hosp_Location)
                        }

                        spinnerselect = this.SelectH1
                        spinnerselect!!.setOnItemSelectedListener(this)

                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, hosploc)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        spinnerselect!!.setAdapter(aa)

                    } else {
                        hospName.clear()
                        hospName.add("Select Name")
                        hosploc.clear()
                        hosploc.add("Select Location")
                        toast("Choose a Correct Option")
                    }

            }

            R.id.SelectH1 -> {

                if (option.equals("Name")) {
                    hname = hospName[position]
                    toast("Showing By:${hname}")

                    hosploc.clear()
                    hosploc.add("Select Location")

                    for (i in hosp_list) {
                        if ((i.Hosp_Name).equals(hname)) {
                            hosploc.add(i.Hosp_Location)
                        }
                    }

                    spinnerselect = this.SelectH2
                    spinnerselect!!.setOnItemSelectedListener(this)

                    val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, hosploc)
                    ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    spinnerselect!!.setAdapter(ab)

                } else {
                    if (option.equals("Location")) {
                        hloc = hosploc[position]
                        toast("Showing By:${hloc}")

                        hospName.clear()
                        hospName.add("Select Name")

                        for (i in hosp_list) {
                            if ((i.Hosp_Location).equals(hloc)) {
                                hospName.add(i.Hosp_Name)
                            }
                        }

                        spinnerselect = this.SelectH2
                        spinnerselect!!.setOnItemSelectedListener(this)

                        val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, hospName)
                        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        spinnerselect!!.setAdapter(ab)

                    }
                }
            }

            R.id.SelectH2 -> {

                if (option.equals("Name")) {
                    hloc = hosploc[position]
                    toast("Choosing:${hloc}")
                } else {
                    if (option.equals("Location")) {
                        toast("Choosing:${hname}")
                        hname = hospName[position]
                    }
                }

                for (i in hosp_list) {
                    if ((i.Hosp_Name).equals(hname) && (i.Hosp_Location).equals((hloc))) {
                        hid = i.Hosp_ID
                        break
                    }
                }
            }


            R.id.Option -> {
                coption = selectval[position]
                toast("Showing By:${option}")

                if (coption.equals("Name")) {

                    clinicName.clear()
                    clinicName.add("Select Name")
                    clinicloc.clear()
                    clinicloc.add("Select Location")

                    for (i in clinic_list) {
                        clinicName.add(i.Clinic_Name)
                    }

                    spinneroption1 = this.Option1
                    spinneroption1!!.setOnItemSelectedListener(this)

                    val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, clinicName)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    spinneroption1!!.setAdapter(aa)

                } else

                    if (coption.equals("Location")) {

                        clinicName.clear()
                        clinicName.add("Select Name")
                        clinicloc.clear()
                        clinicloc.add("Select Location")

                        for (i in clinic_list) {
                            clinicloc.add(i.Clinic_Location)
                        }

                        spinneroption1 = this.Option1
                        spinneroption1!!.setOnItemSelectedListener(this)

                        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, clinicloc)
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        spinneroption1!!.setAdapter(aa)

                    } else {
                        clinicName.clear()
                        clinicName.add("Select Name")
                        clinicloc.clear()
                        clinicloc.add("Select Location")
                        toast("Choose a Correct Option")
                    }
            }

            R.id.Option1 -> {

                if (coption.equals("Name")) {
                    cname = clinicName[position]
                    toast("Showing By:${cname}")

                    clinicloc.clear()
                    clinicloc.add("Select Location")

                    for (i in clinic_list) {
                        if ((i.Clinic_Name).equals(cname)) {
                            clinicloc.add(i.Clinic_Location)
                        }
                    }

                    spinneroption2 = this.Option2
                    spinneroption2!!.setOnItemSelectedListener(this)

                    val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, clinicloc)
                    ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    spinneroption2!!.setAdapter(ab)

                } else {
                    if (coption.equals("Location")) {
                        cloc = clinicloc[position]
                        toast("Showing By:${cloc}")

                        clinicName.clear()
                        clinicName.add("Select Name")

                        for (i in clinic_list) {
                            if ((i.Clinic_Location).equals(cloc)) {
                                clinicName.add(i.Clinic_Name)
                            }
                        }

                        spinneroption2 = this.Option2
                        spinneroption2!!.setOnItemSelectedListener(this)

                        val ab = ArrayAdapter(this, android.R.layout.simple_spinner_item, clinicName)
                        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        spinneroption2!!.setAdapter(ab)

                    }
                }
            }

            R.id.Option2 -> {

                if (coption.equals("Name")) {
                    cloc = clinicloc[position]
                    toast("Choosing:${cloc}")
                } else {
                    if (coption.equals("Location")) {
                        toast("Choosing:${cname}")
                        cname = clinicName[position]
                    }
                }

                for (i in clinic_list) {
                    if ((i.Clinic_Name).equals(cname) && (i.Clinic_Location).equals((cloc))) {
                        cid = i.Clinic_ID
                        break
                    }
                }
            }

            else -> {

                toast("Choose Properly")
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}


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
                    this@Doc_Settings.finish()
                    intent = Intent(this, SignIn_Activity::class.java)
                    startActivity(intent)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
