package com.example.lifolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifolio.databinding.FragmentPlanningBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_planning.*
import kotlinx.android.synthetic.main.fragment_today_todo.*

class PlanningFragment : Fragment() {

    private lateinit var binding: FragmentPlanningBinding
//    private var progr = 0

//    lateinit var total_count_tx : TextView
//    lateinit var success_count_tx : TextView

//    lateinit var num1: String
//    lateinit var num2: String
//    var result: Double? = null

//    lateinit var selectedDate: LocalDate

    lateinit var todayTodoFragment: TodayTodoFragment
    lateinit var weekTodoFragment: WeekTodoFragment
    lateinit var monthTodoFragment: MonthTodoFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planning, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentPlanningBinding.inflate(layoutInflater)

//        updateProgressBar()

        //TextView를 string으로 형변환
//        num1 = total_count_tx.text.toString()
//        num2 = success_count_tx.text.toString()

        //목표 달성율 계산 소수점 두번째자리 까지만 표시
//        result = num2.toDouble() / num1.toDouble() * 100.0


        //progress 퍼센트에 따라 코멘트 변경
//        if(result!! <= 20){
//            comment_tx.text = "조금 더 노력해볼까요?"
//        }
//        else if(result!! <= 49){
//            comment_tx.text = "노력의 결실이 보여요!"
//        }
//        else if(result!! <= 70){
//            comment_tx.text = "반 이상 해냈어요!"
//        }
//        else if(result!! <= 89){
//            comment_tx.text = "잘 하고 있어요!"
//        }
//        else if(result!! <= 99){
//            comment_tx.text = "거의 다 왔어요!"
//        }
//        else{
//            comment_tx.text = "달성 완료! 최고예요!"
//        }

        planning_scroll_view.run {
//            header = header_view
//            stickListener = { _ ->
//                Log.d("LOGGER_TAG", "stickListener")
//            }
//            freeListener = { _ ->
//                Log.d("LOGGER_TAG", "freeListener")
//            }
        }

        //To-Do 캘린터 리사이클러뷰

//        //현재 날짜
//        selectedDate = LocalDate.now()

//        //화면 설정
//        setMonthView()
//
//        //이전 달로 이동
//        binding.calendarPreBtn.setOnClickListener{
//            selectedDate = selectedDate.minusMonths(1)
//            setMonthView()
//        }
//
//        //다음 달로 이동
//        binding.calendarNextBtn.setOnClickListener{
//            selectedDate = selectedDate.plusMonths(1)
//            setMonthView()
//        }

//        val monthListManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val monthListAdapter = AdapterMonth()
//
//        todo_calendar_custom.apply {
//            layoutManager = monthListManager
//            adapter = monthListAdapter
//            scrollToPosition(Int.MAX_VALUE/2)
//        }
//        val snap = PagerSnapHelper()
//        snap.attachToRecyclerView(todo_calendar_custom)

        //연간계획 리사이클러뷰
//        val list = arrayListOf("2022", "2023", "2024")
//        val manager02 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val adapter02 = ListAdapterThisyeargoal(list)
//
//        this_year_goal_recycler.adapter = adapter02
//
//        this_year_goal_recycler.apply {
//            adapter = adapter02
//            layoutManager = manager02
//        }

        //목표 탭 레이아웃
        todayTodoFragment = TodayTodoFragment()
        weekTodoFragment = WeekTodoFragment()
        monthTodoFragment = MonthTodoFragment()
        //시작화면 지정
        childFragmentManager.beginTransaction().add(R.id.frame_todo_tab,todayTodoFragment).commit()

        binding.frameTabsGoal.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        //TodayTodoFragment
                        replaceView(todayTodoFragment)
                    }
                    1->{
                        //WeekTodoFragment
                        replaceView(weekTodoFragment)
                    }
                    2->{
                        //MonthTodoFragment
                        replaceView((monthTodoFragment))
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        //TodayTodoFragment
                        replaceView(todayTodoFragment)
                    }
                    1->{
                        //WeekTodoFragment
                        replaceView(weekTodoFragment)
                    }
                    2->{
                        //MonthTodoFragment
                        replaceView((monthTodoFragment))
                    }
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        //TodayTodoFragment
                        replaceView(todayTodoFragment)
                    }
                    1->{
                        //WeekTodoFragment
                        replaceView(weekTodoFragment)
                    }
                    2->{
                        //MonthTodoFragment
                        replaceView((monthTodoFragment))
                    }
                }
            }
        })

        binding.thisYearGoalAddBtn.setOnClickListener {
            val dialog = AddYearGoalDialog(requireContext())
            dialog.AddYearGoalDlg()
            dialog.setOnClickListener(object : AddYearGoalDialog.OnDialogClickListener {
                override fun onClicked(year_goal: String)
                {
                    this_year_goal_tx.text = year_goal
                }
            })
        }

        binding.todoAddBtn.setOnClickListener {
            val dialog = AddTodoDialog(requireContext())
            dialog.AddTodoDlg()
            dialog.setOnClickListener(object : AddTodoDialog.OnDialogClickListener {
                override fun onClicked(today_goal: String) {
                    todo_tx.text = today_goal
                }
            })
        }
    }


//    private fun updateProgressBar(){
//        circle_progressbar.progress = progr
//        progress_tx.text = "$progr%"
//    }

    private fun replaceView(tab:Fragment){
        //화면 변경
        val selectedFragment:Fragment?
        selectedFragment = tab
        selectedFragment.let{
            childFragmentManager.beginTransaction()
                .replace(R.id.frame_todo_tab, it).commit()
        }
    }

//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun setMonthView(){
//        //월 텍스트뷰 세팅
//        binding.itemMonthTx.text = monthFromDate(selectedDate)
//        //날짜 생성해서 리스트에 담기
//        val dayList = dayInMonthArray(selectedDate)
//        //어댑터 초기화
//        val adapter = AdapterDay(dayList)
//        //레이아웃 설정
//        val manager: RecyclerView.LayoutManager = GridLayoutManager(this.context, 7)
//
//        binding.todoCalendarCustom.layoutManager = manager
//        binding.todoCalendarCustom.adapter = adapter
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun monthFromDate(date: LocalDate): String{
//        val formatter = DateTimeFormatter.ofPattern("MM월")
//
//        return date.format(formatter)
//    }
//
//    //날짜 생성
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun dayInMonthArray(date: LocalDate): ArrayList<String>{
//        val dayList = ArrayList<String>()
//
//        val yearMonth = YearMonth.from(date)
//        //해당 월의 마지막 날짜 가져오기
//        val lastDay = yearMonth.lengthOfMonth()
//        //해당 월의 처음 날짜 가져오기
//        val firstDay = selectedDate.withDayOfMonth(1)
//        //첫 날 요일 가져오기(월=1,일=7)
//        val dayOfWeek = firstDay.dayOfWeek.value
//
//        for(i in 1..31){
//            if(i <= dayOfWeek || i > (lastDay + dayOfWeek)){
//                dayList.add("")
//            }
//            else{
//                dayList.add((i - dayOfWeek).toString())
//            }
//        }
//        return dayList
//    }
}
