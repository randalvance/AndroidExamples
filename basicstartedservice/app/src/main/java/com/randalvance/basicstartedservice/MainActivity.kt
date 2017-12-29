package com.randalvance.basicstartedservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val intent = Intent(this, DelayedMessageService::class.java)
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, resources.getString(R.string.response))
        startService(intent)
    }
}
