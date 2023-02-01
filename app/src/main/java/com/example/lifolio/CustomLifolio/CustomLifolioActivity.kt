package com.example.lifolio.CustomLifolio

import android.content.DialogInterface
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityCustomLifolioBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class CustomLifolioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCustomLifolioBinding
    private lateinit var lineChart1: LineChart
    private lateinit var lineChart2: LineChart
    private lateinit var lineChart3: LineChart

    private var categoryDayList1 : Array<String> = arrayOf(
        "1/1",
        "1/6",
        "1/14",
        "1/19",
        "1/24",
        "1/25",
        "1/28",
        "2/1",
    )
    private var categoryDayList2 : Array<String> = arrayOf(
        "1/1",
        "1/6",
        "1/14",
        "1/19",
        "1/24",
        "1/25",
        "1/28",
        "2/1",
    )
    private var categoryDayList3 : Array<String> = arrayOf(
        "1/1",
        "1/6",
        "1/14",
        "1/19",
        "1/24",
        "1/25",
        "1/28",
        "2/1",
    )

    private var dummyList1: ArrayList<Entry> = arrayListOf<Entry>()
    private var dummyList2: ArrayList<Entry> = arrayListOf<Entry>()
    private var dummyList3: ArrayList<Entry> = arrayListOf<Entry>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomLifolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customlifolioChart1.visibility = View.GONE
        binding.customlifolioChart2.visibility = View.GONE
        binding.customlifolioChart3.visibility = View.GONE

        var category = arrayOf("추억", "여행", "친구") // 추후에 서버에서 받아올 카테고리 배열
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

        // 그래프 처리 위한 버튼 이벤트 리스너
        binding.customlifolioCategoryBtn1.setOnClickListener {
            Log.d("TAG", "onCreate: btn1")
            if(binding.customlifolioChart1.visibility == View.GONE) {
                binding.customlifolioChart1.visibility = View.VISIBLE
            }
            binding.customlifolioChart2.visibility = View.GONE
            binding.customlifolioChart3.visibility = View.GONE
        }

        binding.customlifolioCategoryBtn2.setOnClickListener {
            Log.d("TAG", "onCreate: btn2")
            if(binding.customlifolioChart2.visibility == View.GONE) {
                binding.customlifolioChart2.visibility = View.VISIBLE
            }
            binding.customlifolioChart1.visibility = View.GONE
            binding.customlifolioChart3.visibility = View.GONE
        }

        binding.customlifolioCategoryBtn3.setOnClickListener {
            Log.d("TAG", "onCreate: btn3")
            if(binding.customlifolioChart3.visibility == View.GONE) {
                binding.customlifolioChart3.visibility = View.VISIBLE
            }
            binding.customlifolioChart1.visibility = View.GONE
            binding.customlifolioChart2.visibility = View.GONE
        }

        lineChart1 = binding.customlifolioChart1
        lineChart2 = binding.customlifolioChart2
        lineChart3 = binding.customlifolioChart3

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

        setGraph(lineChart1, dummyList1, categoryDayList1)
        setGraph(lineChart2, dummyList2, categoryDayList2)
        setGraph(lineChart3, dummyList3, categoryDayList3)
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
            ContextCompat.getColor(this, R.color.graph_end_color),
            ContextCompat.getColor(this, R.color.graph_start_color),
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