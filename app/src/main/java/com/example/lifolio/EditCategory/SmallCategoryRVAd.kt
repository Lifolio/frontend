package com.example.lifolio.EditCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.EditCategory.models.SmallCategory
import com.example.lifolio.databinding.ItemSmallCategoryBinding
import java.util.*

class SmallCategoryRVAd (context : Context, val smallCategoryList : MutableList<SmallCategory>) : RecyclerView.Adapter<SmallCategoryRVAd.DataViewHolder>() {

    fun swapData(fromPos : Int, toPos : Int){
        Collections.swap(smallCategoryList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    inner class DataViewHolder(var binding : ItemSmallCategoryBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(smallCategory: SmallCategory){
            binding.itemSmallcategoryTitle.text = smallCategory.smallCategory
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemSmallCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(smallCategoryList[position])
    }

    override fun getItemCount(): Int = smallCategoryList.size

}