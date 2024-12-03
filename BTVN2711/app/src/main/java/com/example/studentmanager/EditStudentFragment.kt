package com.example.studentmanager

// EditStudentFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class EditStudentFragment : Fragment() {
    private lateinit var editTextName: EditText
    private lateinit var editTextMSSV: EditText
    private lateinit var buttonUpdate: Button
    private lateinit var student: Student

    companion object {
        fun newInstance(student: Student): EditStudentFragment {
            val fragment = EditStudentFragment()
            val args = Bundle()
            args.putString("name", student.name)
            args.putString("mssv", student.mssv)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        editTextName = view.findViewById(R.id.editTextName)
        editTextMSSV = view.findViewById(R.id.editTextMSSV)
        buttonUpdate = view.findViewById(R.id.buttonUpdate)

        arguments?.let {
            editTextName.setText(it.getString("name"))
            editTextMSSV.setText(it.getString("mssv"))
        }

        buttonUpdate.setOnClickListener {
            val name = editTextName.text.toString()
            val mssv = editTextMSSV.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                student = Student(name, mssv)
                fragmentManager?.popBackStack()
            }
        }

        return view
    }
}