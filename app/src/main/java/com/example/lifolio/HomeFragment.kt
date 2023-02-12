package com.example.lifolio

import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lifolio.CustomOfTheYear.CustomOfTheYearActivity
import com.example.lifolio.Home.ApiService
import com.example.lifolio.Home.ResponseHome
import com.example.lifolio.My.MyFragment
import com.example.lifolio.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var allChart: LineChart
    private lateinit var bnbActivity: BnbActivity
    private lateinit var categoryChart2: LineChart

    private var dataList: ArrayList<Entry> = arrayListOf()
    private var dummyList1: ArrayList<Entry> = arrayListOf<Entry>()
    private var dummyList2: ArrayList<Entry> = arrayListOf<Entry>()
    private var dummyList3: ArrayList<Entry> = arrayListOf<Entry>()

    // 프레그먼트를 안고 있는 액티비티에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        bnbActivity = context as BnbActivity // Context를 부모 액티비티로 형변환해서 할당, context 필요시 사용
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        edit1_btn.visibility = View.INVISIBLE

//        val retrofit = Retrofit.Builder()// Retrofit2 사용을 위한 선언
//            .baseUrl("https://www.lifolio.shop/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // Retrofit2 interface 연결
//        val apiService = retrofit.create(ApiService::class.java)

        //토큰 문제 해결 후 작성
