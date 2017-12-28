package com.randalvance.bitsandpizzas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

class PizzaDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PIZZA_ID = "extraPizzaId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val pizzaId = intent.extras.get(EXTRA_PIZZA_ID) as Int
        val pizzaName = Pizza.pizzas[pizzaId].name
        val pizzaImage = Pizza.pizzas[pizzaId].imageResourceId

        val textView = findViewById<TextView>(R.id.pizza_text)
        val imageView = findViewById<ImageView>(R.id.pizza_image)

        textView.text = pizzaName

        imageView.setImageDrawable(ContextCompat.getDrawable(this, pizzaImage))
        imageView.contentDescription = pizzaName
    }
}
