package com.devshish.tasks.ui.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.devshish.tasks.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TasksActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: TasksViewModelFactory by instance()

    lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        viewModel = ViewModelProvider(this, factory).get(TasksViewModel::class.java)
    }
}