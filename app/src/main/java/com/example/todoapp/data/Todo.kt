// File: app/src/main/java/com/example/todoapp/data/Todo.kt
package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val deadline: String,
    val priority: Int,
    val category: String,
    val isDone: Boolean = false
)
