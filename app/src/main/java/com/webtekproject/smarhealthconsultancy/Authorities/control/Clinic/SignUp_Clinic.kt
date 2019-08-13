package com.webtekproject.smarhealthconsultancy.Authorities.control.Clinic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.Model_Classes.Clinic_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_signup_clinic.*

class SignUp_Clinic : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_clinic)


        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F
    }

    fun Save_record(view: View) {
        val id = edit_ID.text.toString()
        val name = edit_Name.text.toString()
        val location = edit_Loc.text.toString()
        val cont = Integer.parseInt(edit_Cont.text.toString())
        val pass = edit_pass.text.toString()

        val databaseHandler: DatabaseHandler =
            DatabaseHandler(this)

        if (id.trim() != " " && name.trim() != " " && location.trim() != " " && pass.trim() != " ") {
            val status =
                databaseHandler.addClinic(Clinic_Model(id, name, location, cont, pass))


            if (status > -1) {

                val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("userid", id)
                editor.putString("category", "Clinic")
                editor.putInt("logged", 1)
                val stat = editor.commit()

                if (stat) {
                    Toast.makeText(applicationContext, " Record Saved. ", Toast.LENGTH_LONG).show()

                    intent = Intent(this, SignIn_Clinic::class.java)
                    startActivity(intent)
                    this@SignUp_Clinic.finish()


                }
            } else {
                Toast.makeText(applicationContext, " Fields can't be blank ", Toast.LENGTH_LONG)
                    .show()

                edit_ID.text.clear()
                edit_Name.text.clear()
                edit_Loc.text.clear()
                edit_Cont.text.clear()
                edit_pass.text.clear()
            }
        }
    }
}
