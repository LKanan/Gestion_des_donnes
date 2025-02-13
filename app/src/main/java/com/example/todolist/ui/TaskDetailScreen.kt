
import android.R.attr.title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist.ViewModel.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(navController: NavController, taskId:Int, viewModel: TaskViewModel= viewModel()){
    Scaffold(topBar = { TopAppBar(title={Text(text="Détail de la tâche")})}) {
        padding ->
        Column(modifier=Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Vous avez sélectionné la tâche : ${taskId}", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {navController.popBackStack()}) {
                Text("Retour")
            }
        }
    }
}