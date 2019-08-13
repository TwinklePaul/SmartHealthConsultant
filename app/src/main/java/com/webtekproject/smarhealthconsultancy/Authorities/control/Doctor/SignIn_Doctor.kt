package com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.webtekproject.smarhealthconsultancy.Authorities.control.Create_Feed
import com.webtekproject.smarhealthconsultancy.Authorities.control.Profile_Page_Authority
import com.webtekproject.smarhealthconsultancy.Common.Feedback
import com.webtekproject.smarhealthconsultancy.Common.SignIn_Activity
import com.webtekproject.smarhealthconsultancy.Common.View_Feed
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.Patient.Check_Appointment
import com.webtekproject.smarhealthconsultancy.R

class SignIn_Doctor : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in__doctor)

        //Setting ActionBar
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        actionBar!!.elevation = 4.0F
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settingsmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)

        return when (item.itemId) {
            R.id.settings -> {
                intent = Intent(this, Doc_Settings::class.java)
                startActivity(intent)
                return true
            }

            R.id.logout -> {

                val editor = pref.edit()
                editor.putInt("logged", 0)
                val stat = editor.commit()
                if (stat) {
                    this@SignIn_Doctor.finish()
                    intent = Intent(this, SignIn_Activity::class.java)
                    startActivity(intent)
                }
                return true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    fun generateFeed(view: View) {
        intent = Intent(this, Create_Feed::class.java)
        startActivity(intent)
    }

    fun viewProfile(view: View) {
        intent = Intent(this, Profile_Page_Authority::class.java)
        startActivity(intent)
    }

    fun viewFeed(view: View) {
        intent = Intent(this, View_Feed::class.java)
        startActivity(intent)
    }

    fun schedule(view: View) {
        intent = Intent(this, Check_Appointment::class.java)
        startActivity(intent)
    }

    fun giveFeedback(view: View) {
        intent = Intent(this, Feedback::class.java)
        startActivity(intent)
    }


}
