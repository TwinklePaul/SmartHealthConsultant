package com.webtekproject.smarhealthconsultancy.Common

import android.os.Bundle
import android.view.View
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.FeedDatabase
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.ListAdapter_Feed
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_feed.*
import org.jetbrains.anko.toast


class View_Feed : Base_Activity() {


    val title_array: ArrayList<String> = ArrayList()
    val description_array: ArrayList<String> = ArrayList()
    val category_array: ArrayList<String> = ArrayList()

    val img_Array = arrayOf<Int>(
        R.drawable.advt,
        R.drawable.fitness_solns,
        R.drawable.devs,
        R.drawable.health_alert,
        R.drawable.health
    )

    val cat = arrayOf<String>(
        "Advertisement",
        "Fitness Solution",
        "Latest Development",
        "Health Alerts",
        "Miscellaneous"
    )

    val img = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)


        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar


        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

        val db = FeedDatabase(this)
        val feed_list = db.viewFeed()

        if (feed_list.isEmpty())
            listView.visibility = View.GONE
        else {
            img_feed!!.visibility = View.GONE
        }

        for (i in feed_list) {
            title_array.add(i.Feed_Title)
            description_array.add(i.Feed_Desc)
            category_array.add(i.Feed_Cat)

            var count = 0
            for (j in cat) {
                if (j.equals(i.Feed_Cat))
                    img.add(img_Array[count])
                count++
            }
        }


        val myListAdapter =
            ListAdapter_Feed(this, title_array, description_array, category_array, img)

        listView.adapter = myListAdapter

        listView.setOnItemClickListener { adapterView, view, position, id ->

            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            toast("Click on Item at $itemAtPos, its Item ID at $itemIdAtPos")
        }
    }

}
