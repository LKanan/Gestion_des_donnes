import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TaskScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            TaskScreen(navController)
        }
        composable("task_detail/{taskId}"){
            backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            taskId?.let{TaskDetailScreen(navController, taskId)}
        }
    }
}
