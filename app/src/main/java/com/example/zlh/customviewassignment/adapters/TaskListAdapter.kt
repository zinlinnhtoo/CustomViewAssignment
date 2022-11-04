package com.example.zlh.customviewassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zlh.customviewassignment.R
import com.example.zlh.customviewassignment.views.viewholders.ProfileViewHolder
import com.example.zlh.customviewassignment.views.viewholders.TaskViewHolder

class TaskListAdapter: RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 4
    }
}