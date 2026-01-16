package com.example.todos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_items ORDER BY isDone ASC, id DESC")
    fun getAllTodos(): Flow<List<ToDoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(item: ToDoEntity)

    @Delete
    suspend fun deleteTodo(item: ToDoEntity)

    @Update
    suspend fun updateTodo(item: ToDoEntity)
}