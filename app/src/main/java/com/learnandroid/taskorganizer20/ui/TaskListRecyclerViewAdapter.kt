package com.learnandroid.taskorganizer20.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learnandroid.taskorganizer20.R
import com.learnandroid.taskorganizer20.model.TaskEntity
import com.learnandroid.taskorganizer20.model.TaskStatus
import kotlinx.android.synthetic.main.recycler_task_list_item.view.*

class TaskListRecyclerViewAdapter(private val context:Context):
    RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskHolder>() {
    private var taskList:MutableList<TaskEntity> = mutableListOf()
    private var selectedItemPos:Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val inflatedView = LayoutInflater.from(context)
        return TaskHolder(inflatedView.inflate(R.layout.recycler_task_list_item, parent, false))
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val taskEntity = taskList[position]
        holder.bind(taskEntity)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (holder.adapterPosition == RecyclerView.NO_POSITION) return
                notifyItemChanged(selectedItemPos) // deselect the previously selected item
                selectedItemPos = holder.adapterPosition
                notifyItemChanged(selectedItemPos) // select the newly selected item

                selectedItemPos = holder.adapterPosition
                (context as ITaskFragmentListener).onTaskSelect(taskList[selectedItemPos])
            }
        })
        holder.itemView.isSelected = selectedItemPos == position
    }

    fun setTaskEntityList(tasks: MutableList<TaskEntity>){
        taskList = tasks
        notifyDataSetChanged()
    }

    class TaskHolder(private val view:View):RecyclerView.ViewHolder(view) {
        fun bind(taskEntity: TaskEntity){
            view.taskTitleTextView.text = taskEntity.title
            view.statusImageView.setImageResource(when(taskEntity.status) {
                TaskStatus.NEED_ACTION -> R.drawable.ic_baseline_hourglass_empty_24px
                TaskStatus.ABANDONED -> R.drawable.ic_baseline_delete_outline_24px
                TaskStatus.COMPLETED -> R.drawable.ic_baseline_done_24px
            })
        }
    }
}

