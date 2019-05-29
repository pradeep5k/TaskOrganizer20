package com.learnandroid.taskorganizer20.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.learnandroid.taskorganizer20.R
import com.learnandroid.taskorganizer20.model.TaskEntity
import com.learnandroid.taskorganizer20.model.TaskStatus
import com.learnandroid.taskorganizer20.repository.TaskRepository
import kotlinx.android.synthetic.main.fragment_task_detail.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class TaskDetailFragment : TaskOrganizerFragment() {
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // a work around for the bug in the SafeArgs
        if (arguments == null) arguments = Bundle()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(args.taskId == -1) return
        TaskRepository.getInstance(this.context!!).taskList.find {
            it.id == args.taskId
        }?.let {
            updateUI(it)
        }
    }
    fun updateUI(task: TaskEntity) {
        resetUI()

        taskTitleTextView.text = task.title
        taskNoteTextView.text = task.note
        taskStatusTextView.text = task.status.text

        taskStatusTextView.setTextColor(
            when (task.status) {
                TaskStatus.NEED_ACTION -> resources.getColor(R.color.need_action, context?.theme)
                TaskStatus.COMPLETED -> resources.getColor(R.color.completed_action, context?.theme)
                TaskStatus.ABANDONED -> resources.getColor(R.color.abandoned_action, context?.theme)
            }
        )

        task.createdOn?.let {
            taskCreatedOnTextView.text = it.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
            ).toString()
        }

        task.dueOn?.let {
            taskDueOnTextView.text = it.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
            ).toString()
        }

        task.updatedOn?.let {
            taskUpdatedOnTextView.text = it.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
            ).toString()
        }

        task.completedOn?.let {
            taskCompletedOnTextView.text = it.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
            ).toString()
        }

        snoozeImageView.visibility = View.INVISIBLE
        taskInfoGridLayout.visibility = View.VISIBLE
    }

    private fun resetUI() {
        taskTitleTextView.text = ""
        taskCreatedOnTextView.text = getText(R.string.dash_dash)
        taskDueOnTextView.text = getText(R.string.dash_dash)
        taskCompletedOnTextView.text = getText(R.string.dash_dash)
        taskUpdatedOnTextView.text = getText(R.string.dash_dash)
        taskInfoGridLayout.visibility = View.INVISIBLE
        snoozeImageView.visibility = View.VISIBLE
    }
}
