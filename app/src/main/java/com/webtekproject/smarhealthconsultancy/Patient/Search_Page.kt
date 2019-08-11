package com.webtekproject.smarhealthconsultancy.Patient

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import android.widget.Toast
import com.webtekproject.smarhealthconsultancy.DeveloperFiles.Base_Activity
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_search_page.*

class Search_Page : Base_Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_page)
        var a: String = ""
        var b: String = ""

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.elevation = 4.0F
        actionBar.title = "Search Page!!"

        title_search_disease.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                a = title_search_disease.text.toString()
            }
        })

        title_search_fitness.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                b = title_search_disease.text.toString()
            }
        })

        button_search_disease.setOnClickListener {
            var id: Int = radio_search_disease.checkedRadioButtonId
            if (id != -1 && a == "") {
                val radio: RadioButton = findViewById(id)
                a = radio.text.toString()
                Toast.makeText(this, " Searching for Disease: ${radio.text}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, " Searching for Disease: ${a}", Toast.LENGTH_SHORT).show()
            }
        }

        button_search_fitness.setOnClickListener {
            var id: Int = radio_search_fitness.checkedRadioButtonId
            if (id != -1 && b == "") {
                val radio: RadioButton = findViewById(id)
                b = radio.text.toString()
                Toast.makeText(applicationContext, " Searching for Fitness: ${radio.text}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, " Searching for Fitness: ${b}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}