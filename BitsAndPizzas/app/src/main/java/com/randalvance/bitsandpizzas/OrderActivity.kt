package com.randalvance.bitsandpizzas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import android.widget.Toast

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //toolbar.setPadding(0, getStatusBarHeight(), 0, 0)

        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onClickDone(view: View) {
        val text: CharSequence = "Your order has been updated"
        val duration: Int = Snackbar.LENGTH_SHORT
        val snackbar = Snackbar.make(findViewById(R.id.coordinator),text, duration)
        snackbar.setAction("Undo") {
            val toast = Toast.makeText(this@OrderActivity, "Undone!", Toast.LENGTH_SHORT)
            toast.show()
        }
        snackbar.show()
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
