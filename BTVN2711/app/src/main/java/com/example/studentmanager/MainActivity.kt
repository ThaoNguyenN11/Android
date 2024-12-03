package com.example.studentmanager
// MainActivity.kt
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), AddStudentFragment.OnStudentAddedListener {
    private lateinit var listViewStudents: ListView
    private val studentList = ArrayList<Student>()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewStudents = findViewById(R.id.listViewStudents)
        adapter = StudentAdapter(this, studentList)
        listViewStudents.adapter = adapter

        registerForContextMenu(listViewStudents)

        listViewStudents.setOnItemClickListener { parent, view, position, id ->
            val selectedStudent = studentList[position]
            val editFragment = EditStudentFragment.newInstance(selectedStudent)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, editFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AddStudentFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStudentAdded(student: Student) {
        studentList.add(student)
        adapter.notifyDataSetChanged()
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedStudent = studentList[info.position]

        return when (item.itemId) {
            R.id.edit -> {
                val editFragment = EditStudentFragment.newInstance(selectedStudent)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, editFragment)
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.remove -> {
                studentList.removeAt(info.position)
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
