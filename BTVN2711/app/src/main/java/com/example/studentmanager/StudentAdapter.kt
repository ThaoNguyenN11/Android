package com.example.studentmanager

// StudentAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter(context: Context, students: List<Student>) : ArrayAdapter<Student>(context, 0, students) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val student = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)

        val nameTextView = view.findViewById<TextView>(android.R.id.text1)
        val mssvTextView = view.findViewById<TextView>(android.R.id.text2)

        nameTextView.text = student?.name
        mssvTextView.text = student?.mssv

        return view
    }
}