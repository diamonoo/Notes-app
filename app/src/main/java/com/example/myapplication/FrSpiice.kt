package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.Login

class FrSpiice : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.spiice, container, false)
        val btn = view.findViewById<Button>(R.id.btn1)
        btn.setOnClickListener {
            val fm = fragmentManager
            val ft = fm!!.beginTransaction()
            ft.replace(R.id.fragment_main, Login())
            ft.commit()
        }
        return view
    }
}