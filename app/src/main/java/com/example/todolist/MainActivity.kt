package com.example.todolist

import NavigationGraph
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TaskScreen
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {
                NavigationGraph()
                }
            }
        }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    NavigationGraph()
}