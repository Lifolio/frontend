package com.example.lifolio.My

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.databinding.ItemLifeDataBinding

class DataRVAdapter(
    private val dataList: ArrayList<LifeData>,
    val onClickDeleteBtn: (data: LifeData) -> Unit):
    RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {
    // ViewHolder 객체
    inner class DataViewHolder(val binding: ItemLifeDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LifeData) {
            binding.lifeTitle.text = data.life_title
            binding.lifeSubtitle.text = data.life_subtitle
        }
    }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemLifeDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    // ViewHolder 가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val listposition = dataList[position]
        holder.bind(listposition)
    }

    // 표현할 Item 의 총 개수
    override fun getItemCount(): Int = dataList.size
}