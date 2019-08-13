/*
package com.webtekproject.smarhealthconsultancy.Authorities.control.Pharmacy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.webtekproject.smarhealthconsultancy.Authorities.control.Profile_Page_Authority
import com.webtekproject.smarhealthconsultancy.Common.SignIn_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.R

class Pharma_Settings : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharma__settings)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
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
                intent = Intent(this, Profile_Page_Authority::class.java)
                startActivity(intent)
                return true
            }

            R.id.logout -> {

                val editor = pref.edit()
                editor.putInt("logged", 0)
                val stat = editor.commit()
                if (stat) {
                    this@Pharma_Settings.finish()
                    intent = Intent(this, SignIn_Activity::class.java)
                    startActivity(intent)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}*/
