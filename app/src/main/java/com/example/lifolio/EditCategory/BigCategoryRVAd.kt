package com.example.lifolio.EditCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.EditCategory.models.BigCategory
import com.example.lifolio.EditCategory.models.SmallCategory
import com.example.lifolio.databinding.ItemBigCategoryBinding

class BigCategoryRVAd(val context : Context, private var dataList : MutableList<BigCategory>) : RecyclerView.Adapter<BigCategoryRVAd.DataViewHolder>(),ItemTouchHelperListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemBigCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class DataViewHolder(private var binding : ItemBigCategoryBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(bigCategory: BigCategory){
            binding.itemBigcategoryTitle.text = bigCategory.bigCategory

            binding.itemBigcategoryRv.adapter = SmallCategoryRVAd(context, dataList[position].innerList)
            binding.itemBigcategoryRv.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val name = dataList[from_position]
        // 리스트 갱신
        dataList.removeAt(from_position)
        dataList.add(to_position, name)

        // fromPosition에서 toPosition으로 아이템 이동 공지
        notifyItemMoved(from_position, to_position)
        return true
    }

    override fun onItemSwipe(position: Int) {
    }
}