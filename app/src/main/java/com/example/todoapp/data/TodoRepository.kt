package com.example.todoapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todoapp.data.remote.ApiService

class TodoRepository(
    private val dao: TodoDao,
    private val api: ApiService
) {

    val allTodos: LiveData<List<Todo>> = dao.getAllTodos()

    suspend fun syncFromServer() {
        val serverTodos = api.getTodos()
        dao.deleteAll()
        dao.insertAll(serverTodos)
    }

    suspend fun insert(todo: Todo) {
        // 1. Simpan ke Room (offline-first)
        dao.insert(todo)

        // 2. Kirim ke server
        try {
            api.addTodo(
                todo.title,
                todo.description,
                todo.deadline,
                todo.priority,
                todo.category
            )
        } catch (e: Exception) {
            Log.e("SYNC", "Gagal kirim ke server", e)
        }
    }

    suspend fun update(todo: Todo) {
        dao.update(todo)

        try {
            api.updateTodo(
                todo.id,
                todo.title,
                todo.description,
                todo.deadline,
                todo.priority,
                todo.category,
                if (todo.isDone) 1 else 0
            )
        } catch (e: Exception) {
            Log.e("SYNC", "Update gagal, pending sync", e)
        }
    }

    suspend fun delete(todo: Todo) {
        dao.delete(todo)

        try {
            api.deleteTodo(todo.id)
        } catch (e: Exception) {
            Log.e("SYNC", "Delete gagal, pending sync", e)
        }
    }

    suspend fun toggleDone(todo: Todo) {
        val updatedTodo = todo.copy(isDone = !todo.isDone)

        dao.update(updatedTodo)

        try {
            api.updateTodo(
                updatedTodo.id,
                updatedTodo.title,
                updatedTodo.description,
                updatedTodo.deadline,
                updatedTodo.priority,
                updatedTodo.category,
                if (updatedTodo.isDone) 1 else 0
            )
        } catch (e: Exception) {
            Log.e("SYNC", "Toggle gagal sync ke server", e)
        }
    }
}