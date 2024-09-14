package com.example.gassapp.adapter

import androidx.recyclerview.widget.DiffUtil

class CityDiffCallback(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Since cities are identified by their names (strings), we can compare them directly
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // If the item is the same, the content is the same too (as they are strings)
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}