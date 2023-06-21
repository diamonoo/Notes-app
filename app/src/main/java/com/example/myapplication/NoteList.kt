package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.myapplication.R
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import com.example.myapplication.MyAdapter
import com.example.myapplication.AddNote
import java.util.ArrayList

class NoteList : Fragment() {
    var myNotes = "contacts"
    var KEY_TITLE = "title"
    var KEY_DESCR = "descr"
    var Key_id = "id"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list, container, false)
        val btn = view.findViewById<Button>(R.id.addnewnotebtn)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val note = ArrayList<Note>()
        val dbHelper: SQLiteOpenHelper = object : SQLiteOpenHelper(context, "contactDb", null, 1) {
            override fun onCreate(db: SQLiteDatabase) {
                db.execSQL(
                    "create table " + myNotes + "(" + Key_id + " integer primary key,"
                            + KEY_TITLE + " text," + KEY_DESCR + " text" + ")"
                )
            }

            override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
                db.execSQL("drop table if exists $myNotes")
                onCreate(db)
            }
        }
        val database = dbHelper.writableDatabase
        val cursor = database.query(myNotes, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(Key_id)
            val nameIndex = cursor.getColumnIndex(KEY_TITLE)
            val emailIndex = cursor.getColumnIndex(KEY_DESCR)
            do {
                note.add(
                    Note(
                        cursor.getString(nameIndex),
                        cursor.getString(emailIndex),
                        cursor.getInt(idIndex)
                            .toLong()
                    )
                )
                println(
                    "ID = " + cursor.getInt(idIndex) +
                            ", name= " + cursor.getString(nameIndex) + ", email= " + cursor.getString(
                        emailIndex
                    )
                )
            } while (cursor.moveToNext())
        } else println("0 rows")
        cursor.close()
        dbHelper.close()
        val myAdapter = MyAdapter(context!!, note)
        recyclerView.adapter = myAdapter
        btn.setOnClickListener {
            val fm = fragmentManager
            val ft = fm!!.beginTransaction()
            ft.replace(R.id.fragment_main, AddNote())
            ft.commit()
        }
        return view
    }
}