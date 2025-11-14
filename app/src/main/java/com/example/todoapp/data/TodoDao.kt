// File: app/src/main/java/com/example/todoapp/data/TodoDao.kt
package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAllTodos(): LiveData<List<Todo>>

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}