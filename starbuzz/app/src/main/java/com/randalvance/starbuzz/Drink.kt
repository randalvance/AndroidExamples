package com.randalvance.starbuzz

/**
 * Created by Randal on 12/26/2017.
 */
class Drink(var name: String, var description: String, var imageResourceId: Int) {
    override fun toString(): String = this.name
}

val drinks = arrayOf(
        Drink("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte),
        Drink("Cappucino", "Espresso, hot milk, and steamed milk foam", R.drawable.cappuccino),
        Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
)