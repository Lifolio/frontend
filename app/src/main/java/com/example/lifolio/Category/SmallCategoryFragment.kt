package com.example.lifolio.Category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifolio.PortfolioFragment
import com.example.lifolio.R

class SmallCategoryFragment : Fragment() {

    companion object {
        const val TAG : String = "SMALL_CATEGORY_FRAGMENT"

        fun newInstance() : PortfolioFragment {
            return PortfolioFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_small_category, container, false)
    }
}