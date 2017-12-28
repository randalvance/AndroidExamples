package com.randalvance.workout


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.support.v4.app.*
import android.widget.ListView

class WorkoutListFragment : ListFragment() {

    private var listener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val names = workouts.map { it.name }

        val adapter = ArrayAdapter<String>(inflater.context, android.R.layout.simple_list_item_1, names)

        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        listener = context as Listener
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {

        if (listener != null) {
            listener?.itemClicked(id)
        }
    }

}// Required empty public constructor
