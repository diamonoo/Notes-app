package com.example.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.example.myapplication.MyAdapter.MyViewHolder
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import java.text.DateFormat
import java.util.ArrayList

class MyAdapter(var context: Context, var notesList: ArrayList<Note>) :
    RecyclerView.Adapter<MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleOutput: TextView
        var descriptionOutput: TextView
        var timeOutput: TextView

        init {
            titleOutput = itemView.findViewById(R.id.titleoutput)
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput)
            timeOutput = itemView.findViewById(R.id.timeoutput)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = notesList[position]
        holder.titleOutput.text = note.title
        holder.descriptionOutput.text = note.description
        val formatedTime = DateFormat.getDateTimeInstance().format(note.createdTime)
        holder.timeOutput.text = formatedTime
        holder.itemView.setOnLongClickListener { v ->
            val menu = PopupMenu(context, v)
            menu.menu.add("DELETE")
            menu.setOnMenuItemClickListener { item ->
                if (item.title == "DELETE") {
                    //delete the note
                    Toast.makeText(context, "Note deleted" + item.order, Toast.LENGTH_SHORT).show()
                }
                true
            }
            menu.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}