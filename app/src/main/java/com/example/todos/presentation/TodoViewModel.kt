package com.example.todos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todos.data.TodoEntity
import com.example.todos.data.TodoDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val dao: TodoDao) : ViewModel() {
    // Hot flow of database data, converted to StateFlow for UI
    val todoList = dao.getAllTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTodo(title: String,isDone: Boolean) {
        if (title.isBlank()) return
        viewModelScope.launch {
            dao.insertTodo(TodoEntity(
                title = title,
                isDone = isDone
            ))
        }
    }

    fun toggleTodo(item: TodoEntity) {
        viewModelScope.launch {
            dao.updateTodo(item.copy(isDone = !item.isDone))
        }
    }

    fun deleteTodo(item: TodoEntity) {
        viewModelScope.launch {
            dao.deleteTodo(item)
        }
    }
}