package com.ykyh.localweather.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T: RecyclerItem> : RecyclerView.Adapter<BaseViewHolder>() {
    private var mItems: ArrayList<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return onCreateNewHolder(parent, viewType)
    }

    abstract fun onCreateNewHolder(parent: ViewGroup, type: Int): BaseViewHolder

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBindView(getItem(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) != null) getItem(position)!!.viewType else 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return if (mItems != null) mItems!!.size else 0
    }

    fun getItem(position: Int): RecyclerItem? {
        return if (itemCount > position) mItems!![position] else null
    }

    open fun addItem(item: T) {
        if (mItems == null) mItems = ArrayList()
        val mergeItems = ArrayList(mItems!!)
        mergeItems.add(item)
        mItems = mergeItems
    }

    open fun addItem(position: Int, item: T) {
        if (mItems == null) mItems = ArrayList()
        val mergeItems = ArrayList(mItems!!)
        mergeItems.add(position, item)
        mItems = mergeItems
    }

    fun addItems(items: ArrayList<T>, notify: Boolean = true) {
        if (mItems == null) mItems = ArrayList()
        val mergeItems = ArrayList(mItems!!)
        mergeItems.addAll(items)
        mItems = mergeItems
        if (notify) {
            notifyItemRangeInserted(mItems!!.size - items.size, mItems!!.size)
        }
    }

    fun setItems(items: ArrayList<T>, notify: Boolean) {
        mItems = items
        if (notify) {
            notifyDataSetChanged()
        }
    }

    fun getItemList():ArrayList<T>? {
        return mItems
    }

    open fun clear() {
        if (mItems != null) mItems!!.clear()
    }
}