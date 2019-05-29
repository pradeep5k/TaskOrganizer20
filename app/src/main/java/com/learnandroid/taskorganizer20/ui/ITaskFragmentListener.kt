package com.learnandroid.taskorganizer20.ui

import com.learnandroid.taskorganizer20.model.TaskEntity

interface ITaskFragmentListener {
    fun onTaskSelect(taskEntity: TaskEntity)
    fun registerFragment(fragment: TaskOrganizerFragment)
    fun unregisterFragment(fragment: TaskOrganizerFragment)
}



