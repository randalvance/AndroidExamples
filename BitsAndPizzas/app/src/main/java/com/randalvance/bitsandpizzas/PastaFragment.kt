package com.randalvance.bitsandpizzas


import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter


class PastaFragment : ListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val adapter = ArrayAdapter<String>(inflater?.context, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.pastas))

        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
