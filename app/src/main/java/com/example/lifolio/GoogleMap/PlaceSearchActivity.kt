package com.example.lifolio.GoogleMap

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifolio.GoogleMap.MapActivity.Companion.SEARCH_RESULT_EXTRA_KEY
import com.example.lifolio.GoogleMap.model.SearchResult
import com.example.lifolio.GoogleMap.model.search.Poi
import com.example.lifolio.GoogleMap.model.search.Pois
import com.example.lifolio.GoogleMap.model.search.SearchPoiInfo
import com.example.lifolio.GoogleMap.model.util.LocationLatLng
import com.example.lifolio.GoogleMap.model.util.RetrofitMapUtil
import com.example.lifolio.databinding.ActivityPlaceSearchBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PlaceSearchActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job : Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var binding: ActivityPlaceSearchBinding
    lateinit var adapter: SearchRecyclerAdapter
    lateinit var inputMethodManager: InputMethodManager

    var latitude: Float = 0.0f
    var longitude: Float = 0.0F
    var mainAddress: String = ""
    lateinit var activityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        activityLauncher= openActivityResultLauncher()

        initAdapter()
        initViews()
        bindViews()
        initData()
    }

    private fun initAdapter() {
        adapter = SearchRecyclerAdapter()
    }

    private fun initViews() = with(binding) {
        emptyResultTextView.isVisible = false
        recyclerView.adapter = adapter

        // ?????? ????????? ??????
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.adapter?: return
                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val totalItemCount = recyclerView.adapter!!.itemCount - 1

                // ????????? ?????? ????????? ??????
                if(!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == totalItemCount) {
                    loadNext()
                }
            }
        })
    }

    // ?????? page??? ?????? ????????? ?????????
    private fun loadNext() {
        if(binding.recyclerView.adapter?.itemCount == 0)
            return

        searchWithPage(adapter.currentSearchString, adapter.currentPage + 1)
    }

    private fun bindViews() = with(binding) {
        searchButton.setOnClickListener {
            searchKeyword(searchBarInputView.text.toString())

            hideKeyboard()
        }

        searchBarInputView.setOnKeyListener { v, keyCode, event ->
            when(keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    searchKeyword(searchBarInputView.text.toString())

                    hideKeyboard()
                    return@setOnKeyListener true
                }
            }
            return@setOnKeyListener false

        }
    }

    private fun hideKeyboard() {
        if (::inputMethodManager.isInitialized.not()) {
            inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        }
        inputMethodManager.hideSoftInputFromWindow(binding.searchBarInputView.windowToken, 0)
    }


    private fun searchKeyword(keywordString: String) {
        searchWithPage(keywordString, 1)
    }

    private fun searchWithPage(keywordString: String, page: Int) {
        launch(coroutineContext) {      // ????????? ??????
            try {
                binding.progressCircular.isVisible = true   // ?????????
                if(page == 1) {
                    adapter.clearList()
                }
                withContext(Dispatchers.IO) {       // IO ????????? ??????
                    val response = RetrofitMapUtil.apiService.getSearchLocation(
                        keyword = keywordString,
                        page = page
                    )
                    if(response.isSuccessful) {     // ?????? ??????
                        val body = response.body()
                        withContext(Dispatchers.Main) {     // Main ????????? ??????
                            Log.d("response::", "searchWithPage -" + body.toString())
                            body?.let { searchRes ->
                                setData(searchRes.searchPoiInfo, keywordString)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                binding.progressCircular.isVisible = false // ?????? ?????? ??????
            }
        }
    }

    private fun setData(searchInfo: SearchPoiInfo, keywordString: String) {
        val pois: Pois = searchInfo.pois
        val dataList = pois.poi.map {
            SearchResult(
                name = it.name ?: "-",
                fullAddress = makeMainAddress(it),
                locationLatLng = LocationLatLng(
                    it.noorLat,
                    it.noorLon
                )
            )
        }

        adapter.setSearchResultList(dataList) {
//            Toast.makeText(
//                this,
//                "???????????? : ${it.name}, ?????? : ${it.fullAddress} ??????/?????? : ${it.locationLatLng}",
//                Toast.LENGTH_SHORT
//            )
//                .show()

            latitude = it.locationLatLng.latitude
            longitude = it.locationLatLng.longitude
            mainAddress = it.fullAddress

            // map ???????????? ??????
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra(SEARCH_RESULT_EXTRA_KEY, it)
            activityLauncher.launch(intent)
        }
        adapter.currentPage = searchInfo.page.toInt()
        adapter.currentSearchString = keywordString
    }

    private fun makeMainAddress(poi: Poi): String =
        if (poi.secondNo?.trim().isNullOrEmpty()) {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    poi.firstNo?.trim()
        } else {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    (poi.firstNo?.trim() ?: "") + " " +
                    poi.secondNo?.trim()
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData() {
        adapter.notifyDataSetChanged()
    }

    // registerForActivityResult() ??? ???????????? ActivityResultLauncher ????????? ???????????? ?????????
    private fun openActivityResultLauncher(): ActivityResultLauncher<Intent> {
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {  // MapActivity Finish ???
//                Toast.makeText(this, "?????? ??????", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.putExtra("latitude", latitude)
                intent.putExtra("longitude", longitude)
                intent.putExtra("mainAddress", mainAddress)
                setResult(RESULT_OK, intent)
                finish()
            }
            else {
//                Toast.makeText(this, "?????? ??????", Toast.LENGTH_SHORT).show()
            }
        }
        return resultLauncher
    }
}