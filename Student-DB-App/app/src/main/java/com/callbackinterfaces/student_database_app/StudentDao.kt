package com.callbackinterfaces.student_database_app

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student?)

    @Update
    fun updateStudent(student: Student?)

    @Delete
    fun deleteStudent(student: Student?)

    @Query("DELETE FROM student_table")
    fun deleteAllStudents()

    @get:Query("SELECT * FROM student_table")
    val allStudents: LiveData<List<Student?>?>?
}