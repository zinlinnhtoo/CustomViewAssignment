package com.example.zlh.customviewassignment.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zlh.customviewassignment.R
import com.example.zlh.customviewassignment.adapters.BottomSheetTaskListAdapter
import com.example.zlh.customviewassignment.adapters.ProfileListAdapter
import com.example.zlh.customviewassignment.adapters.TaskListAdapter
import com.example.zlh.customviewassignment.delegates.ProfileDelegate
import com.example.zlh.customviewassignment.utils.OverlappingItemDecoration
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_profile.*

class MainActivity : AppCompatActivity(), ProfileDelegate {

    private val tabList = listOf("Project Tasks", "Contacts", "About you")
    private lateinit var mProfileListAdapter: ProfileListAdapter
    private lateinit var mTaskListAdapter: TaskListAdapter
    private lateinit var mBottomSheetTaskListAdapter: BottomSheetTaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        setUpBottomSheet()
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        tabList.forEach {
            tabLayoutProfile.newTab().apply {
                text = it
                tabLayoutProfile.addTab(this)
            }
        }
    }

    private fun setUpBottomSheet() {
        val sheet = BottomSheetBehavior.from(bottomSheetProfile)
        btnCloseBottomSheet.setOnClickListener {
            sheet.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun setUpRecyclerView() {
        mProfileListAdapter = ProfileListAdapter(this)
        rvProfile.adapter = mProfileListAdapter
        rvProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvProfile.addItemDecoration(OverlappingItemDecoration())

        mTaskListAdapter = TaskListAdapter()
        rvTaskList.apply {
            adapter = mTaskListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

        mBottomSheetTaskListAdapter = BottomSheetTaskListAdapter()
        rvProfileTasks.adapter = mBottomSheetTaskListAdapter
        rvProfileTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTapProfile() {
        val sheet = BottomSheetBehavior.from(bottomSheetProfile)
        when {
            sheet.state != BottomSheetBehavior.STATE_EXPANDED -> {
                sheet.state = BottomSheetBehavior.STATE_EXPANDED
            }
            else -> sheet.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onTapAddTask() {
        startActivity(CreateNewTaskActivity.newIntent(this))
    }
}