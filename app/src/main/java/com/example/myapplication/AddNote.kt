package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note)
        val btnActTwo = findViewById<Button>(R.id.savebtn)

        btnActTwo.setOnClickListener {
            val intent = Intent(this@AddNote, NoteList::class.java)
            startActivity(intent)
        }
    }
}