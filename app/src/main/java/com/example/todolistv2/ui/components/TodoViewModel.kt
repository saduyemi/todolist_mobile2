package com.example.todolistv2.ui.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Task (val id: Int, val name: String, val isCompleted: Boolean = false)

class TodoViewModel : ViewModel() {
    private val _input = MutableStateFlow("")
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())

    private var nextId = 0 // delete this later, will use room to generate unique ids

    val input = _input.asStateFlow()
    val tasks = _tasks.asStateFlow()

    fun updateInput(text: String) {
        _input.value = text
    }

    fun addTask() {
        val taskName = _input.value.trim()
        if (taskName.isNotEmpty()) {
            _tasks.value = _tasks.value + Task(id = nextId++, name = _input.value)
            _input.value = ""
        }
    }

    fun renameTask(id: Int, name: String) {
        /*_tasks.value = _tasks.value.toMutableList().also { listT ->
           val index = listT.indexOfFirst { it.id == id }
            if (index != -1) {
                listT[index] = listT[index].copy(name = name)
            } else {
                print("item not found")
            }
        } <- both works */

        // _tasks will now be assigned a copy of the orignal list except one of the orignal elems is modified
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) {
                task.copy(name = name)
            } else {
                task
            }
        }
    }

    fun checkTask(id: Int, isChecked: Boolean) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) {
                task.copy(isCompleted = isChecked)
            } else {
                task
            }
        }
    }

    fun deleteTask(id: Int) {
        val temp = _tasks.value.toMutableList()
        val index = _tasks.value.indexOfFirst { it.id == id }
        temp.removeAt(index);
        _tasks.value = temp
    }

    fun reset() {
        _input.value = ""
        _tasks.value = emptyList()
    }
}