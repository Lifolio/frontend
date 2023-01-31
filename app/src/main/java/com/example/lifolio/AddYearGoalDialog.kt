package com.example.lifolio

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.EditText
import kotlinx.android.synthetic.main.dialog_add_todo.*

class AddYearGoalDialog(context: Context) {
    private val dlg = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun AddYearGoalDlg(){
        dlg.show()
        dlg.setContentView(R.layout.dialog_add_year_goal)
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setCanceledOnTouchOutside(false)
        dlg.setCancelable(false)

        val add_year_goal = dlg.findViewById<EditText>(R.id.this_year_goal_tx)

        //취소 버튼 클릭했을 때 다이얼로그 종료
        dlg.dialog_cancle_btn.setOnClickListener{
            dlg.dismiss()
        }

        //등록 버튼 클릭했을 때 텍스트 뷰에 값 보내기
        dlg.dialog_add_btn.setOnClickListener{
            onClickListener.onClicked(add_year_goal.text.toString())
            dlg.dismiss()
        }
    }

    interface OnDialogClickListener
    {
        fun onClicked(goal: String)
    }
}