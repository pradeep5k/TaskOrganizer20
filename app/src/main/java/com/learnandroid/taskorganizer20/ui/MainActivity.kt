package com.learnandroid.taskorganizer20.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.learnandroid.taskorganizer20.R
import com.learnandroid.taskorganizer20.model.TaskEntity

class MainActivity : AppCompatActivity(), ITaskFragmentListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private var taskLandingFragment: TaskLandingFragment? = null
    private var taskDetailFragment: TaskDetailFragment? = null
    private var taskListFragment: TaskListFragment? = null
    private var taskEditorFragment: TaskEditorFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navController = this.findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun registerFragment(fragment: TaskOrganizerFragment) {
        when (fragment::class) {
            TaskDetailFragment::class -> taskDetailFragment = fragment as TaskDetailFragment
            TaskLandingFragment::class -> taskLandingFragment = fragment as TaskLandingFragment
            TaskListFragment::class -> taskListFragment = fragment as TaskListFragment
            TaskEditorFragment::class -> taskEditorFragment = fragment as TaskEditorFragment
        }
    }

    override fun unregisterFragment(fragment: TaskOrganizerFragment) {
        when (fragment) {
            taskDetailFragment -> taskDetailFragment = null
            taskLandingFragment -> taskLandingFragment = null
            taskListFragment -> taskListFragment = null
            taskEditorFragment -> taskEditorFragment = null
        }
    }

    override fun onTaskSelect(taskEntity: TaskEntity) {
        taskEntity.id?.let { id ->
            if (taskDetailFragment != null && taskDetailFragment?.isVisible!!) {
                taskDetailFragment?.updateUI(taskEntity)
            } else {
                val navDirection = TaskLandingFragmentDirections.actionTaskLandingToTaskDetail(id)
                navController.navigate(navDirection)
            }
        }
    }
}


