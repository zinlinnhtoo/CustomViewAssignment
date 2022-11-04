package com.example.zlh.customviewassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zlh.customviewassignment.R
import com.example.zlh.customviewassignment.views.viewholders.BottomSheetTaskViewHolder

class BottomSheetTaskListAdapter: RecyclerView.Adapter<BottomSheetTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_task_bottomsheet, parent, false)
        return BottomSheetTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: BottomSheetTaskViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }
}