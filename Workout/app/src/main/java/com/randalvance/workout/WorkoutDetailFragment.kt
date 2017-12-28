package com.randalvance.workout


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.*
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class WorkoutDetailFragment : Fragment() {

    private var workoutId : Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong("workoutId")
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()

        val view = getView()

        if (view != null) {
            val title = view.findViewById<TextView>(R.id.textTitle)
            val description = view.findViewById<TextView>(R.id.textDescription)
            val workout = workouts[workoutId.toInt()]
            title.text = workout.name
            description.text = workout.description
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong("workoutId", workoutId)
    }

    fun setWorkout(id: Long) {
        this.workoutId = id
    }

}// Required empty public constructor
