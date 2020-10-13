package com.devshish.tasks.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devshish.tasks.data.db.entities.TaskItem

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: TaskItem)

    @Delete
    suspend fun delete(item: TaskItem)

    @Query("SELECT * FROM tasks_items")
    fun getAllTaskItems(): LiveData<List<TaskItem>>
}