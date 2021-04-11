package com.callbackinterfaces.student_database_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
class Student     //initialising private members/sqlight student_table attributes.
(val studentName: String, //designating attributes for out Student instance.
 @field:PrimaryKey val studentID: String, val programme: String)