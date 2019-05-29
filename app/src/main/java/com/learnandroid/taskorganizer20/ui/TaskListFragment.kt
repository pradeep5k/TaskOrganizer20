package com.learnandroid.taskorganizer20.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnandroid.taskorganizer20.R
import com.learnandroid.taskorganizer20.repository.TaskRepository
import kotlinx.android.synthetic.main.fragment_task_list.*


class TaskListFragment : TaskOrganizerFragment() {

    private var taskListRecyclerViewAdapter:TaskListRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskRecyclerView.setHasFixedSize(true)
        taskRecyclerView.layoutManager = LinearLayoutManager(context)
        taskListRecyclerViewAdapter = TaskListRecyclerViewAdapter(view.context)
        taskRecyclerView.adapter = taskListRecyclerViewAdapter
        taskListRecyclerViewAdapter?.setTaskEntityList(TaskRepository.getInstance(this.context!!).taskList)
    }
}
