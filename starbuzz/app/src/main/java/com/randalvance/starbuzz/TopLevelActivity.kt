package com.randalvance.starbuzz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView

class TopLevelActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)

        val listView: ListView = findViewById<ListView>(R.id.list_options)
        listView.setOnItemClickListener{
            _, _, position, _ -> run {
                if (position == 0) {
                    val intent = Intent(this@TopLevelActivity, DrinkCategoryActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
