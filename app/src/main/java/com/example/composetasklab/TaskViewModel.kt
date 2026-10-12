package com.example.composetasklab

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class TaskItem(
    val id: Int,
    val title: String,
    var isDone: Boolean = false
)

class TaskViewModel : ViewModel() {

    val tasks = mutableStateListOf(
        TaskItem(1, "学习 Column 和 Row", true),
        TaskItem(2, "学习状态管理", false),
        TaskItem(3, "完成 Compose 实验", false)
    )

    val inputText = mutableStateOf("")
    private var nextId = 4

    val doneCount: Int
        get() = tasks.count { it.isDone }

    val totalCount: Int
        get() = tasks.size

    fun addTask() {
        val text = inputText.value.trim()
        if (text.isNotEmpty()) {
            tasks.add(TaskItem(nextId++, text, false))
            inputText.value = ""
        }
    }

    fun toggleTask(id: Int) {
        val task = tasks.find { it.id == id } ?: return
        task.isDone = !task.isDone
    }

    fun deleteTask(id: Int) {
        tasks.removeAll { it.id == id }
    }
}