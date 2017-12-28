package com.randalvance.starbuzz

import android.app.Activity
import android.os.Bundle
import android.widget.*

class DrinkActivity : Activity() {

    companion object {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val drinkId : Int = intent.extras.get(EXTRA_DRINK_ID) as Int

        val drink : Drink = drinks[drinkId]

        val photo = findViewById<ImageView>(R.id.photo)
        val name = findViewById<TextView>(R.id.name)
        val description = findViewById<TextView>(R.id.description)

        photo.setImageResource(drink.imageResourceId)
        photo.contentDescription = drink.name
        name.text = drink.name
        description.text = drink.description
    }
}

const val EXTRA_DRINK_ID: String = "drinkId"