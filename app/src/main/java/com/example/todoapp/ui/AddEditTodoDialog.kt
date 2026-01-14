// File: app/src/main/java/com/example/todoapp/ui/AddEditTodoDialog.kt
package com.example.todoapp.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.DialogAddEditTodoBinding
import java.text.SimpleDateFormat
import java.util.*

class AddEditTodoDialog(
    private val todo: Todo? = null,
    private val onSave: (Todo) -> Unit
) : DialogFragment() {

    private var _binding: DialogAddEditTodoBinding? = null
    private val binding get() = _binding!!

    private var selectedDeadline: String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddEditTodoBinding.inflate(layoutInflater)

        // Setup Spinner for Priority
        val priorities = arrayOf("Low", "Medium", "High")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPriority.adapter = adapter

        // Set data if editing
        todo?.let {
            binding.etTitle.setText(it.title)
            binding.etDescription.setText(it.description)
            binding.tvDeadline.text = it.deadline
            selectedDeadline = it.deadline
            binding.spinnerPriority.setSelection(it.priority - 1)
            binding.etCategory.setText(it.category)
            binding.checkboxStatus.isChecked = it.isDone
        }

        // DatePicker
        binding.btnSelectDeadline.setOnClickListener {
            showDatePicker()
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(if (todo == null) "Add Task" else "Edit Task")
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ ->
                saveTodo()
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val date = Calendar.getInstance()
                date.set(year, month, day)
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                selectedDeadline = format.format(date.time)
                binding.tvDeadline.text = selectedDeadline
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun saveTodo() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()
        val isDone = binding.checkboxStatus.isChecked
        val category = binding.etCategory.text.toString().trim()
        val priority = binding.spinnerPriority.selectedItemPosition + 1

        if (title.isEmpty()) {
            Toast.makeText(context, "Title is required", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedDeadline.isEmpty()) {
            Toast.makeText(context, "Deadline is required", Toast.LENGTH_SHORT).show()
            return
        }

        if (category.isEmpty()) {
            Toast.makeText(context, "Category is required", Toast.LENGTH_SHORT).show()
            return
        }

        val newTodo = Todo(
            id = todo?.id ?: 0,
            title = title,
            description = description,
            deadline = selectedDeadline,
            priority = priority,
            category = category,
            isDone = isDone
        )

        onSave(newTodo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
