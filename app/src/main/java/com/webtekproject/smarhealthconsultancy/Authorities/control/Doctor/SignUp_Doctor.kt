package com.webtekproject.smarhealthconsultancy.Authorities.control.Doctor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.Model_Classes.Doctor_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_signup_doctor.*
import org.jetbrains.anko.toast

class SignUp_Doctor : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_doctor)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F
    }


    fun Save_record(view: View) {
        val id = edit_ID.text.toString()
        val name = edit_Name.text.toString()
        val speciality = edit_Speciality.text.toString()
        val qualify = edit_Qualification.text.toString()
        val cont = Integer.parseInt(edit_Cont.text.toString())
        val pass = edit_pass.text.toString()

        val databaseHandler: DatabaseHandler =
            DatabaseHandler(this)

        if (id.trim() != " " && name.trim() != " " && speciality.trim() != " " && qualify.trim() != " " && pass.trim() != " ") {
            val status = databaseHandler.addDoctor(Doctor_Model(id, name, 1, speciality, qualify, cont, pass))

            if (status > -1) {
                toast(" Record Saved").show()

                val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("userid", id)
                editor.putString("category", "Doctor")
                editor.putInt("logged", 1)
                val stat = editor.commit()

                if (stat) {
                    intent = Intent(this, SignIn_Doctor::class.java)
                    startActivity(intent)
                    this@SignUp_Doctor.finish()
                }

            } else {
                toast (" Fields can't be blank ")

                edit_ID.text.clear()
                edit_Name.text.clear()
                edit_Speciality.text.clear()
                edit_Qualification.text.clear()
                edit_Cont.text.clear()
                edit_pass.text.clear()
            }
        }
    }
}