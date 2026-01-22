package com.example.todos.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todos.data.TodoDatabase

// Factory to create ViewModel with dependencies (Dao)
class TodoViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T{
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)){
            val database = TodoDatabase.getDatabase(context)
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(database.todoDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}