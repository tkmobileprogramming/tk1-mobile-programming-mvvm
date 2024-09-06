package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        _todos.value = emptyList()
    }

    fun addTodo(todo: Todo) {
        val currentList = _todos.value ?: emptyList()
        _todos.value = currentList + todo
    }

    fun deleteTodo(todo: Todo) {
        val currentList = _todos.value ?: emptyList()
        _todos.value = currentList - todo
    }
}
