package com.example.lifolio.CustomOfTheYear.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.databinding.ItemDataCustomoftheyearBinding

class DataRVAd (private val datalist : ArrayList<com.example.lifolio.CustomOfTheYear.models.Result>) : RecyclerView.Adapter<DataRVAd.DataViewHolder>() {
    inner class DataViewHolder(private var binding : ItemDataCustomoftheyearBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun bind(data : Result) {
                    binding.customoftheyear23yearHistoryGoalTv.text = data.goal
                    binding.customoftheyear23yearHistoryCreatedateTv.text = data.createDate
                }
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataCustomoftheyearBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    override fun getItemCount(): Int = datalist.size
}