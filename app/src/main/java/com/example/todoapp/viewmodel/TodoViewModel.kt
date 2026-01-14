package com.example.todoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.Todo
import com.example.todoapp.data.TodoDatabase
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.data.remote.ApiClient
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    val allTodos: LiveData<List<Todo>>

    init {
        val dao = TodoDatabase.getDatabase(application).todoDao()
        val api = ApiClient.create()
        repository = TodoRepository(dao, api)
        allTodos = repository.allTodos

        // 🔎 VALIDASI KONEKSI KE SERVER
        viewModelScope.launch {
            try {
                val todos = api.getTodos()
                Log.d("API_TEST", "Server todos: ${todos.size}")
            } catch (e: Exception) {
                Log.e("API_TEST", "Gagal konek API", e)
            }
        }

        viewModelScope.launch {
            try {
                repository.syncFromServer()
                Log.d("SYNC", "Room berhasil sync dari server")
            } catch (e: Exception) {
                Log.e("SYNC", "Gagal sync, pakai data lokal", e)
            }
        }
    }

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun update(todo: Todo) = viewModelScope.launch {
        repository.update(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        repository.delete(todo)
    }
}