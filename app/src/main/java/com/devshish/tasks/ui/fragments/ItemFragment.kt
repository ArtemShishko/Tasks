package com.devshish.tasks.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.devshish.tasks.R
import com.devshish.tasks.adapters.TaskItemAdapter
import com.devshish.tasks.ui.tasks.TasksActivity
import com.devshish.tasks.ui.tasks.TasksViewModel

class ItemFragment : Fragment(R.layout.fragment_item) {

    lateinit var viewModel: TasksViewModel
    lateinit var itemAdapter: TaskItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as TasksActivity).viewModel
    }
}