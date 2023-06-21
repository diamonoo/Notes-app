package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import android.widget.TextView
import android.widget.EditText
import com.example.myapplication.NoteList

class Login : Fragment() {
    var isSignScreen = true
    var sPref: SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login, container, false)
        val btn = view.findViewById<Button>(R.id.signbtn)
        val skipText = view.findViewById<TextView>(R.id.skiplogin)
        val loginText = view.findViewById<TextView>(R.id.textView2)
        val title = view.findViewById<TextView>(R.id.titletextview)
        val editText1 = view.findViewById<EditText>(R.id.input1)
        val editText2 = view.findViewById<EditText>(R.id.input2)
        val editText3 = view.findViewById<EditText>(R.id.input3)
        val editText4 = view.findViewById<EditText>(R.id.input4)
        loginText.setOnClickListener {
            if (isSignScreen) {
                title.text = "log-in"
                editText3.visibility = View.INVISIBLE
                editText4.visibility = View.INVISIBLE
                btn.text = "log-in"
                loginText.text = "Return to sign up"
                isSignScreen = false
            } else {
                title.text = "sign-up"
                editText3.visibility = View.VISIBLE
                editText4.visibility = View.VISIBLE
                btn.text = "sign-up"
                loginText.text = "you have an account? log-in"
                isSignScreen = true
            }
        }
        skipText.setOnClickListener {
            val fm = fragmentManager
            val ft = fm!!.beginTransaction()
            ft.replace(R.id.fragment_main, NoteList())
            ft.commit()
        }
        btn.setOnClickListener {
            if (isSignScreen) {
                sPref = requireContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                val editor = sPref!!.edit()
                editor.putString("e", editText1.text.toString())
                editor.putString("p", editText2.text.toString())
                editor.putString("n", editText3.text.toString())
                editor.putString("s", editText4.text.toString())
                editor.commit()

                title.text = "log-in"
                editText3.visibility = View.INVISIBLE
                editText4.visibility = View.INVISIBLE
                btn.text = "log-in"
                loginText.text = "Return to sign up"
                isSignScreen = false
            } else {
                sPref = requireContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                sPref!!.getString("u","i")
                if (editText1.text.toString() ==  sPref!!.getString("e", "-")) {
                    if (editText2.text.toString() == sPref!!.getString("p", "-")) skipText.text =
                        "cor" else skipText.text = "incor"
                }
                title.text = "sign-up"
                editText3.visibility = View.VISIBLE
                editText4.visibility = View.VISIBLE
                btn.text = "sign-up"
                loginText.text = "you have an account? log-in"
                isSignScreen = true
            }
        }
        return view
    }
}