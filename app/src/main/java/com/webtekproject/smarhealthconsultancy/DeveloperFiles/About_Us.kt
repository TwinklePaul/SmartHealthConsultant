package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.os.Bundle
import com.webtekproject.smarhealthconsultancy.R

class About_Us : Base_Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.elevation = 4.0F
    }
}