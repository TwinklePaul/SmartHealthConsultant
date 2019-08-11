package com.webtekproject.smarhealthconsultancy.Common

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.R


class View_Feed : Base_Activity() {

    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null
    //private var data: ArrayList<DataModel>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        actionBar!!.elevation = 4.0F
/*
      recyclerView =  findViewById <RecyclerView> (R.id.listView)
        recyclerView!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!.LayoutManager(layoutManager)
        recyclerView!.setItemAnimator(DefaultItemAnimator())

        data =  ArrayList<DataModel>()


        val myListAdapter = Adapter_Feed (this, empArrayId, empArrayName, empArrayEmail)
        recyclerView.adapter = myListAdapter*/
    }
}