// File: app/src/main/java/com/example/todoapp/adapter/TodoAdapter.kt
package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter(
    private val onEditClick: (Todo) -> Unit,
    private val onDeleteClick: (Todo) -> Unit,
    private val onStatusChange: (Todo) -> Unit
) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.apply {
                tvTitle.text = todo.title
                tvDeadline.text = "Deadline: ${todo.deadline}"
                
                // Set listener ke null dulu untuk menghindari trigger saat sync
                checkboxDone.setOnCheckedChangeListener(null)
                checkboxDone.isChecked = todo.isDone
                
                // Setelah set value, baru attach listener
                checkboxDone.setOnCheckedChangeListener { _, isChecked ->
                    val updatedTodo = todo.copy(isDone = isChecked)
                    onStatusChange(updatedTodo)
                }

                tvCategory.text = "Category: ${todo.category}"
                tvPriority.text = when (todo.priority) {
                    3 -> "Priority: High"
                    2 -> "Priority: Medium"
                    else -> "Priority: Low"
                }


                btnEdit.setOnClickListener {
                    onEditClick(todo)
                }

                btnDelete.setOnClickListener {
                    onDeleteClick(todo)
                }
            }
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
}
