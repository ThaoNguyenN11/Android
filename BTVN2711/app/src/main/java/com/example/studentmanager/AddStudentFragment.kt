package com.example.studentmanager
// AddStudentFragment.kt
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddStudentFragment : Fragment() {
    private lateinit var editTextName: EditText
    private lateinit var editTextMSSV: EditText
    private lateinit var buttonSave: Button
    private var listener: OnStudentAddedListener? = null

    interface OnStudentAddedListener {
        fun onStudentAdded(student: Student)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnStudentAddedListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_student, container, false)

        editTextName = view.findViewById(R.id.editTextName)
        editTextMSSV = view.findViewById(R.id.editTextMSSV)
        buttonSave = view.findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val mssv = editTextMSSV.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                listener?.onStudentAdded(Student(name, mssv))
                fragmentManager?.popBackStack()
            }
        }

        return view
    }
}