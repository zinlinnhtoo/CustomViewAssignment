package com.example.zlh.customviewassignment.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zlh.customviewassignment.R
import com.example.zlh.customviewassignment.views.viewholders.TaskCategoryViewHolder

class TaskCategoryAdapter : RecyclerView.Adapter<TaskCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCategoryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.view_holder_task_category,parent,false)
    return TaskCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskCategoryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}