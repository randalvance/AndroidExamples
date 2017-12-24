package randalvance.com.androidexamples

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import randalvance.com.androidexamples.intents001.Intents001MainActivity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this, R.layout.activity_listview, resources.getStringArray(R.array.examples))

        var listView = findViewById<ListView>(R.id.examples_list)
        listView.adapter = adapter
        listView.setOnItemClickListener({
            adapterView, view, i, l -> run {
                val intent = Intent(this, Intents001MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
