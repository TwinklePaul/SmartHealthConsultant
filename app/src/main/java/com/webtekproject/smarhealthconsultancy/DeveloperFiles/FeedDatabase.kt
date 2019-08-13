package com.webtekproject.smarhealthconsultancy.DeveloperFiles

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.webtekproject.smarhealthconsultancy.Model_Classes.FeedModel

class FeedDatabase(context: Context) : SQLiteOpenHelper(
    context,
    DB_NAME, null,
    DB_VERSION
) {
    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "FeedDb"

        private val TABLE_FEED = "News_Feed"

        private var KEY_FEED_ID = "Feed_ID"
        private val KEY_FEED_TITLE = "Feed_Title"
        private val KEY_FEED_CATEGORY = "Feed_Cat"
        private val KEY_FEED_DESC = "Feed_Desc"
        private val KEY_FEED_IMG = "Feed_Img"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //To change body of created functions use File | Settings | File Templates.

        //creating Table Doctor
        val CREATE_TABLE_FEED = ("CREATE TABLE " + TABLE_FEED + " (" +
                KEY_FEED_ID + " TEXT PRIMARY KEY, " + KEY_FEED_TITLE + " TEXT," + KEY_FEED_CATEGORY + " TEXT, " + KEY_FEED_DESC + " TEXT," + KEY_FEED_IMG + "INTEGER " + ")")

        db?.execSQL(CREATE_TABLE_FEED)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //To change body of created functions use File | Settings | File Templates.

        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_FEED)

        onCreate(db)
    }

    fun addFeed(feed: FeedModel): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_FEED_ID, feed.Feed_ID)
        contentValues.put(KEY_FEED_TITLE, feed.Feed_Title)
        contentValues.put(KEY_FEED_CATEGORY, feed.Feed_Cat)
        contentValues.put(KEY_FEED_DESC, feed.Feed_Desc)
        contentValues.put(KEY_FEED_IMG, feed.Feed_Img)


        val success = db.insert(TABLE_FEED, null, contentValues)
        db.close()
        return success
    }

    fun viewFeed(): List<FeedModel> {

        val Feed_List: ArrayList<FeedModel> = ArrayList<FeedModel>()
        val selectQuery = "SELECT * FROM $TABLE_FEED"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var Feed_ID: String
        var Feed_Title: String
        var Feed_Cat: String
        var Feed_Desc: String
        var Feed_Img: Int

        if (cursor.moveToFirst()) {
            do {
                Feed_ID = cursor.getString(cursor.getColumnIndex("Feed_ID"))
                Feed_Title = cursor.getString(cursor.getColumnIndex("Feed_Title"))
                Feed_Cat = cursor.getString(cursor.getColumnIndex("Feed_Cat"))
                Feed_Desc = cursor.getString(cursor.getColumnIndex("Feed_Desc"))
                Feed_Img = cursor.getInt(cursor.getColumnIndex("Feed_Img"))

                val feed = FeedModel(Feed_ID, Feed_Title, Feed_Cat, Feed_Desc, Feed_Img)
                Feed_List.add(feed)
            } while (cursor.moveToNext())
        }

        return Feed_List
    }
}
