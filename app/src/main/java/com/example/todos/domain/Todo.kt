package com.example.todos.domain

data class Todo(
    val id: Int = 0,
    val title: String,
    var isDone: Boolean = false
)
