package com.randalvance.starbuzz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class DrinkCategoryActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        val listAdapter = ArrayAdapter<Drink>(this, android.R.layout.simple_list_item_1, drinks)

        val listDrinks = findViewById<ListView>(R.id.list_drinks)
        listDrinks.adapter = listAdapter

        listDrinks.setOnItemClickListener { _, _, _, id ->
            val intent = Intent(this@DrinkCategoryActivity, DrinkActivity::class.java)
            intent.putExtra(EXTRA_DRINK_ID, id.toInt())
            startActivity(intent)
        }
    }
}
