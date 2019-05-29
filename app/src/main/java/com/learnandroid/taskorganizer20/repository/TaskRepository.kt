package com.learnandroid.taskorganizer20.repository

import android.content.Context
import com.learnandroid.taskorganizer20.mock.MockTaskListGenerator

class TaskRepository private constructor(context: Context) {
    val taskList = MockTaskListGenerator.getTaskList()

    companion object {
        @Volatile
        private var INSTANCE: TaskRepository? = null

        @Synchronized
        fun getInstance(context: Context): TaskRepository {
            if (INSTANCE != null) return INSTANCE as TaskRepository
            INSTANCE = TaskRepository(context)
            return INSTANCE as TaskRepository
        }
    }
}



