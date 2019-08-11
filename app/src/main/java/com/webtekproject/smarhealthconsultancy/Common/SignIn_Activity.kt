package com.webtekproject.smarhealthconsultancy.Common

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic.SignIn_Clinic
import com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic.SignUp_Clinic
import com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor.SignIn_Doctor
import com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor.SignUp_Doctor
import com.webtekproject.smarhealthconsultancy.Authorities.control.Hospital.SignIn_Hospital
import com.webtekproject.smarhealthconsultancy.Authorities.control.Hospital.SignUp_Hospital
import com.webtekproject.smarhealthconsultancy.Authorities.control.Pharmacy.SignIn_Pharmacy
import com.webtekproject.smarhealthconsultancy.Authorities.control.Pharmacy.SignUp_Pharmacy
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.Patient.SignIn_Patient
import com.webtekproject.smarhealthconsultancy.Patient.SignUp_Patient
import com.webtekproject.smarhealthconsultancy.R
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


class SignIn_Activity : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar


        //set sign in
        val mySharedPreferences = getSharedPreferences(
            "user_details",
            Activity.MODE_PRIVATE
        )
        val stat = mySharedPreferences.getInt("logged", 0)
        if (stat == 1) {
            this@SignIn_Activity.finish()
        }
        val button_signin = findViewById<Button>(R.id.button_signIn)

        button_signin.setOnClickListener {

            val userID = findViewById<TextView>(R.id.edittxt_ID).text.toString()
            val password = findViewById<TextView>(R.id.edittxt_pass).text.toString()

            val db = DatabaseHandler(this)

            val popupMenu: PopupMenu = PopupMenu(this, button_signin, Gravity.END)
            popupMenu.menuInflater.inflate(R.menu.signup_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_patient -> {
                        intent = Intent(this, SignIn_Patient::class.java)
                        startActivity(intent)
                    }

                    R.id.action_doctor -> {
                        val doc_list = db.viewDoctors()

                        for (i in doc_list) {
                            if ((i.Dr_ID).equals(userID) && (i.Dr_Current_Status).equals(1) && (i.Dr_Pass).equals(
                                    password
                                )
                            ) {

                                //found it!
                                val editor = mySharedPreferences.edit()
                                editor.putString("userid", i.Dr_ID)
                                editor.putString("category", "Doctor")
                                editor.putInt("logged", 1)
                                val stat = editor.commit()
                                if (stat) {
                                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                                        .show()
                                    intent = Intent(this, SignIn_Doctor::class.java)
                                    startActivity(intent)
                                    this@SignIn_Activity.finish()
                                }

                            } else {
                                longToast("Login UnSuccessful. Retry!")
                            }
                        }
                    }

                    R.id.action_hospital -> {
                        val hosp_list = db.viewHospital()

                        for (i in hosp_list) {
                            if ((i.Hosp_ID).equals(userID) && (i.Hosp_Pass).equals(password)) {

                                //found it!
                                val editor = mySharedPreferences.edit()
                                editor.putString("userid", i.Hosp_ID)
                                editor.putString("category", "Hospital")
                                editor.putInt("logged", 1)
                                val stat = editor.commit()
                                if (stat) {
                                    toast("Login Successful")
                                    intent = Intent(this, SignIn_Hospital::class.java)
                                    startActivity(intent)
                                    this@SignIn_Activity.finish()
                                }

                            } else {
                                longToast("Login UnSuccessful. Retry!")
                            }
                        }

                    }

                    R.id.action_clinic -> {
                        val clinic_list = db.viewClinic()

                        for (i in clinic_list) {
                            if ((i.Clinic_ID).equals(userID) && (i.Clinic_Pass).equals(password)) {

                                //found it!
                                val editor = mySharedPreferences.edit()
                                editor.putString("userid", i.Clinic_ID)
                                editor.putString("category", "Clinic")
                                editor.putInt("logged", 1)
                                val stat = editor.commit()
                                if (stat) {
                                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                                        .show()
                                    intent = Intent(this, SignIn_Clinic::class.java)
                                    startActivity(intent)
                                    this@SignIn_Activity.finish()
                                } else {
                                    longToast("Login UnSuccessful. Retry!")

                                }
                            }
                        }

                    }


                    R.id.action_pharmacy -> {
                        val pharma_list = db.viewPharmacy()

                        for (i in pharma_list) {
                            if ((i.Pharma_ID).equals(userID) && (i.Pharma_Pass).equals(password)) {

                                //found it!
                                val editor = mySharedPreferences.edit()
                                editor.putString("userid", i.Pharma_ID)
                                editor.putString("category", "Pharmacy")
                                editor.putInt("logged", 1)
                                val stat = editor.commit()
                                if (stat) {
                                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                                        .show()
                                    intent = Intent(this, SignIn_Pharmacy::class.java)
                                    startActivity(intent)
                                    this@SignIn_Activity.finish()
                                }

                            } else {
                                longToast("Login UnSuccessful. Retry!")
                            }

                        }
                    }
                }
                true
            })
            popupMenu.show()
        }


        //set sign up
        val button_signup = findViewById<Button>(R.id.button_signUp)

        button_signup.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, button_signup, Gravity.END)
            popupMenu.menuInflater.inflate(R.menu.signup_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

                when (item.itemId) {
                    R.id.action_patient -> {
                        intent = Intent(this, SignUp_Patient::class.java)
                        startActivity(intent)
                    }

                    R.id.action_doctor -> {
                        intent = Intent(this, SignUp_Doctor::class.java)
                        startActivity(intent)
                    }

                    R.id.action_hospital -> {
                        intent = Intent(this, SignUp_Hospital::class.java)
                        startActivity(intent)
                    }

                    R.id.action_clinic -> {
                        intent = Intent(this, SignUp_Clinic::class.java)
                        startActivity(intent)
                    }

                    R.id.action_pharmacy -> {
                        intent = Intent(this, SignUp_Pharmacy::class.java)
                        startActivity(intent)
                    }
                }
                true
            })
            popupMenu.show()
        }
    }
}






