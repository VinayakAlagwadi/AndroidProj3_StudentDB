package com.callbackinterfaces.student_database_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StudentsViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: Repository
    val allStudents: LiveData<List<Student?>?>?
    fun insert(student: Student?) {
        repo.insert(student)
    }

    fun delete(student: Student?) {
        repo.delete(student)
    }

    fun update(student: Student?) {
        repo.delete(student)
    }

    fun deleteAll() {
        repo.deleteAll()
    }

    init {
        repo = Repository(application)
        allStudents = repo.allStudents
    }
}