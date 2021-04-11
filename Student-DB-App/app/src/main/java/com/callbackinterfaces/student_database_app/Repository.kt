package com.callbackinterfaces.student_database_app

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class Repository(application: Application?) {
    val repoStudentDao: StudentDao?
    private val database: StudentDatabase?
    val allStudents: LiveData<List<Student?>?>?
    fun insert(student: Student?) {
        InsertAsyncTask(repoStudentDao).execute(student)
    }

    fun delete(student: Student?) {
        DeleteAsyncTask(repoStudentDao).execute(student)
    }

    fun deleteAll() {
        DeleteAllAsyncTask(repoStudentDao).execute()
    }

    fun update(student: Student?) {
        UpdateAsyncTask(repoStudentDao).execute(student)
    }

    private inner class InsertAsyncTask(private val studentDao: StudentDao?) : AsyncTask<Student?, Void?, Void?>() {
        protected override fun doInBackground(vararg students: Student): Void? {
            studentDao!!.insertStudent(students[0])
            return null
        }
    }

    private inner class DeleteAsyncTask(private val studentDao: StudentDao?) : AsyncTask<Student?, Void?, Void?>() {
        protected override fun doInBackground(vararg students: Student): Void? {
            studentDao!!.deleteStudent(students[0])
            return null
        }
    }

    private inner class DeleteAllAsyncTask(private val studentDao: StudentDao?) : AsyncTask<Void?, Void?, Void?>() {
        protected override fun doInBackground(vararg voids: Void): Void? {
            studentDao!!.deleteAllStudents()
            return null
        }
    }

    private inner class UpdateAsyncTask(private val studentDao: StudentDao?) : AsyncTask<Student?, Void?, Void?>() {
        protected override fun doInBackground(vararg students: Student): Void? {
            studentDao!!.updateStudent(students[0])
            return null
        }
    }

    init {
        database = StudentDatabase.Companion.retrieveInstance(application)
        repoStudentDao = database.getStudentDao()
        allStudents = repoStudentDao.getAllStudents()
    }
}