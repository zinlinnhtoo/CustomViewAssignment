package com.example.zlh.customviewassignment.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.zlh.customviewassignment.delegates.ProfileDelegate

class AddTaskViewHolder(itemView: View, mDelegate: ProfileDelegate) : BaseViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mDelegate.onTapAddTask()
        }
    }
}