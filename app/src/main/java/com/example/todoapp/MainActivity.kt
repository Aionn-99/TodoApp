// File: app/src/main/java/com/example/todoapp/MainActivity.kt
package com.example.todoapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapter.TodoAdapter
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.AddEditTodoDialog
import com.example.todoapp.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupFab()
        observeTodos()
    }

    private fun setupRecyclerView() {
        adapter = TodoAdapter(
            onEditClick = { todo -> showEditDialog(todo) },
            onDeleteClick = { todo -> showDeleteConfirmation(todo) },
            onStatusChange = { todo -> viewModel.update(todo) }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupFab() {
        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun observeTodos() {
        viewModel.allTodos.observe(this) { todos ->
            adapter.submitList(todos)
        }
    }

    private fun showAddDialog() {
        val dialog = AddEditTodoDialog(
            todo = null,
            onSave = { todo ->
                viewModel.insert(todo)
            }
        )
        dialog.show(supportFragmentManager, "AddTodoDialog")
    }

    private fun showEditDialog(todo: Todo) {
        val dialog = AddEditTodoDialog(
            todo = todo,
            onSave = { updatedTodo ->
                viewModel.update(updatedTodo)
            }
        )
        dialog.show(supportFragmentManager, "EditTodoDialog")
    }

    private fun showDeleteConfirmation(todo: Todo) {
        AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want to delete \"${todo.title}\"?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.delete(todo)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}