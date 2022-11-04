package com.example.zlh.customviewassignment.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zlh.customviewassignment.delegates.ProfileDelegate

class ProfileViewHolder(itemView: View, mDelegate: ProfileDelegate) : BaseViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mDelegate.onTapProfile()
        }
    }
}