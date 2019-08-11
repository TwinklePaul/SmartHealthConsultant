package com.webtekproject.smarhealthconsultancy.Authorities.control

import android.os.Bundle
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.layout_feedpost.*

class Create_Feed : Base_Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_feedpost)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.elevation = 4.0F

        var bundle = intent.extras!!
        val cat = bundle.get("feed_title")
        feed_title.text = cat.toString()
    }
}