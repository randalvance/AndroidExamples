package com.randalvance.workout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_WORKOUT_ID = "extraWorkoutId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val workoutId = intent.extras.get(EXTRA_WORKOUT_ID) as Int

        val detailFragment = supportFragmentManager.findFragmentById(R.id.detail_frag) as WorkoutDetailFragment
        detailFragment.setWorkout(workoutId.toLong())
    }
}
