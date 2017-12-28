package com.randalvance.bitsandpizzas

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PizzaFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val pizzaRecycler = inflater.inflate(R.layout.fragment_pizza, container, false) as RecyclerView

        val pizzaNames = Pizza.pizzas.map { it.name }

        var pizzaImages = Pizza.pizzas.map { it.imageResourceId }

        val adapter = CaptionedImagesAdapter(pizzaNames.toTypedArray(), pizzaImages.toTypedArray())
        pizzaRecycler.adapter = adapter

        val layoutManager = GridLayoutManager(activity, 2)
        pizzaRecycler.layoutManager = layoutManager

        adapter.onCardClick = { pos ->
            val intent = Intent(activity, PizzaDetailActivity::class.java)
            intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, pos)
            activity?.startActivity(intent)
        };

        return pizzaRecycler
    }

}
