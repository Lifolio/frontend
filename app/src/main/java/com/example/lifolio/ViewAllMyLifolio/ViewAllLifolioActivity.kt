package com.example.lifolio.ViewAllMyLifolio

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.lifolio.EditCategory.EditCategoryActivity
import com.example.lifolio.R
import com.example.lifolio.ViewSmallCategory.ViewSmallCategoryActivity
import com.example.lifolio.databinding.ActivityViewAllLifolioBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_view_all_lifolio.view.*


class ViewAllLifolioActivity : AppCompatActivity(){
    private lateinit var binding: ActivityViewAllLifolioBinding
    private lateinit var lineChart: LineChart
    private lateinit var thread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllLifolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewAllLifolioToolbar.ibToolbar.setOnClickListener { // 툴바에 버튼을 눌렀을때
            toggleDrawerLayout(binding.root)
        }

        binding.viewAllLifolioNavi.naviViewAllLifolioHeaderEditBtn.setOnClickListener { // navi에서 톱니바퀴 수정버튼 눌렀을때
            startActivity(Intent(this,EditCategoryActivity::class.java))
        }

        binding.viewAllLifolioNavi.naviViewAllLifolioSmallcategory2.setOnClickListener{
            startActivity(Intent(this, ViewSmallCategoryActivity::class.java))
        }

        binding.viewAllLifolioViewWayGroup.view_all_lifolio_view_way1_btn.isChecked = true // 디폴트값으로 첫번째 보여주기 방식 체크해주기
        supportFragmentManager
            .beginTransaction()
            .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay1Fragment())
            .commitAllowingStateLoss()

        binding.viewAllLifolioViewWayGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.view_all_lifolio_view_way1_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay1Fragment())
                        .commitAllowingStateLoss()
                }
                R.id.view_all_lifolio_view_way2_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay2Fragment())
                        .commitAllowingStateLoss()
                }
                R.id.view_all_lifolio_view_way3_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay3Fragment())
                        .commitAllowingStateLoss()
                }
            }
        }

        // 그래프 처리
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

        lineChart = binding.viewAllLifolioStarChart

        lineChart.extraBottomOffset = 20F
        lineChart.extraTopOffset = 20F

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
//                Log.d("TAG", "getFormattedValue: ${day.substring(day.length - 2)}")
                return day.substring(day.length - 2)
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
        var dataList: ArrayList<Entry> = arrayListOf()
        dataList.add(Entry(1f, 50f))
        dataList.add(Entry(2f, 70f))
        dataList.add(Entry(3f, 40f))
        dataList.add(Entry(4f, 50f))
        dataList.add(Entry(5f, 30f))
        dataList.add(Entry(6f, 100f))
        dataList.add(Entry(7f, 20f))
        dataList.add(Entry(8f, 50f))
        dataList.add(Entry(9f, 30f))

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

    private fun toggleDrawerLayout(drawerLayout: DrawerLayout) { // 툴바에 버튼을 눌렀을때 실행할 함수

        if(!drawerLayout.isDrawerOpen(GravityCompat.START)) { // navigation drawer 띄우기
            drawerLayout.openDrawer(GravityCompat.START)
        }
        else {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() { //뒤로가기 했을 때
        if (binding.root.isDrawerOpen(GravityCompat.START)) { // navigation drawer가 띄워진 상태에서 뒤로가기를 눌렀을때
            binding.root.closeDrawer(GravityCompat.START)
        } else { // naigation drawer가 띄워지지 않은 상태에서 뒤로가기를 눌렀을때
            super.onBackPressed()
        }
    }
}