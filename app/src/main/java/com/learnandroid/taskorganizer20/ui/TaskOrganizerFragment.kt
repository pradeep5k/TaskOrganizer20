package com.learnandroid.taskorganizer20.ui

import android.content.Context
import androidx.fragment.app.Fragment

open class TaskOrganizerFragment:Fragment() {
    protected var listener:ITaskFragmentListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ITaskFragmentListener
        if (listener == null) {
            throw ClassCastException("$context must implement ITaskFragmentListener")
        }
        listener?.registerFragment(this)
    }


    override fun onDetach() {
        super.onDetach()
        listener?.unregisterFragment(this)
        listener = null
    }
}


