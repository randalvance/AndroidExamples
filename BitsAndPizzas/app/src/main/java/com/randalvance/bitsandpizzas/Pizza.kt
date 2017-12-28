package com.randalvance.bitsandpizzas

class Pizza private constructor(val name: String, val imageResourceId: Int) {
    companion object {

        val pizzas = arrayOf(Pizza("Diavolo", R.drawable.diavolo), Pizza("Funghi", R.drawable.funghi))
    }
}
