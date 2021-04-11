package com.callbackinterfaces.student_database_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.callbackinterfaces.student_database_app.AddEditStudentActivity

class AddEditStudentActivity : AppCompatActivity() {
    private var title: String? = null
    private var done: Button? = null
    private var programme: EditText? = null
    private var name: EditText? = null
    private var id: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_activity_layout)
        done = findViewById(R.id.done)
        programme = findViewById(R.id.programme_edit_text)
        name = findViewById(R.id.std_name_edit_text)
        id = findViewById(R.id.std_id_edit_text)
    }

    override fun onStart() {
        super.onStart()
        title = intent.getStringExtra("REQUEST_SPEC")
        val viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        supportActionBar!!.title = title
        if (intent.getStringExtra("REQUEST_SPEC") == "Edit Student") {
            programme!!.setText(intent.getStringExtra("STD_PROGRAMME"))
            name!!.setText(intent.getStringExtra("STD_NAME"))
            id!!.setText(intent.getStringExtra("STD_ID"))
        }
        done!!.setOnClickListener {
            if (programme!!.text.toString().isEmpty() || name!!.text.toString().isEmpty() || id!!.text.toString().isEmpty()) {
                Toast.makeText(this@AddEditStudentActivity, "Please fill in all spaces", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(Student(name!!.text.toString().trim { it <= ' ' }, id!!.text.toString().trim { it <= ' ' }, programme!!.text.toString().trim { it <= ' ' }))
                setResult(RESULT_OK, null)
                Toast.makeText(this@AddEditStudentActivity, "student added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}