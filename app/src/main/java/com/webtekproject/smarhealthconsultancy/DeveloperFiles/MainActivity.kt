package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic.SignIn_Clinic
import com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor.SignIn_Doctor
import com.webtekproject.smarhealthconsultancy.Authorities.control.Hospital.SignIn_Hospital
import com.webtekproject.smarhealthconsultancy.Authorities.control.Pharmacy.SignIn_Pharmacy
import com.webtekproject.smarhealthconsultancy.Common.SignIn_Activity
import com.webtekproject.smarhealthconsultancy.Patient.SignIn_Patient

class MainActivity : AppCompatActivity() {

    private val screen_timeout: Long = 2700

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = getSharedPreferences(
            "user_details",
            Activity.MODE_PRIVATE
        )
        val cat = pref.getString("category", null)

        val stat = pref.getInt("logged", 0)

        if (stat == 1) {
            Handler().postDelayed({
                if (cat == "Doctor") {
                    val mainIntent = Intent(this@MainActivity, SignIn_Doctor::class.java)
                    this@MainActivity.startActivity(mainIntent)
                } else
                    if (cat == "Hospital") {
                        val mainIntent = Intent(this@MainActivity, SignIn_Hospital::class.java)
                        this@MainActivity.startActivity(mainIntent)
                    } else
                        if (cat == "Clinic") {
                            val mainIntent = Intent(this@MainActivity, SignIn_Clinic::class.java)
                            this@MainActivity.startActivity(mainIntent)
                        } else
                            if (cat == "Pharmacy") {
                                val mainIntent = Intent(this@MainActivity, SignIn_Pharmacy::class.java)
                                this@MainActivity.startActivity(mainIntent)
                            } else
                                if (cat == "Patient") {
                                    val mainIntent = Intent(this@MainActivity, SignIn_Patient::class.java)
                                    this@MainActivity.startActivity(mainIntent)
                                }
                this@MainActivity.finish()
            }, screen_timeout)
        } else {
            Handler().postDelayed({
                val mainIntent = Intent(this@MainActivity, SignIn_Activity::class.java)
                this@MainActivity.startActivity(mainIntent)
                this@MainActivity.finish()
            }, screen_timeout)
        }


    }


}
