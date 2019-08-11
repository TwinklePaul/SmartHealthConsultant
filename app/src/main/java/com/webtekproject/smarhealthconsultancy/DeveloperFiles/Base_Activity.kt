package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.webtekproject.smarhealthconsultancy.R
import kotlinx.android.synthetic.main.activity_base.*

open class Base_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar


        // Set toolbar title/app title
        actionBar!!.elevation = 4.0F

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.defaultmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.more -> {
                return true
            }
            //when (item.itemId) {

            R.id.share -> {
                val popupMenu: PopupMenu = PopupMenu(this, my_toolbar, Gravity.END)
                popupMenu.menuInflater.inflate(R.menu.shareoptions, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_wa -> {
                            val sendIntent = Intent()
                            sendIntent.action = Intent.ACTION_SEND
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                            sendIntent.type = "text/plain"
                            startActivity(sendIntent)

                            sendIntent.setPackage("com.whatsapp")
                            startActivity(sendIntent)
                            Toast.makeText(this, "WhatsApp:", Toast.LENGTH_LONG).show()
                        }

                        R.id.action_msg -> {
                            val sendIntent = Intent()
                            sendIntent.action = Intent.ACTION_SEND
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                            sendIntent.type = "text/plain"
                            startActivity(sendIntent)
                            Toast.makeText(this, "Messaging:", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                })
                popupMenu.show()
                return true
            }

            R.id.aboutus -> {
                intent = Intent(this, About_Us::class.java)
                startActivity(intent)
                Toast.makeText(this, "About Us:", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.help -> {
                Toast.makeText(this, "Help:", Toast.LENGTH_SHORT).show()
                return true
            }


            // true}

            else -> super.onOptionsItemSelected(item)
        }


    }
}