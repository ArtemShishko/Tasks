package com.devshish.tasks.ui.tasks

import com.devshish.tasks.data.db.entities.TaskItem

interface AddDialogListener {

    fun onAddButtonClicked(item: TaskItem)
}