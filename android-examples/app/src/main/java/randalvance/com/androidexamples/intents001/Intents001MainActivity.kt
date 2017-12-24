package randalvance.com.androidexamples.intents001

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import randalvance.com.androidexamples.R

class Intents001MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents001_main)

        val button = findViewById<Button>(R.id.intents001_sendButton)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Hello World")
            intent.putExtra(Intent.EXTRA_SUBJECT, "My Message")
            startActivity(intent)
        }
    }
}
