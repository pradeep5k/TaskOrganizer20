package com.learnandroid.taskorganizer20.model

import java.time.OffsetDateTime

data class TaskEntity(
    val id:Int? = null,
    val title: String = "",
    val note:String = "",
    val status:TaskStatus = TaskStatus.NEED_ACTION,
    val createdOn:OffsetDateTime? = null,
    val dueOn:OffsetDateTime? = null,
    val updatedOn:OffsetDateTime? = null,
    val completedOn:OffsetDateTime? = null
)


