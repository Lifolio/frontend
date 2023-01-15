package com.example.lifolio.CustomLifolio

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityCustomLifolioBinding

class CustomLifolioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCustomLifolioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomLifolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var category = arrayOf("카테고리1", "카테고리2", "카테고리3", "카테고리4") // 추후에 서버에서 받아올 카테고리 배열
        binding.customlifolioCategorySelectConst.setOnClickListener{ // 카테고리 선택을 눌렀을때 발생할 이벤트
            AlertDialog.Builder(this)
                .setTitle("카테고리 선택")
                .setItems(category, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        val currentItem = category[which]
                        binding.customlifolioCategoryTv.text = currentItem
                    }
                })
                .show()
        }

    }
}