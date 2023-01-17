package com.example.lifolio.Category

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityAddCategoryBinding

class AddCategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddCategoryBinding

    private lateinit var bigCategoryFragment: BigCategoryFragment
    private lateinit var smallCategoryFragment: SmallCategoryFragment

    private lateinit var tvSwitchLeft: TextView
    private lateinit var tvSwitchRight: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 버튼 리스너
        binding.addCategoryBackBtn.setOnClickListener {
            onBackPressed()
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }

        // 카테고리 분류 Switch 버튼 change 이벤트 처리리
       tvSwitchLeft = findViewById<TextView>(R.id.add_category_left_tx)
        tvSwitchRight = findViewById<TextView>(R.id.add_category_right_tx)

        changeFragment(BigCategoryFragment())

        binding.addCategorySelectsizeSwch.setOnCheckedChangeListener { _, checked ->
            when {
                checked -> { // 버튼 오른쪽 활성화
                    tvSwitchLeft.setTextColor(ContextCompat.getColor(this, R.color.switch_text_unselected))
                    tvSwitchLeft.typeface = Typeface.create(tvSwitchLeft.typeface, Typeface.NORMAL)
                    tvSwitchRight.setTextColor(ContextCompat.getColor(this,R.color.switch_text_selected))
                    tvSwitchRight.setTypeface(tvSwitchRight.typeface, Typeface.BOLD)
//                    Toast.makeText(this@MainActivity, "checked, rightBold", Toast.LENGTH_SHORT).show()
                    changeFragment(SmallCategoryFragment())
                }
                else -> { // 버튼 왼쪽 활성화
                    tvSwitchLeft.setTextColor(ContextCompat.getColor(this,R.color.switch_text_selected))
                    tvSwitchLeft.setTypeface(tvSwitchRight.typeface, Typeface.BOLD)
                    tvSwitchRight.setTextColor(ContextCompat.getColor(this, R.color.switch_text_unselected))
                    tvSwitchRight.typeface = Typeface.create(tvSwitchLeft.typeface, Typeface.NORMAL)
//                    Toast.makeText(this@MainActivity, "unchecked, leftBold", Toast.LENGTH_SHORT).show()
                    changeFragment(BigCategoryFragment())
                }
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.add_categoty_frame_fl, fragment)
            .commit()
    }

}