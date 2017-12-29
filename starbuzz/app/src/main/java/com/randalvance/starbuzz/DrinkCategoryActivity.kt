package com.randalvance.starbuzz

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import com.randalvance.starbuzz.DrinkActivity.Companion.EXTRA_DRINK_ID

class DrinkCategoryActivity : Activity() {

    private var cursor : Cursor? = null;
    private var db: SQLiteDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        val listDrinks = findViewById<ListView>(R.id.list_drinks)

        val sqlLiteHelper = StarbuzzDatabaseHelper(this)

        try {
            db = sqlLiteHelper.readableDatabase
            cursor = db?.query(
                    "DRINK",
                    arrayOf("_id", "NAME"),
                    null,
                    null,
                    null,
                    null,
                    null
            )

            val adapter = SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    arrayOf("NAME"),
                    intArrayOf(android.R.id.text1),
                    0
            )

            listDrinks.adapter = adapter

        } catch (ex: SQLiteException) {
            val toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT)
            toast.show()
        }

        listDrinks.setOnItemClickListener { _, _, _, id ->
            val intent = Intent(this@DrinkCategoryActivity, DrinkActivity::class.java)
            intent.putExtra(EXTRA_DRINK_ID, id.toInt())
            startActivity(intent)
        }

        // We can't close cursor and db here as we may still need to use it, so we put it
        // in onDestroy instead
    }

    override fun onDestroy() {
        super.onDestroy()

        cursor?.close()
        db?.close()
    }
}
