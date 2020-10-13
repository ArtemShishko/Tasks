package com.devshish.tasks.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_items")
data class TaskItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_isChecked")
    var isChecked: Boolean = false,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}