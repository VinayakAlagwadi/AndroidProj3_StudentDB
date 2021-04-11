package com.callbackinterfaces.student_database_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.callbackinterfaces.student_database_app.StudentAdapter.StudentHolder

class StudentAdapter(private val intentListener: EditStudentIntentListener) : RecyclerView.Adapter<StudentHolder>() {
    interface EditStudentIntentListener {
        fun onReceiveIntent(intent: Intent?)
    }

    private var students: List<Student>? = null
    fun setStudents(students: List<Student>?) {
        this.students = students
        notifyDataSetChanged()
    }

    fun getStudentAt(position: Int): Student {
        return students!![position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        return StudentHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false), null)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.studentName.text = students!![position].studentName
        holder.studentID.text = students!![position].studentID.toString()
        holder.programme.text = students!![position].programme
        holder.listener = View.OnClickListener {
            val intent = Intent(intentListener as MainActivity, AddEditStudentActivity::class.java)
            intent.putExtra("REQUEST_SPEC", "Edit Student")
            intent.putExtra("STD_NAME", students!![position].studentName)
            intent.putExtra("STD_ID", students!![position].studentID)
            intent.putExtra("STD_PROGRAMME", students!![position].programme)
            intentListener.onReceiveIntent(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (students != null) students!!.size else 0
    }

    inner class StudentHolder(itemView: View, listener: View.OnClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val studentName: TextView
        val studentID: TextView
        val programme: TextView
        val listener: View.OnClickListener?
        override fun onClick(v: View) {
            listener!!.onClick(v)
        }

        init {
            studentName = itemView.findViewById(R.id.student)
            studentID = itemView.findViewById(R.id.id)
            programme = itemView.findViewById(R.id.programme)
            this.listener = listener
            itemView.setOnClickListener(this)
        }
    }
}