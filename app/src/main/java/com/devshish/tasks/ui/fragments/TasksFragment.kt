package com.devshish.tasks.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devshish.tasks.R
import com.devshish.tasks.adapters.TaskItemAdapter
import com.devshish.tasks.data.db.entities.TaskItem
import com.devshish.tasks.ui.tasks.AddDialogListener
import com.devshish.tasks.ui.tasks.TasksActivity
import com.devshish.tasks.ui.tasks.TasksViewModel
import com.devshish.tasks.utils.Constants.Companion.DIALOG_TAG
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tasks.*

class TasksFragment : Fragment(R.layout.fragment_tasks) {

    lateinit var viewModel: TasksViewModel
    lateinit var tasksAdapter: TaskItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as TasksActivity).viewModel

        setupRecyclerView()
        setupBottomNavigation()
        setupFloatingActionButton()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = tasksAdapter.items[position - 1]
                viewModel.delete(task)
                Snackbar.make(view, "Successfully deleted task", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.upsert(task)
                    }
                    show()
                    anchorView = fab
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvItems)
        }
    }

    private fun setupBottomNavigation() {
        bottomAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.miMore -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }
    }

    private fun setupFloatingActionButton() {
        rvItems.setOnScrollChangeListener { _, _, _, _, oldScrollY ->
            if (oldScrollY < 0) fab.hide() else fab.show()
        }

        fab.setOnClickListener {
            BottomSheetFragment(object : AddDialogListener {
                override fun onAddButtonClicked(item: TaskItem) {
                    viewModel.upsert(item)
                }
            }).show(parentFragmentManager, DIALOG_TAG)
        }
    }

    private fun setupRecyclerView() {
        tasksAdapter = TaskItemAdapter(listOf(), viewModel)
        rvItems.apply {
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        viewModel.getAllTaskItems().observe(viewLifecycleOwner, {
            tasksAdapter.items = it
            tasksAdapter.notifyDataSetChanged()
        })
    }
}
