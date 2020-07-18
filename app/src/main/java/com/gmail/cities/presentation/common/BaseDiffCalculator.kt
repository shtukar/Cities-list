package com.gmail.cities.presentation.common

import androidx.recyclerview.widget.DiffUtil


open class BaseDiffCalculator<T>(var oldItems: List<T>? = null, var newItems: List<T>? = null) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems?.size ?: 0

    override fun getNewListSize(): Int = newItems?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            checkEquality(oldItemPosition, newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            checkEquality(oldItemPosition, newItemPosition)

    private fun checkEquality(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = getOldItem(oldItemPosition)
        val newItem = getNewItem(newItemPosition)
        return oldItem === newItem || (oldItem != null && oldItem == newItem)
    }

    protected fun getOldItem(position: Int): T? = oldItems?.get(position)

    protected fun getNewItem(position: Int): T? = newItems?.get(position)
}