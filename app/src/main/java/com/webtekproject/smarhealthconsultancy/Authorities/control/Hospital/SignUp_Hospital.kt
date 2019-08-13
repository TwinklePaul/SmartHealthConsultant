package com.webtekproject.smarhealthconsultancy.Authorities.control.Hospital

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.DatabaseHandler
import com.webtekproject.smarhealthconsultancy.Model_Classes.Hospital_Model
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_signup_hospital.*

class SignUp_Hospital : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_hospital)

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
        val avail_beds = Integer.parseInt(edit_Bed.text.toString())
        val avail_OTs = Integer.parseInt(edit_OT.text.toString())
        val cont = Integer.parseInt(edit_Cont.text.toString())
        val pass = edit_pass.text.toString()

        val databaseHandler: DatabaseHandler =
            DatabaseHandler(this)

        if (id.trim() != " " && name.trim() != " " && location.trim() != " " && pass.trim() != " ") {

            val status =
                databaseHandler.addHospital(
                    Hospital_Model(
                        id,
                        name,
                        location,
                        avail_beds,
                        0,
                        avail_OTs,
                        0,
                        cont,
                        pass
                    )
                )


            if (status > -1) {

                val pref = getSharedPreferences("user_details", Activity.MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("userid", id)
                editor.putString("category", "Hospital")
                editor.putInt("logged", 1)
                val stat = editor.commit()

                if (stat) {
                    Toast.makeText(applicationContext, " Record Saved. ", Toast.LENGTH_LONG).show()

                    intent = Intent(this, SignIn_Hospital::class.java)
                    startActivity(intent)
                    this@SignUp_Hospital.finish()


                }
            } else {
                Toast.makeText(applicationContext, " Fields can't be blank ", Toast.LENGTH_LONG)
                    .show()

                edit_ID.text.clear()
                edit_Name.text.clear()
                edit_Loc.text.clear()
                edit_Bed.text.clear()
                edit_OT.text.clear()
                edit_Cont.text.clear()
                edit_pass.text.clear()
            }
        }
    }
}

