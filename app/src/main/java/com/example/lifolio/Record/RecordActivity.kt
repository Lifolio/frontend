package com.example.lifolio.Record

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityRecordBinding
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.chip.Chip
import java.util.*
import kotlin.math.roundToInt


class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private lateinit var thumbView: View
    private var importanceScore = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSeekbarView()

        binding.recordStartDateBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val data = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                binding.recordStartDateBtn.text = "${year}-${month+1}-${day}"

            }
            DatePickerDialog(this, data, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.recordEndDateBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val data = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                binding.recordEndDateBtn.text = "${year}-${month+1}-${day}"

            }
            DatePickerDialog(this, data, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        // spinner demo TODO: 추후 수정 필요
        val myAdapter = ArrayAdapter.createFromResource(this@RecordActivity, R.array.spinner_array, R.layout.item_category_spinner).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.recordBigCategorySp.adapter = adapter
        }

        val myAdapter2 = ArrayAdapter.createFromResource(this@RecordActivity, R.array.spinner_array, R.layout.item_category_spinner).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.recordSmallCategorySp.adapter = adapter
        }

        val myAdapter3 = ArrayAdapter.createFromResource(this@RecordActivity, R.array.spinner_array, R.layout.item_category_spinner).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.recordGoalOfYearSp.adapter = adapter
        }

        binding.recordWithWhoEt.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val et = v as EditText
                val name = et.text.toString()

                binding.recordFlexboxlt.addChip(name)

                et.text = null
            }
            return@setOnKeyListener false
        }



    }

    fun initSeekbarView() {
        thumbView = LayoutInflater.from(this@RecordActivity)
            .inflate(R.layout.item_seekbar_thumb, null, false)

        binding.recordSeekbarSb.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar?.thumb = getThumb(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {    // seekbar에서 터치를 떼었을 때
                importanceScore = seekBar?.progress.toString()
                Log.d("record", "onStopTrackingTouch: " + importanceScore)
            }
        })
    }

    fun getThumb(progress: Int): Drawable? {
        (thumbView.findViewById(R.id.tvProgress) as TextView).text = progress.toString() + ""
        thumbView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(
            thumbView.getMeasuredWidth(),
            thumbView.getMeasuredHeight(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        thumbView.layout(0, 0, thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight())
        thumbView.draw(canvas)
        return BitmapDrawable(resources, bitmap)
    }
}

private fun FlexboxLayout.addChip(text: String) {
    val chip = LayoutInflater.from(context).inflate(R.layout.item_category_chip, null) as Chip
    chip.text = text

    val layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT)
    layoutParams.rightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4F, resources.displayMetrics).roundToInt()

    chip.setOnCloseIconClickListener {
        removeView(chip as View)
    }

    addView(chip, childCount - 1, layoutParams)
}

private fun FlexboxLayout.getAllChips(): List<Chip> {
    return (0 until childCount).mapNotNull { index ->
        getChildAt(index) as? Chip
    }
}

private fun FlexboxLayout.clearChips() {
    val chipViews = (0 until childCount).mapNotNull { index ->
        val view = getChildAt(index)
        if (view is Chip) view else null
    }
    chipViews.forEach { removeView(it) }
}