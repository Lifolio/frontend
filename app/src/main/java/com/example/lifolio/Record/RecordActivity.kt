package com.example.lifolio.Record

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityRecordBinding
import java.util.*


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
                binding.recordStartDateBtn.text = "${year}/${month}/${day}"

            }
            DatePickerDialog(this, data, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()

        }

    }

    fun initSeekbarView() {
        thumbView = LayoutInflater.from(this@RecordActivity)
            .inflate(R.layout.item_seekbar_thumb, null, false)

        binding.recordSeekbarSb.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar?.thumb = getThumb(progress);
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