package com.example.zlh.customviewassignment.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zlh.customviewassignment.R
import com.example.zlh.customviewassignment.adapters.ProfileListAdapter
import com.example.zlh.customviewassignment.adapters.TaskCategoryAdapter
import com.example.zlh.customviewassignment.delegates.ProfileDelegate
import com.example.zlh.customviewassignment.utils.OverlappingItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_new_task.*
import kotlinx.android.synthetic.main.activity_main.*

class CreateNewTaskActivity : AppCompatActivity(), ProfileDelegate {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CreateNewTaskActivity::class.java)

        }
    }

    lateinit var mProfileAdapter: ProfileListAdapter
    lateinit var mTaskCategoryAdapter: TaskCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mProfileAdapter = ProfileListAdapter(this)
        rvAssignee.adapter = mProfileAdapter
        rvAssignee.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAssignee.addItemDecoration(OverlappingItemDecoration())

        mTaskCategoryAdapter = TaskCategoryAdapter()
        rvTaskCategory.adapter = mTaskCategoryAdapter
        rvTaskCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onTapProfile() {
        Snackbar.make(window.decorView, "Tap Profile", Snackbar.LENGTH_LONG).show()
    }

    override fun onTapAddTask() {
        Snackbar.make(window.decorView, "Tap Add", Snackbar.LENGTH_LONG).show()
    }
}