//        apiService.homeView().enqueue(object : Callback<ResponseHome>{
//            override fun onResponse(call: Call<ResponseHome>, response: Response<ResponseHome>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ResponseHome>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })

        binding.editLifolioBtn.setOnClickListener{

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        //item 1 클릭 이벤트
        binding.open1Btn.setOnClickListener{
            if(binding.itemFirstExpand.visibility == View.VISIBLE) {
                binding.itemFirstExpand.visibility = View.GONE
                binding.edit1Btn.visibility = View.GONE
                binding.open1Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemFirstExpand.visibility = View.VISIBLE
                binding.edit1Btn.visibility = View.VISIBLE
                binding.open1Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        //item 2 클릭 이벤트
        binding.open2Btn.setOnClickListener{
            if(binding.itemSecondExpand.visibility == View.VISIBLE) {
                binding.itemSecondExpand.visibility = View.GONE
                binding.edit2Btn.visibility = View.GONE
                binding.open2Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemSecondExpand.visibility = View.VISIBLE
                binding.edit2Btn.visibility = View.VISIBLE
                binding.open2Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        //item 3 클릭 이벤트
        binding.open3Btn.setOnClickListener{
            if(binding.itemThirdExpand.visibility == View.VISIBLE) {
                binding.itemThirdExpand.visibility = View.GONE
                binding.edit3Btn.visibility = View.GONE
                binding.open3Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemThirdExpand.visibility = View.VISIBLE
                binding.edit3Btn.visibility = View.VISIBLE
                binding.open3Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        //item 4 클릭 이벤트
        binding.open4Btn.setOnClickListener{
            if(binding.itemFourthExpand.visibility == View.VISIBLE) {
                binding.itemFourthExpand.visibility = View.GONE
                binding.edit4Btn.visibility = View.GONE
                binding.open4Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemFourthExpand.visibility = View.VISIBLE
                binding.edit4Btn.visibility = View.VISIBLE
                binding.open4Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        //올해의 목표 텍스트 버튼 클릭 이벤트 - #1-1로 이동
        binding.gotoCustomOfTheYearBtn.setOnClickListener{
            val intent = Intent(getActivity(), CustomOfTheYearActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        allChart = binding.homeAllChart
        categoryChart2 = binding.homeCategoryChart2

        var dayList: Array<String> = arrayOf(
            "2022-09",
            "2022-10",
            "2022-11",
            "2022-12",
            "2023-01",
            "2023-02",
            "2023-03",
            "2023-04",
            "2023-05"
        )

        var categoryDayList1 : Array<String> = arrayOf(
            "1/1",
            "1/6",
            "1/14",
            "1/19",
            "1/24",
            "1/25",
            "1/28",
            "2/1",
        )
        var categoryDayList2 : Array<String> = arrayOf(
            "1/1",
            "1/6",
            "1/14",
            "1/19",
            "1/24",
            "1/25",
            "1/28",
            "2/1",
        )
        var categoryDayList3 : Array<String> = arrayOf(
            "1/1",
            "1/6",
            "1/14",
            "1/19",
            "1/24",
            "1/25",
            "1/28",
            "2/1",
        )

        dataList.add(Entry(1f, 50f))
        dataList.add(Entry(2f, 70f))
        dataList.add(Entry(3f, 40f))
        dataList.add(Entry(4f, 50f))
        dataList.add(Entry(5f, 30f))
        dataList.add(Entry(6f, 100f))
        dataList.add(Entry(7f, 20f))
        dataList.add(Entry(8f, 50f))
        dataList.add(Entry(9f, 30f))

        dummyList1.add(Entry(1f, 50f))
        dummyList1.add(Entry(2f, 70f))
        dummyList1.add(Entry(3f, 40f))
        dummyList1.add(Entry(4f, 50f))
        dummyList1.add(Entry(5f, 30f))
        dummyList1.add(Entry(6f, 100f))
        dummyList1.add(Entry(7f, 20f))
        dummyList1.add(Entry(8f, 50f))

        dummyList2.add(Entry(1f, 20f))
        dummyList2.add(Entry(2f, 30f))
        dummyList2.add(Entry(3f, 60f))
        dummyList2.add(Entry(4f, 30f))
        dummyList2.add(Entry(5f, 50f))
        dummyList2.add(Entry(6f, 20f))
        dummyList2.add(Entry(7f, 40f))
        dummyList2.add(Entry(8f, 50f))

        dummyList3.add(Entry(1f, 10f))
        dummyList3.add(Entry(2f, 20f))
        dummyList3.add(Entry(3f, 50f))
        dummyList3.add(Entry(4f, 20f))
        dummyList3.add(Entry(5f, 40f))
        dummyList3.add(Entry(6f, 70f))
        dummyList3.add(Entry(7f, 8f))
        dummyList3.add(Entry(8f, 60f))

        setGraph(allChart, dataList, dayList)
        setGraph(categoryChart2, dummyList1, categoryDayList2)
        return binding.root
    }

    private fun setGraph(lineChart: LineChart, dummy: ArrayList<Entry>, dayList: Array<String>) {
        val xAxis: XAxis = lineChart.xAxis
        // x축 설정
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM   //x축 데이터의 위치를 아래로
            textSize = 14f
            setDrawAxisLine(true)
            setDrawGridLines(false) // 배경 그리드 라인 세팅 X
            granularity = 1f    // x축 데이터 표시 간격
            axisMinimum = 1f    // x축 데이터 최소 표시 값
            isGranularityEnabled = true     // x축 간격을 제한하는 세분화 기능
//            spaceMin = 0.1f     // Chart 맨 왼쪽 간격 띄우기
//            spaceMin = 0.1f
        }

        xAxis.valueFormatter = object: ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val day = dayList.get(value.toInt() - 1)
                Log.d("TAG", "getFormattedValue: ${day.substring(day.length - 2)}")
                return day
            }
        }

//        val day = dayList.get(value.toInt() - 1)
//        return day.substring(day.length - 2)

        // 초기 라인차트 세팅
        lineChart.apply {
            description.isEnabled = false

            // axisRight (오른쪽) 설정
            axisRight.apply {
                isEnabled = false //y축의 오른쪽 데이터 표시 비홢성화
                setDrawLabels(false) // 라벨 삭제
                setDrawAxisLine(false)
                axisMinimum = 0f
                axisMaximum = 100f
            }
            // axisLeft (왼쪽) 설정
            axisLeft.apply {
                textSize = 14f
                gridLineWidth = 1f
                setDrawAxisLine(false)
                axisLineWidth = 2f
                axisMinimum = 0f
                axisMaximum = 100f
            }

            // 범례 설정
            legend.isEnabled = false
        }

//        var graphCustomMarker = GraphCustomMarker(this, R.layout.item_graph_marker)
//        lineChart.marker = graphCustomMarker
//
//        graphCustomMarker.chartView = lineChart

        // 라인 색 그라데이션 설정
        val gradient = LinearGradient(
            800f, 100f, 30f, 0f,
            ContextCompat.getColor(bnbActivity, R.color.graph_end_color),
            ContextCompat.getColor(bnbActivity, R.color.graph_start_color),
            Shader.TileMode.CLAMP)
        val paint = lineChart.renderer.paintRender
        paint.setShader(gradient)

        // Entry ArrayList 생성 - Dummy
        var dataList: ArrayList<Entry> = dummy

        xAxis.mAxisMaximum = dataList.size.toFloat()

        // LinearDataSet에 데이터 ArrayList 넣기
        var set: LineDataSet = LineDataSet(dataList, "dataset 1")
        set.apply {
            mode = LineDataSet.Mode.CUBIC_BEZIER    // cubic line 설정
            setDrawValues(false)
            setDrawCircleHole(true)
            setDrawCircles(true)
            cubicIntensity = 0.2f   // 휘는 정도
            valueTextSize = 10f
            lineWidth = 5f
            circleRadius = 10f      // 원 밖의 반지름
            circleHoleRadius = 5f   // 원 안의 반지름
//            circleHoleColor = R.color.graph_circle_color  // 절대 사용 x
            valueTextSize = 0f  // 값이 필요 없으므로
            fillAlpha = 0   // 투명도
            isHighlightEnabled =false
        }

        // ILineDataSet 데이터 리스트에 LinearDataSet 추가 - 멀티 그래프를 원하면 여기에 데이터셋 여러개 추가
        var dataSets: ArrayList<ILineDataSet> = arrayListOf()
        dataSets.add(set)

        // 라인데이터에 ILineDataSet 데이터 리스트 추가
        var data: LineData = LineData(dataSets)


        // 그래프 아래 배경 색 그라데이션으로 칠하기
//        set.fillDrawable = ContextCompat.getDrawable(this, R.drawable.item_graph_fill)
//        set.setDrawFilled(true)

        // 차트에 라인 데이터 추가
        lineChart.data = data


        // 데이터 추가 후 라인차트 세팅
        lineChart.apply {
            setVisibleXRangeMaximum(5f) // 반드시 data 추가 후 달아야 Scroll 가능
            moveViewToX(data.entryCount.toFloat())
            setPinchZoom(false)
            isDoubleTapToZoomEnabled = false

        }

        // 차트 초기화 - Data 세팅 후 필수
        lineChart.invalidate()
    }
}
