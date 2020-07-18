package com.gmail.cities.presentation.common

import android.view.View

class DebouncingOnClickListener(private val clickAction: (view: View) -> Unit) : View.OnClickListener {

    override fun onClick(v: View) {
        if (enabled) {
            enabled = false
            v.postDelayed(enable_again, DELAY_MILLIS)
            clickAction.invoke(v)
        }
    }

    companion object {
        const val DELAY_MILLIS = 500L
        private var enabled = true
        private val enable_again = Runnable { enabled = true }
    }
}
