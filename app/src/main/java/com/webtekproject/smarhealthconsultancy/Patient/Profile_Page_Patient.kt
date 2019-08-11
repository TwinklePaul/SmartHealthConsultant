package com.webtekproject.smarhealthconsultancy.Patient

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.webtekproject.smarhealthconsultancy.Common.SignIn_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_profile_patient.*

class Profile_Page_Patient : Base_Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_patient)

        var pref = getSharedPreferences("user_details", MODE_PRIVATE)
        val userID = pref.getString("userid", null)
        var info: String = ""

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

        val db = DatabaseHandler(this)
        val patient = db.viewPatient()

        for (i in patient) {
            if ((i.Patient_ID).equals(userID)) {

                showID.text = i.Patient_ID
                showName.text = i.Patient_Name
                showcont.text = i.Patient_Contact.toString()
                address.text = i.Patient_Address

            }
        }

        intent = Intent(this, SignUp_Patient::class.java)
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
                intent = Intent(this, Patient_Settings::class.java)
                startActivity(intent)
                return true
            }

            R.id.logout -> {
                val editor = pref.edit()
                editor.putInt("logged", 0)
                val stat = editor.commit()
                if (stat) {
                    this@Profile_Page_Patient.finish()
                    intent = Intent(this, SignIn_Activity::class.java)
                    startActivity(intent)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}