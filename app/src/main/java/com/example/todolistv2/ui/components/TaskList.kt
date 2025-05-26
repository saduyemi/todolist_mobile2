package com.example.todolistv2.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun TasksList(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier
) {
    val tasks by viewModel.tasks.collectAsState()

    LazyColumn (
        modifier = modifier.fillMaxWidth()
    ) {
        itemsIndexed(tasks) { index, task ->
            TaskItem(
                task = task,
                viewModel = viewModel,
                checkTask = {isChecked -> viewModel.checkTask(index, isChecked)}
            )
        }
    }

}