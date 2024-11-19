package vn.edu.hust.studentman

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  private lateinit var students: MutableList<StudentModel>
  private lateinit var studentAdapter: StudentAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    students = mutableListOf(
      StudentModel("Nguyễn Văn An", "SV001"),
      StudentModel("Trần Thị Bảo", "SV002"),
      StudentModel("Lê Hoàng Cường", "SV003"),
      StudentModel("Phạm Thị Dung", "SV004"),
      StudentModel("Đỗ Minh Đức", "SV005"),
      StudentModel("Vũ Thị Hoa", "SV006"),
      StudentModel("Hoàng Văn Hải", "SV007"),
      StudentModel("Bùi Thị Hạnh", "SV008"),
      StudentModel("Đinh Văn Hùng", "SV009"),
      StudentModel("Nguyễn Thị Linh", "SV010"),
      StudentModel("Phạm Văn Long", "SV011"),
      StudentModel("Trần Thị Mai", "SV012"),
      StudentModel("Lê Thị Ngọc", "SV013"),
      StudentModel("Vũ Văn Nam", "SV014"),
      StudentModel("Hoàng Thị Phương", "SV015"),
      StudentModel("Đỗ Văn Quân", "SV016"),
      StudentModel("Nguyễn Thị Thu", "SV017"),
      StudentModel("Trần Văn Tài", "SV018"),
      StudentModel("Phạm Thị Tuyết", "SV019"),
      StudentModel("Lê Văn Vũ", "SV020")
    )

    studentAdapter = StudentAdapter(students)

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      showAddNewStudentDialog()
    }
  }

  fun showAddNewStudentDialog() {
    val dialogView = layoutInflater.inflate(R.layout.dialog_add_student, null)
    val dialog = AlertDialog.Builder(this)
      .setView(dialogView)
      .setTitle("Add New Student")
      .setPositiveButton("Add") { _, _ ->
        val studentName = dialogView.findViewById<EditText>(R.id.edit_student_name).text.toString()
        val studentId = dialogView.findViewById<EditText>(R.id.edit_student_id).text.toString()
        students.add(StudentModel(studentName, studentId))  // Thêm sinh viên vào danh sách
        studentAdapter.notifyItemInserted(students.size - 1)  // Cập nhật RecyclerView
      }
      .setNegativeButton("Cancel", null)
      .create()
    dialog.show()
  }

  fun showEditStudentDialog(student: StudentModel, position: Int) {
    val dialogView = layoutInflater.inflate(R.layout.dialog_add_student, null)
    dialogView.findViewById<EditText>(R.id.edit_student_name).setText(student.studentName)
    dialogView.findViewById<EditText>(R.id.edit_student_id).setText(student.studentId)

    val dialog = AlertDialog.Builder(this)
      .setView(dialogView)
      .setTitle("Edit Student")
      .setPositiveButton("Save") { _, _ ->
        val studentName = dialogView.findViewById<EditText>(R.id.edit_student_name).text.toString()
        val studentId = dialogView.findViewById<EditText>(R.id.edit_student_id).text.toString()
        students[position] = StudentModel(studentName, studentId)  // Cập nhật sinh viên
        studentAdapter.notifyItemChanged(position)  // Cập nhật RecyclerView
      }
      .setNegativeButton("Cancel", null)
      .create()
    dialog.show()
  }

  fun showDeleteConfirmationDialog(student: StudentModel, position: Int) {
    AlertDialog.Builder(this)
      .setTitle("Confirm Deletion")
      .setMessage("Are you sure you want to delete this student?")
      .setPositiveButton("Yes") { _, _ ->
        deleteStudent(student, position)
      }
      .setNegativeButton("No", null)
      .show()
  }

  fun deleteStudent(student: StudentModel, position: Int) {
    val removedStudent = students.removeAt(position)
    studentAdapter.notifyItemRemoved(position)  // Cập nhật RecyclerView

    val snackbar = Snackbar.make(findViewById(R.id.main), "Student removed", Snackbar.LENGTH_LONG)
    snackbar.setAction("Undo") {
      students.add(position, removedStudent)  // Khôi phục sinh viên
      studentAdapter.notifyItemInserted(position)  // Cập nhật RecyclerView
    }
    snackbar.show()
  }
}
