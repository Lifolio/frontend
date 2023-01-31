package com.example.lifolio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_month.view.*
import java.util.*

class AdapterMonth: RecyclerView.Adapter<AdapterMonth.MonthView>() {
    private val center = Int.MAX_VALUE / 2
    private var calendar = Calendar.getInstance()

    inner class MonthView(val layout: View): RecyclerView.ViewHolder(layout)

    //onCreateViewHolder = 화면 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_month, parent, false)
        return MonthView(view)
    }

    //onBindViewHolder = 데이터 설정
    override fun onBindViewHolder(holder: MonthView, position: Int) {
        //Calendar의 time을 현재 날짜로 초기화
        calendar.time = Date()
        //set을 사용하여 현재 월의 1일
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        //add를 사용하여 월 단위로 'position - center' 만큼 이동
        //center = Int.MAX_VALUE/2이므로 리스트를 좌로 스크롤 할 경우 'position - center'= -1, 우로 스크롤 할 경우 +1
        calendar.add(Calendar.MONTH, position - center)
        holder.layout.item_month_tx.text = "${calendar.get(Calendar.MONTH) + 1}월"
        //현재의 월을 저장
        val tempMonth = calendar.get(Calendar.MONTH)

        //위에서 구한 월에서 보여줄 일들을 구하기
        val dayList: MutableList<Date> = MutableList(6 * 7) { Date() }
        for(i in 0..5) {
            for(k in 0..6) {
                calendar.add(Calendar.DAY_OF_MONTH, (1-calendar.get(Calendar.DAY_OF_WEEK)) + k)
                dayList[i * 7 + k] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }

        //Grid 타입의 RecyclerView를 사용하여 각 날짜를 보여주기
        val dayListManager = GridLayoutManager(holder.layout.context, 7)
//        val dayListAdapter = AdapterDay(tempMonth, dayList)

        //각 정보는 dayList에 저장하여 AdapterDay의 파라미터로 주기
        holder.layout.item_month_day_list.apply {
            layoutManager = dayListManager
//            adapter = dayListAdapter
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}