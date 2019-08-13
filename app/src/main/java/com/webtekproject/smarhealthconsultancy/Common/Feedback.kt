package com.webtekproject.smarhealthconsultancy.Common

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_feedback.*

class Feedback : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.elevation = 4.0F

        val rated = findViewById<RatingBar>(R.id.rate)

        if (rated != null) {

            rate.setOnRatingBarChangeListener { ratingBar, fl, b ->
                val msg = rated.rating.toString()

                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun submit(view: View) {
        Toast.makeText(this, "Thank You For Your Valuable Feedback!", Toast.LENGTH_SHORT).show()
        this@Feedback.finish()
    }
}