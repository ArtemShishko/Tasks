package com.devshish.tasks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.devshish.tasks.R
import com.devshish.tasks.data.db.entities.TaskItem
import com.devshish.tasks.ui.tasks.AddDialogListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottomsheet.*

class BottomSheetFragment(var addDialogListener: AddDialogListener) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSave.setOnClickListener {
            val task = etTask.text.toString()

            if (task.isEmpty()) {
                Toast.makeText(context, "Enter a task", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val item = TaskItem(task, false)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
    }
}