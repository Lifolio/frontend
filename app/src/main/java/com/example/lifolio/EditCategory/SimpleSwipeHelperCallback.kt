package com.example.lifolio.EditCategory

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView

class SimpleSwipeHelperCallback(private val smallCategoryRVAd : SmallCategoryRVAd) :
    ItemTouchHelper.SimpleCallback(UP or DOWN,LEFT or RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        smallCategoryRVAd.swapData(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

}