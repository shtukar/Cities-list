package com.gmail.cities.presentation.common

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(protected val context: Context) : RecyclerView.Adapter<BaseRecyclerHolder>() {

    protected val inflater: LayoutInflater = LayoutInflater.from(context)
    open var items: MutableList<T> = mutableListOf()
        set(value) {
            val calculator = diffCalculator
            if (calculator != null) {
                calculator.oldItems = field
                calculator.newItems = value
                val diffResult = DiffUtil.calculateDiff(calculator)
                diffResult.dispatchUpdatesTo(this)
                field = value
            } else {
                field.clear()
                field.addAll(value)
                notifyDataSetChanged()
            }
        }
    protected open val diffCalculator: BaseDiffCalculator<T>? = null
    private var itemClickedListener: ((T) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items: List<T>?) {
        val pos = itemCount
        if (items != null && items.isNotEmpty()) {
            this.items.addAll(items)
            notifyItemRangeInserted(pos, items.size)
        }
    }

    open fun replaceItem(item: T, pos: Int) {
        if (pos != -1) {
            items[pos] = item
            notifyItemChanged(pos)
        }
    }

    fun getList(): MutableList<T> {
        return items
    }

    open fun getItem(position: Int): T {
        return items[position]
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        holder.itemView.setOnClickListener { itemClickedListener?.invoke(getItem(holder.adapterPosition)) }
    }

    open fun itemClicked(block: (T) -> Unit) {
        itemClickedListener = block
    }
}