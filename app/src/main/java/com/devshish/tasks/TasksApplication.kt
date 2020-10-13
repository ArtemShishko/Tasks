package com.devshish.tasks

import android.app.Application
import com.devshish.tasks.data.db.TasksDatabase
import com.devshish.tasks.data.repositories.TasksRepository
import com.devshish.tasks.ui.tasks.TasksViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TasksApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@TasksApplication))
        bind() from singleton { TasksDatabase(instance()) }
        bind() from singleton { TasksRepository(instance()) }
        bind() from provider {
            TasksViewModelFactory(instance())
        }
    }
}