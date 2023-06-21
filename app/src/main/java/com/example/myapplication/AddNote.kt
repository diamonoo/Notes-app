package com.example.myapplication

import android.database.sqlite.SQLiteOpenHelper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.widget.EditText
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button


class AddNote : Fragment() {
    var dbHelper: SQLiteOpenHelper? = null
    var myNotes = "contacts"
    var KEY_TITLE = "title"
    var KEY_DESCR = "descr"
    var Key_id = "id"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_note, container, false)
        val titleTW = view.findViewById<EditText>(R.id.titleinput)
        val descr = view.findViewById<EditText>(R.id.descriptioninput)
        val btn = view.findViewById<Button>(R.id.savebtn)
        dbHelper = object : SQLiteOpenHelper(context, "contactDb", null, 1) {
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
        btn.setOnClickListener {
            val database = (dbHelper as SQLiteOpenHelper).getWritableDatabase()
            val contentValues = ContentValues()
            contentValues.put(KEY_TITLE, titleTW.text.toString())
            contentValues.put(KEY_DESCR, descr.text.toString())
            database.insert(myNotes, null, contentValues)
            (dbHelper as SQLiteOpenHelper).close()
            val fm = fragmentManager
            val ft = fm!!.beginTransaction()
            ft.replace(R.id.fragment_main, NoteList())
            ft.commit()
        }
        return view
    }
}