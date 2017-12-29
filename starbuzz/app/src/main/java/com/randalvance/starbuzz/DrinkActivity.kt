package com.randalvance.starbuzz

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteException
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*

class DrinkActivity : Activity() {

    companion object {
        const val EXTRA_DRINK_ID: String = "drinkId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val drinkId : Int = intent.extras.get(EXTRA_DRINK_ID) as Int

        val starbuzzDatabaseHelper = StarbuzzDatabaseHelper(this)

        try {
            val db = starbuzzDatabaseHelper.readableDatabase
            val cursor = db.query(
                    "DRINK",
                    arrayOf("NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"),
                    "_id = ?",
                    arrayOf(drinkId.toString()),
                    null,
                    null,
                    null
            )

            if (cursor.moveToFirst()) {
                val nameText = cursor.getString(0)
                val descriptionText = cursor.getString(1)
                val photoId = cursor.getInt(2)
                val isFavorite = cursor.getInt(3) == 1

                val photo = findViewById<ImageView>(R.id.photo)
                val name = findViewById<TextView>(R.id.name)
                val description = findViewById<TextView>(R.id.description)
                val checkBox = findViewById<CheckBox>(R.id.favorite)

                photo.setImageResource(photoId)
                photo.contentDescription = nameText
                name.text = nameText
                description.text = descriptionText
                checkBox.isChecked = isFavorite
            }

            cursor.close()
            db.close()
        } catch (e: SQLiteException) {
            val toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun onFavoriteClicked(view: View) {
        val drinkId = intent.extras.get(EXTRA_DRINK_ID) as Int
        UpdateDrinkTask().execute(drinkId)
    }

    inner class UpdateDrinkTask : AsyncTask<Int, Void, Boolean>() {

        private var drinkValues : ContentValues? = null

        override fun onPreExecute() {
            val favorite : CheckBox = findViewById<CheckBox>(R.id.favorite)
            drinkValues = ContentValues()
            drinkValues?.put("FAVORITE", favorite.isChecked)
        }

        override fun doInBackground(vararg drinks: Int?): Boolean {
            val drinkId = drinks[0]
            val starbuzzDatabaseHelper = StarbuzzDatabaseHelper(this@DrinkActivity)

            try {
                val db = starbuzzDatabaseHelper.writableDatabase
                db.update("DRINK", drinkValues, "_id = ?", arrayOf(drinkId.toString()))
                db.close()

                return true
            } catch (ex: SQLiteException) {
                return false
            }
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)

            // If we need it, change the second generic param to non-void
            // then call publishProgress from the doInBackground method
        }

        override fun onPostExecute(success: Boolean?) {
            if (success == false) {
                val toast = Toast.makeText(this@DrinkActivity, "Database unavailable", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}
