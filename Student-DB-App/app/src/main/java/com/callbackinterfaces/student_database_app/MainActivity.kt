package com.callbackinterfaces.student_database_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.callbackinterfaces.student_database_app.MainActivity
import com.callbackinterfaces.student_database_app.StudentAdapter.EditStudentIntentListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), EditStudentIntentListener {
    private var recyclerView: RecyclerView? = null
    private var viewModel: ViewModel? = null
    private var button: FloatingActionButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        button = findViewById(R.id.add_button)
        val adapter = StudentAdapter(this)
        recyclerView.setAdapter(adapter)
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        (viewModel as StudentsViewModel).allStudents.observe(this, { students -> adapter.setStudents(students) })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewModel as StudentsViewModel).delete(adapter.getStudentAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Student deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, AddEditStudentActivity::class.java)
            intent.putExtra("REQUEST_SPEC", "Add Student")
            startActivityForResult(intent, ADD_EDIT_REQUEST)
        })
    }

    override fun onReceiveIntent(intent: Intent?) {
        startActivityForResult(intent, ADD_EDIT_REQUEST)
    }

    companion object {
        private const val ADD_EDIT_REQUEST = 1
    }
}