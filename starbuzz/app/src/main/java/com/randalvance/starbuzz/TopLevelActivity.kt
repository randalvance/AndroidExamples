package com.randalvance.starbuzz

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import com.randalvance.starbuzz.DrinkActivity.Companion.EXTRA_DRINK_ID

class TopLevelActivity : Activity() {

    private var favoritesCursor: Cursor? = null
    private var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)
        setupOptionsListView()
        setupFavoritesListView()
    }

    override fun onRestart() {
        super.onRestart()

        val newCursor = db?.query("DRINK", arrayOf("_id", "NAME"), "FAVORITE = 1", null, null, null, null)
        val listFavorites = findViewById<ListView>(R.id.list_favorites)
        val adapter = listFavorites.adapter as CursorAdapter
        adapter.changeCursor(newCursor)
        favoritesCursor = newCursor
    }

    override fun onDestroy() {
        super.onDestroy()

        favoritesCursor?.close()
        db?.close()
    }

    private fun setupOptionsListView() {
        val listView: ListView = findViewById<ListView>(R.id.list_options)
        listView.setOnItemClickListener { _, _, position, _ ->
            if (position == 0) {
                val intent = Intent(this@TopLevelActivity, DrinkCategoryActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupFavoritesListView() {
        val listFavorites = findViewById<ListView>(R.id.list_favorites)
        val starbuzzDatabaseHelper = StarbuzzDatabaseHelper(this)

        try {
            db = starbuzzDatabaseHelper.readableDatabase
            favoritesCursor = db?.query("DRINK", arrayOf("_id", "NAME"), "FAVORITE = 1", null, null, null, null)

            val adapter = SimpleCursorAdapter(this@TopLevelActivity, android.R.layout.simple_list_item_1,
                    favoritesCursor,
                    arrayOf("NAME"), intArrayOf(android.R.id.text1), 0)
            listFavorites.adapter = adapter
        } catch (ex: SQLiteException) {
            val toast = Toast.makeText(this@TopLevelActivity, "Database unavailable", Toast.LENGTH_SHORT)
            toast.show()
        }

        listFavorites.setOnItemClickListener {
            _, _, _, id ->
                val intent = Intent(this@TopLevelActivity, DrinkActivity::class.java)
                intent.putExtra(EXTRA_DRINK_ID, id.toInt())
                startActivity(intent)
        }
    }
}
