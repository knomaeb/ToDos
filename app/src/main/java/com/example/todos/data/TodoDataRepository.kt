package com.example.todos.data

import com.example.todos.domain.Todo
import com.example.todos.domain.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoDataRepository (private val todoDao: TodoDao): TodoRepository {
    override fun getTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos().map { list ->
            list.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    isDone = entity.isDone
                )
            }
        }
    }

    override suspend fun addTodo(todo: Todo) {
        todoDao.insertTodo(TodoEntity(title = todo.title, isDone = todo.isDone))
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(TodoEntity(title = todo.title, isDone = todo.isDone))
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(TodoEntity(title = todo.title, isDone = todo.isDone))
    }
}