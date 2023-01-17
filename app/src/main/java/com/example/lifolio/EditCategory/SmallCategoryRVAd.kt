package com.example.lifolio.EditCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.EditCategory.models.SmallCategory
import com.example.lifolio.R
import com.example.lifolio.databinding.ItemSmallCategoryBinding
import okhttp3.Interceptor.Companion.invoke
import java.util.*

class SmallCategoryRVAd (context : Context, val smallCategoryList : MutableList<SmallCategory>) : RecyclerView.Adapter<SmallCategoryRVAd.DataViewHolder>(),ItemTouchHelperListener {
    inner class DataViewHolder(var binding : ItemSmallCategoryBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(smallCategory: SmallCategory){
            binding.itemSmallcategoryTitle.text = smallCategory.smallCategory

            binding.smallcategoryEditBtn.setOnClickListener {
                val popupMenu = PopupMenu(binding.root.context, it)
                popupMenu.menuInflater.inflate(R.menu.edit_category_context_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.editcategory_delete_context_menu -> {
                            smallCategoryList.removeAt(adapterPosition)
                            notifyDataSetChanged()
                        }
                        else -> Toast.makeText(binding.root.context, "수정",Toast.LENGTH_LONG).show()
                    }
                    true
                }
                popupMenu.show()
            }
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

    // 아이템을 드래그되면 호출되는 메소드
    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val name = smallCategoryList[from_position]
        // 리스트 갱신
        smallCategoryList.removeAt(from_position)
        smallCategoryList.add(to_position, name)

        // fromPosition에서 toPosition으로 아이템 이동 공지
        notifyItemMoved(from_position, to_position)
        return true
    }

    override fun onItemSwipe(position: Int) {
        smallCategoryList.removeAt(position)
        notifyItemRemoved(position)
    }

}