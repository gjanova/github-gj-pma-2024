package com.example.myapp015asharedtasklist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp015asharedtasklist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<Task>() // Lokální seznam úkolů
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializace RecyclerView
        taskAdapter = TaskAdapter(tasks) { task ->
            updateTask(task) // Callback pro změnu úkolu
        }
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTasks.adapter = taskAdapter

        // Simulace načtení dat
        loadTasks()
    }

    private fun loadTasks() {
        tasks.add(Task("1", "Buy groceries", isCompleted = false, assignedTo = "Alice"))
        tasks.add(Task("2", "Clean the house", isCompleted = false, assignedTo = ""))
        tasks.add(Task("3", "Prepare presentation", isCompleted = true, assignedTo = "Bob"))
        taskAdapter.notifyDataSetChanged()
    }

    private fun updateTask(task: Task) {
        // Tady později napojíme Firestore update
        println("Task updated: ${task.name}, completed: ${task.isCompleted}")
    }
}