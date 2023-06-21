package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import java.util.ArrayList

class NoteList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val note = ArrayList<Note>()
        note.add(Note("title 1", "text 1", 12345L))
        note.add(Note("title 2", "text 2", 54321L))
        val myAdapter = MyAdapter(this, note)
        recyclerView.adapter = myAdapter
        val btnActTwo = findViewById<Button>(R.id.addnewnotebtn)

        btnActTwo!!.setOnClickListener {
            val intent = Intent(this@NoteList, AddNote::class.java)
            startActivity(intent)
        }
    }
}