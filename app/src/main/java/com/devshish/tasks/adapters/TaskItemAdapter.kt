package com.devshish.tasks.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devshish.tasks.R
import com.devshish.tasks.data.db.entities.TaskItem
import com.devshish.tasks.ui.tasks.TasksViewModel
import com.devshish.tasks.utils.Constants.Companion.TYPE_HEADER
import com.devshish.tasks.utils.Constants.Companion.TYPE_ITEMS
import kotlinx.android.synthetic.main.tasks_item.view.*

class TaskItemAdapter(
    var items: List<TaskItem>,
    private val viewModel: TasksViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else TYPE_ITEMS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val header = LayoutInflater.from(parent.context)
                    .inflate(R.layout.tasks_list_name, parent, false)
                ViewHolderHeader(header)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.tasks_item, parent, false)
                ViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val curTaskItem = items[position - 1]
                holder.itemView.apply {
                    tvItem.text = curTaskItem.name
                    cbDone.isChecked = curTaskItem.isChecked

                    if (curTaskItem.isChecked)
                        tvItem.paintFlags = tvItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    cbDone.setOnClickListener {
                        if (curTaskItem.isChecked) {
                            curTaskItem.isChecked = false
                            tvItem.paintFlags = tvItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                        } else {
                            curTaskItem.isChecked = true
                            tvItem.paintFlags = tvItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        }
                        viewModel.upsert(curTaskItem)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size + 1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView)
}