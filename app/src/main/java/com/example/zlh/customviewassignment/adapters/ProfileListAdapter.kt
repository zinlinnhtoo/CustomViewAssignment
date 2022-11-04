package com.example.zlh.customviewassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zlh.customviewassignment.R
import com.example.zlh.customviewassignment.delegates.ProfileDelegate
import com.example.zlh.customviewassignment.views.viewholders.AddTaskViewHolder
import com.example.zlh.customviewassignment.views.viewholders.BaseViewHolder
import com.example.zlh.customviewassignment.views.viewholders.ProfileViewHolder

class ProfileListAdapter(private val mDelegate: ProfileDelegate): RecyclerView.Adapter<BaseViewHolder>() {

    private val VIEW_TYPE_PROFILE = 1
    private val VIEW_TYPE_ADD_BUTTON = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_PROFILE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_profile, parent, false)
                ProfileViewHolder(view, mDelegate)
            }
            VIEW_TYPE_ADD_BUTTON -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_add_task, parent, false)
                AddTaskViewHolder(view, mDelegate)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_profile, parent, false)
                ProfileViewHolder(view, mDelegate)
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            3 -> {
                VIEW_TYPE_ADD_BUTTON
            }
            else -> VIEW_TYPE_PROFILE
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    }
}