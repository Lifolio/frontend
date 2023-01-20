package com.example.lifolio.Home

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.lifolio.R
import kotlinx.android.synthetic.main.dialog_home.*

class HomeDialog(context : Context) {

    private val dlg = Dialog(context)
//    private lateinit var badgeFragment: BadgeFragment

    fun homeDlg(){
        dlg.show()
        dlg.setContentView(R.layout.dialog_home)
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setCanceledOnTouchOutside(false)
        dlg.setCancelable(false)

        //확인 버튼 클릭했을 때 다이얼로그 종료
        dlg.dialog_ok_btn.setOnClickListener{
            dlg.dismiss()
        }

        //배지 보기 버튼 클릭했을 때 나의 배지로 이동
        dlg.dialog_badge_btn.setOnClickListener{
            setFragment()
        }

    }

    private fun setFragment() {
//        val transaction = supportFragmentManager.beginTransaction()
//            .add(R.id.frameLayout, BadgeFragment())
//        transaction.commit()
    }

    private fun gotoBadge() {

    }
}







//    private fun changeFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragments_frame, fragment)
//            .commit()
//    }


//    fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.dialog_home)
//
//        // 배경 투명(배경을 투명하게 하지 않으면 corner radius 적용이 보이지 않음)
//        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        // 취소 불가능(다이얼로그 이외 부분을 클릭했을 때 창 닫히는 것 방지)
//        setCancelable(false)
//
//        // 다이얼로그 배경 제거
//        window!!.setDimAmount(0f)
//
//    }
//
//    override fun onClick(v: View?) {
//        TODO("Not yet implemented")
//    }
