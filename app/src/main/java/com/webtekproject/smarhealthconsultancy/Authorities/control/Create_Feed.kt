package com.webtekproject.smarhealthconsultancy.Authorities.control

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
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
    val img_Array = arrayOf<Int>(
        R.drawable.advt,
        R.drawable.fitness_solns,
        R.drawable.devs,
        R.drawable.health_alert,
        R.drawable.health
    )

    var categoryText = ""
    var titleText = ""
    var subtitleText = ""
    var img: Int = -1


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

        titleText = findViewById<EditText>(R.id.title).text.toString()
        val imageView = findViewById<ImageView>(R.id.icon)
        subtitleText = findViewById<EditText>(R.id.description).text.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        categoryText = cat[position]
        img = img_Array[position]
    }

    fun submit(view: View) {

        val db = FeedDatabase(this)

        val ID = "${titleText}_${categoryText}"

        if (titleText.trim() != " " && subtitleText.trim() != " " && categoryText.trim() != "Choose Category: ") {
            val status = db.addFeed(FeedModel(ID, titleText, categoryText, subtitleText, img))

            if (status > -1) {
                toast(" Record Saved")

                intent = Intent(this, View_Feed::class.java)
                startActivity(intent)
            } else {
                toast(" Fields can't be blank; and \nFeeds with Same Title Not Accepted under Same Category ")
            }
        }
    }
}