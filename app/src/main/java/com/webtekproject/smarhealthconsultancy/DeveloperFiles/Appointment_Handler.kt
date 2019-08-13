package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.webtekproject.smarhealthconsultancy.Model_Classes.App_Request_Model

class Appointment_Handler(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME, null,
    DB_VERSION
) {
    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "Request_Appointment"

        private val TABLE_REQUESTS = "Request_Appointment"

        private val KEY_PID = "Patient_ID"
        private val KEY_DID = "Dr_ID"
        private val KEY_OID = "Org_ID"
        private val KEY_OType = "Org_Type"
        private val KEY_App_ID = "App_ID"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //To change body of created functions use File | Settings | File Templates.

        //creating Table Doctor
        val CREATE_TABLE_REQUESTS = ("CREATE TABLE " + TABLE_REQUESTS + " (" + KEY_App_ID + "TEXT PRIMARY KEY," +
                KEY_PID + " TEXT, " + KEY_DID + " TEXT," + KEY_OID + " TEXT, " + KEY_OType + " TEXT " + ")")

        db?.execSQL(CREATE_TABLE_REQUESTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //To change body of created functions use File | Settings | File Templates.

        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUESTS)

        onCreate(db)
    }

    fun addRequest(requests: App_Request_Model): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_App_ID, requests.App_ID)
        contentValues.put(KEY_PID, requests.Patient_ID)
        contentValues.put(KEY_DID, requests.Dr_ID)
        contentValues.put(KEY_OID, requests.Org_ID)
        contentValues.put(KEY_OType, requests.Org_Type)


        val success = db.insert(TABLE_REQUESTS, null, contentValues)
        db.close()
        return success
    }

    fun viewrequest(): List<App_Request_Model> {

        val Req_List: ArrayList<App_Request_Model> = ArrayList<App_Request_Model>()
        val selectQuery = "SELECT * FROM $TABLE_REQUESTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var App_ID: String
        var Patient_ID: String
        var Dr_ID: String
        var Org_ID: String
        var Org_Type: String

        if (cursor.moveToFirst()) {
            do {
                App_ID = cursor.getString(cursor.getColumnIndex("App_ID"))
                Patient_ID = cursor.getString(cursor.getColumnIndex("Patient_ID"))
                Dr_ID = cursor.getString(cursor.getColumnIndex("Dr_ID"))
                Org_ID = cursor.getString(cursor.getColumnIndex("Org_ID"))
                Org_Type = cursor.getString(cursor.getColumnIndex("Org_Type"))

                val feed = App_Request_Model(App_ID, Patient_ID, Dr_ID, Org_ID, Org_Type)
                Req_List.add(feed)
            } while (cursor.moveToNext())
        }

        return Req_List
    }

    //method to update data
    fun updaterequest(requests: App_Request_Model): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_App_ID, requests.App_ID)
        contentValues.put(KEY_PID, requests.Patient_ID)
        contentValues.put(KEY_DID, requests.Dr_ID)
        contentValues.put(KEY_OID, requests.Org_ID)
        contentValues.put(KEY_OType, requests.Org_Type)

        //updating row
        val success = db.update(TABLE_REQUESTS, contentValues, "id = " + requests.App_ID, null)
        db.close()

        return success
    }

    //method to delete data
    fun deleterequest(requests: App_Request_Model): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_App_ID, requests.App_ID)

        val success = db.delete(TABLE_REQUESTS, "id = " + requests.App_ID, null)
        db.close()
        return success

    }

}
