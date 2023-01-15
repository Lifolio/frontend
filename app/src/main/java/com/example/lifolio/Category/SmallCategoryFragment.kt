package com.example.lifolio.Category

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.lifolio.PortfolioFragment
import com.example.lifolio.R
import com.example.lifolio.databinding.FragmentSmallCategoryBinding

class SmallCategoryFragment : Fragment() {
    private lateinit var binding : FragmentSmallCategoryBinding
    private lateinit var addCategoryActivity: AddCategoryActivity

    companion object {
        const val TAG : String = "SMALL_CATEGORY_FRAGMENT"

        fun newInstance() : PortfolioFragment {
            return PortfolioFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        addCategoryActivity = context as AddCategoryActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmallCategoryBinding.inflate(layoutInflater)

        // spinner demo TODO: 추후 수정 필요
        val items = arrayOf("선택","아이템1","아이템2","아이템3","아이템4")

        val myAdapter = ArrayAdapter.createFromResource(addCategoryActivity, R.array.spinner_array, R.layout.item_category_spinner).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.smallCategorySpinnerSp.adapter = adapter
        }

//        binding.smallCategorySpinnerSp.adapter = myAdapter

        binding.smallCategorySpinnerSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작
                when (position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    //...
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        return binding.root
    }


}