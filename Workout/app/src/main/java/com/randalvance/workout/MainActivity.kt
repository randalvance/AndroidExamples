package com.randalvance.workout

import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout

class MainActivity : AppCompatActivity(), Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun itemClicked(id: Long) {

        val fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container)

        // We are in tablet
        if (fragmentContainer != null) {
            val workoutDetailFragment = WorkoutDetailFragment()
            workoutDetailFragment.setWorkout(id)

            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, workoutDetailFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()

        } else { // We are in phone
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id.toInt())
            startActivity(intent)
        }
    }

    fun onShowDetails(view: View) {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}
