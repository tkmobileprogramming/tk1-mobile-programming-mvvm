package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        val inputBox = findViewById<EditText>(R.id.inputBox)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = TodoAdapter { todo -> viewModel.deleteTodo(todo) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.todos.observe(this, Observer { todos ->
            adapter.submitList(todos)
        })

        submitButton.setOnClickListener {
            val text = inputBox.text.toString()
            if (text.isNotEmpty()) {
                viewModel.addTodo(Todo(text))
                inputBox.text.clear()
            }
        }
    }
}
