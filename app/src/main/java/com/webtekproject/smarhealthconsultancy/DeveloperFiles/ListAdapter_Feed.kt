package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.webtekproject.smarhealthconsultancy.R

class ListAdapter_Feed(
    private val context: Activity,
    private val title: ArrayList<String>,
    private val description: ArrayList<String>,
    private val category: ArrayList<String>,
    private val imgid: ArrayList<Int>
) :
    ArrayAdapter<String>(context, R.layout.layout_feedpost, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.layout_feedpost, null, true)

        val titleText = rowView.findViewById<TextView>(R.id.title_feed)
        val imageView = rowView.findViewById<ImageView>(R.id.icon)
        val subtitleText = rowView.findViewById<TextView>(R.id.description_feed)
        val categoryText = rowView.findViewById<TextView>(R.id.cat)

        titleText.text = title[position]
        imageView.setImageResource(imgid[position])
        subtitleText.text = description[position]
        categoryText.text = category[position]

        return rowView

    }
}
