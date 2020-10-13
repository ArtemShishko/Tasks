package com.devshish.tasks.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devshish.tasks.data.repositories.TasksRepository

@Suppress("UNCHECKED_CAST")
class TasksViewModelFactory(
    private val repository: TasksRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(repository) as T
    }
}