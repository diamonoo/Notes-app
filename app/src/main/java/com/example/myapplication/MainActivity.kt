package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spiice)

        val btnActTwo = findViewById<Button>(R.id.btn1)
        btnActTwo!!.setOnClickListener {
            val intent = Intent( this@MainActivity, NoteList::class.java )
            startActivity(intent)
        }
    }
}