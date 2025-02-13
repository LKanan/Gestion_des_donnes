package com.example.todolist.data

import com.example.todolist.model.TaskDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(name: String, dateDebut: LocalDate, dateFin: LocalDate) {
        val task = Task(
            name = name,
            dateDebut = dateDebut,
            dateFin = dateFin
        )
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}