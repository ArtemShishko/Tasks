package com.devshish.tasks.data.repositories

import com.devshish.tasks.data.db.TasksDatabase
import com.devshish.tasks.data.db.entities.TaskItem

class TasksRepository(
    private val db: TasksDatabase
) {

    suspend fun upsert(item: TaskItem) = db.getTasksDao().upsert(item)

    suspend fun delete(item: TaskItem) = db.getTasksDao().delete(item)

    fun getAllTaskItems() = db.getTasksDao().getAllTaskItems()
}