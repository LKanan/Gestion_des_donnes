package com.example.todolist.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskDialog(
    context: Context,
    onDismiss: () -> Unit,
    onConfirm: (String, LocalDate, LocalDate) -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var dateDebut by remember { mutableStateOf(LocalDate.now()) }
    var dateFin by remember { mutableStateOf(LocalDate.now().plusDays(7)) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    fun showDatePicker(initialDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
        val calendar = Calendar.getInstance().apply {
            set(initialDate.year, initialDate.monthValue - 1, initialDate.dayOfMonth)
        }
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Ajouter une nouvelle tâche") },
        text = {
            Column {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Nom de la tâche") }
                )

                Spacer(modifier = Modifier.height(16.dp))

// Sélection de la date de début
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker(dateDebut) { dateDebut = it } }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date de début : ${dateDebut.format(dateFormatter)}",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Modifier date début",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }

// Sélection de la date de fin
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker(dateFin) { dateFin = it } }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date de fin : ${dateFin.format(dateFormatter)}",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Modifier date fin",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(taskName, dateDebut, dateFin) }) {
                Text("Ajouter")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Annuler")
            }
        }
    )
}