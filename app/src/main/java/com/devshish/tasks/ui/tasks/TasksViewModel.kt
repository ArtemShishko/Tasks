package com.devshish.tasks.ui.tasks

import androidx.lifecycle.ViewModel
import com.devshish.tasks.data.db.entities.TaskItem
import com.devshish.tasks.data.repositories.TasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(
    private val repository: TasksRepository
) : ViewModel() {

    fun upsert(item: TaskItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: TaskItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllTaskItems() = repository.getAllTaskItems()
}