package com.learnandroid.taskorganizer20.mock

import com.learnandroid.taskorganizer20.model.TaskEntity
import com.learnandroid.taskorganizer20.model.TaskStatus
import java.time.OffsetDateTime

object MockTaskListGenerator {
    fun getTaskList(): MutableList<TaskEntity> {
        val today = OffsetDateTime.now()
        val taskList = mutableListOf<TaskEntity>()
        taskList.add(
            TaskEntity(
                5,
                "Pay mobile bills",
                "Rs. 850 to Vodafone and Rs. 1050 to Airtel",
                TaskStatus.NEED_ACTION,
                today,
                today.plusDays(4)
            )
        )
        taskList.add(
            TaskEntity(
                4,
                "Visit dentist",
                "Appointment @ 8:30 AM",
                TaskStatus.NEED_ACTION,
                today.minusDays(2),
                today.plusDays(3)
            )
        )
        taskList.add(
            TaskEntity(
                3,
                "Give favorite suit for dry-clean",
                "Do it in the morning",
                TaskStatus.ABANDONED,
                today.minusDays(3),
                today.plusDays(2),
                today.minusDays(1)
            )
        )
        taskList.add(
            TaskEntity(
                2,
                "Fix garage door Fix garage door",
                "Buy 10 2 inch screws, one screw driver",
                TaskStatus.COMPLETED,
                today.minusDays(4),
                today.minusDays(1),
                today.minusDays(1),
                today.minusDays(1)
            )
        )
        taskList.add(
            TaskEntity(
                1,
                "Shopping for Birthday Party",
                "Cake, snacks, candles, balloons, drinks etc.",
                TaskStatus.COMPLETED,
                today.minusDays(5),
                today.minusDays(3),
                today.minusDays(3),
                today.minusDays(3)
            )
        )
        return taskList
    }
}



