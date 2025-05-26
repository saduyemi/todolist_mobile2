package com.example.todolistv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistv2.ui.components.TaskScreen
import com.example.todolistv2.ui.components.TodoViewModel
import com.example.todolistv2.ui.theme.TodoListv2Theme

class MainActivity : ComponentActivity() {
    //private val viewModel by viewModels<TodoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListv2Theme {
                val viewModel = viewModel<TodoViewModel>()
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TaskScreen(viewModel = viewModel)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListv2Theme {
    }
}