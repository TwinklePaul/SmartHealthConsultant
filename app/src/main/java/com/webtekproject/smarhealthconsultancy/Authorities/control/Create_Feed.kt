package com.webtekproject.smarhealthconsultancy.Authorities.control

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.webtekproject.smarhealthconsultancy.Common.View_Feed
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.FeedDatabase
import com.webtekproject.smarhealthconsultancy.Model_Classes.FeedModel
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_generate_feed.*
import org.jetbrains.anko.toast


class Create_Feed : Base_Activity(), AdapterView.OnItemSelectedListener {

    var spinner: Spinner? = null

    val cat = arrayOf<String>(
        "Choose Category: ",
        "Advertisement",
        "Fitness Solution",
        "Latest Development",
        "Health Alerts",
        "Miscellaneous"
    )

    var categoryText = ""
    var titleText = ""
    var subtitleText = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_feed)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar

        actionBar!!.elevation = 4.0F

        spinner = this.spinner_cat
        spinner!!.onItemSelectedListener = this

        val spl = ArrayAdapter(this, android.R.layout.simple_spinner_item, cat)
        spl.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner!!.adapter = spl


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        categoryText = cat[position]
    }

    fun submit(view: View) {

        val db = FeedDatabase(this)

        titleText = titleid.text.toString()
        titleText.toUpperCase()
        subtitleText = description_generate.text.toString()

        val id_feed = "$titleText _ $categoryText"

        if (titleText.trim() != " " && subtitleText.trim() != " " && categoryText.trim() != "Choose Category: ") {
            val status = db.addFeed(FeedModel(id_feed, titleText, categoryText, subtitleText))

            if (status > -1) {
                toast(" Record Saved:  $id_feed, $titleText, $subtitleText")
                titleid.text.clear()
                description_generate.text.clear()

                intent = Intent(this, View_Feed::class.java)
                startActivity(intent)
            } else {
                toast(" Fields can't be blank; and \nFeeds with Same Title Not Accepted under Same Category ")
            }
        }
    }
}