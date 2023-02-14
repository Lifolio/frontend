package com.example.lifolio.Record

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.lifolio.GoogleMap.PlaceSearchActivity
import com.example.lifolio.JWT.ApiClient
import com.example.lifolio.MainApplication
import com.example.lifolio.R
import com.example.lifolio.Record.model.BigCategory
import com.example.lifolio.Record.model.GetBigCategoryRes
import com.example.lifolio.Record.model.GetSmallCategoryRes
import com.example.lifolio.Record.model.SmallCategory
import com.example.lifolio.databinding.ActivityRecordBinding
import com.example.lifolio.util.model.MethodCallback
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_record.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext
import kotlin.math.roundToInt


class RecordActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityRecordBinding

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var thumbView: View
    private lateinit var withWhoLayout: ConstraintLayout
    private lateinit var locationLayout: ConstraintLayout
    private lateinit var goalOfYearLayout: ConstraintLayout

    private lateinit var bigCategorySpinner: Spinner
    private lateinit var smallCategorySpinner: Spinner
    private lateinit var goalOfYearSpinner: Spinner

    private lateinit var inputMethodManager: InputMethodManager
    lateinit var getResult: ActivityResultLauncher<Intent>

    private var importanceScore = ""
    private var startDate = ""
    private var endDate = ""
    private lateinit var filePath: String
    private var bigCategoryId: Int = 0
    private var smallCategoryName = ""

    private var bigCategoryList: ArrayList<BigCategory> = arrayListOf()
    private var bigCateogySpinnerItems: ArrayList<String> = arrayListOf()
    private lateinit var smallCategory: SmallCategory
    private var smallCategorySpinnerItems: ArrayList<String> = arrayListOf()

    private lateinit var bigSpinnerAdapter: ArrayAdapter<String>
    private lateinit var smallSpinnerAdapter: ArrayAdapter<String>

    companion object {
        val retrofit = ApiClient.retrofit()
        val service = retrofit.create(RecordService::class.java)
        val userId = MainApplication.prefs.getString("userId", "").toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSeekbarView()
        initCalendarDialog()
        setViewVisibility()

        bigCateogySpinnerItems.add("큰 카테고리 선택")
        smallCategorySpinnerItems.add("작은 카테고리 선택")

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                filePath = getRealPathFromURI(it.data?.data!!)

                val file = File(filePath)

                Log.d("TAG", "onCreate: 갤러리에서 돌아옴")
                Glide.with(applicationContext)
                    .load(filePath)
                    .into(binding.recordPhotoImgv)
                binding.recordAddPhotoBtn.visibility = View.GONE
            }
        }

        // 큰 카테고리 선택 스피너 아이템 GET
        launch(coroutineContext) {
            try {
                val response = service.getBigCategoryList(userId).enqueue(
                    MethodCallback.generalCallback<GetBigCategoryRes, GetBigCategoryRes, GetBigCategoryRes> {
                        response ->
                        if(response!!.bigCategoryList?.isNotEmpty() == true) {
                            bigCategoryList = response.bigCategoryList as ArrayList<BigCategory>
                            bigCategoryList.forEach {
                                bigCateogySpinnerItems.add(it.categoryName)
                            }
                            Log.d("TAG", "big " + bigCategoryList.toString())
                            Log.d("TAG", "big name" + bigCateogySpinnerItems.toString())
                            Log.d("TAG", "onCreate: " + bigCategoryList.get(1).categoryId)
                        }
                    }
                )
                Toast.makeText(this@RecordActivity, "retro" + response.toString(), Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        initSpinner()
        setListener()



    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var focusView: View? = currentFocus
        if(focusView != null) {
            val rect: Rect = Rect()
            val x: Int = (ev?.x?.toInt() ?: Int) as Int
            val y: Int = (ev?.x?.toInt() ?: Int) as Int
            if(!rect.contains(x, y)) {
                var manager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                if(manager != null) {
                    manager.hideSoftInputFromWindow(focusView.windowToken, 0)
                }
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
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

    fun initCalendarDialog() {
        binding.recordStartDateBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val data = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                startDate = "${year}-${month+1}-${day}"
                binding.recordStartDateBtn.text = startDate

            }
            DatePickerDialog(this, data, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.recordEndDateBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val data = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                endDate = "${year}-${month+1}-${day}"
                binding.recordEndDateBtn.text = endDate

            }
            DatePickerDialog(this, data, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    // spinner demo TODO: 추후 수정 필요
    fun initSpinner() {
        bigCategorySpinner = binding.recordBigCategorySp
        smallCategorySpinner = binding.recordSmallCategorySp
        goalOfYearSpinner = binding.recordGoalOfYearSp


//        bigCateogyDummyList.add("큰 카테고리 선택")
//        bigCateogyDummyList.add("인생")
//        bigCateogyDummyList.add("학교")
//        bigCateogyDummyList.add("업무")


        bigSpinnerAdapter = ArrayAdapter(this, R.layout.item_record_spinner, bigCateogySpinnerItems)
        bigSpinnerAdapter.setNotifyOnChange(true)
        bigCategorySpinner.adapter = bigSpinnerAdapter

//        var smallCateogyDummyList: ArrayList<String> = arrayListOf()
//        smallCateogyDummyList.add("작은 카테고리 선택")
//        smallCateogyDummyList.add("추억")
//        smallCateogyDummyList.add("여행")
//        smallCateogyDummyList.add("친구")

        smallCategorySpinner.adapter
        smallSpinnerAdapter = ArrayAdapter(this, R.layout.item_record_spinner, smallCategorySpinnerItems)
        smallSpinnerAdapter.setNotifyOnChange(true)
        smallCategorySpinner.adapter = smallSpinnerAdapter

        var goalOfYearDummyList: ArrayList<String> = arrayListOf()
        goalOfYearDummyList.add("올해의 목표 선택")
        goalOfYearDummyList.add("해외로 여행")
        goalOfYearDummyList.add("일주일에 2번 이상 운동하기")
        goalOfYearDummyList.add("매일 영어 단어 암기")

        goalOfYearSpinner.adapter = ArrayAdapter(this, R.layout.item_record_spinner, goalOfYearDummyList)
    }

    // registerForActivityResult() 를 사용하여 ActivityResultLauncher 타입을 반환하는 메소드
    private fun openActivityResultLauncher(): ActivityResultLauncher<Intent> {
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                var lng = result.data?.getFloatExtra("longitude", 0.0F)
                var lat = result.data?.getFloatExtra("latitude", 0.0F)
//                Toast.makeText(this, "수신 성공 $lng, $lat", Toast.LENGTH_SHORT).show()
                binding.recordLocationBtn.text = result.data?.getStringExtra("mainAddress")
            }
            else {
//                Toast.makeText(this, "수신 실패", Toast.LENGTH_SHORT).show()
            }
        }
        return resultLauncher
    }

    private fun hideOrDisplayOptionIconsButton(v: ConstraintLayout, chip: Chip) {
        chip.setOnClickListener {
            if(v.visibility == View.VISIBLE) {
                v.setVisibility(View.GONE)
            } else {
                v.setVisibility(View.VISIBLE);
            }
        }
    }

    fun setListener() {
        // 갤러리에서 이미지 가져오기
        binding.recordAddPhotoBtn.setOnClickListener {
            // 현재 기기에 설정된 쓰기 권한을 가져오기 위한 변수
            var writePermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            // 현재 기기에 설정된 읽기 권한을 가져오기 위한 변수
            var readPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            // 읽기 권한과 쓰기 권한에 대해서 설정이 되어있지 않다면
            if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
                // 읽기, 쓰기 권한을 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    1
                )
            }

            // 위 경우가 아니라면 권한에 대해서 설정이 되어 있으므로
            else {
                var state = Environment.getExternalStorageState()

                // 갤러리를 열어서 파일을 선택
                if (TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    getResult.launch(intent)
                }
            }
        }

        // 함께한 사람 EditText 입력 시 chip 동적 추가
        binding.recordWithWhoEt.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val et = v as EditText
                val name = et.text.toString()

                binding.recordFlexboxlt.addChip(name)

                et.text = null
            }
            return@setOnKeyListener false
        }

        val activityLauncher= openActivityResultLauncher()

        // 위치 activity 전환
        binding.recordLocationBtn.setOnClickListener {
            val intent = Intent(this, PlaceSearchActivity::class.java)
            activityLauncher.launch(intent)
        }

        // 스피너 선택 리스너
        bigCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("TAG", "onItemSelected: p2 is $p2")
                if(p2 == 0 || p3.toInt() == 0) {
                    Log.d("TAG", "onItemSelected: big category index 0")
                    bigCategoryId = 0
                    smallCategoryName = ""
                } else {
                    bigCategoryId = bigCategoryList.get(p2 - 1).categoryId
                    Log.d("TAG", "onItemSelected: " + " bigId is "+ bigCategoryId)

                    // GET 큰 카테고리를 부모로 가지는 작은 카테고리 값 가져오기
                    launch(coroutineContext) {
                        try {
                            val response = service.getSmallCategoryList(bigCategoryId).enqueue(
                                    MethodCallback.generalCallback<GetSmallCategoryRes, GetSmallCategoryRes, GetSmallCategoryRes> {
                                        response ->
                                        if(response != null) {
                                            smallCategory = response.smallCategory!!
                                            smallCategorySpinnerItems = smallCategory.subCategoryList as ArrayList<String>
                                            Log.d("TAG", "small " + smallCategorySpinnerItems.toString())

                                            smallSpinnerAdapter.clear()
                                            smallCategorySpinnerItems.add(0, "작은 카테고리 선택")
                                            smallSpinnerAdapter.addAll(smallCategorySpinnerItems)
                                            smallSpinnerAdapter.notifyDataSetChanged()
                                        }
                                    }
                            )

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
//                    smallCategorySpinner.adapter
//                    smallSpinnerAdapter = ArrayAdapter(this@RecordActivity, R.layout.item_record_spinner, smallCategorySpinnerItems)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("TAG", "onNothingSelected: ")
            }

        }

        smallCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0) {
                    smallCategoryName = ""
                } else {
                    smallCategoryName = smallCategorySpinnerItems.get(p2)
                    Log.d("TAG", "onItemSelected: " + p2 + " "+ smallCategoryName)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        // 저장 버튼 시 값 체크
        binding.recordSaveBtn.setOnClickListener {
            if(binding.recordTitleEt.text.isNullOrBlank()) {
                Log.d("TAG", "setListener: clickclikc 제목을")
                Toast.makeText(this@RecordActivity, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if(binding.recordStartDateBtn.text.isNullOrEmpty()) {
                Log.d("TAG", "setListener: clickclikc 시작")
                Toast.makeText(this@RecordActivity, "시작 날짜를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if(binding.recordEndDateBtn.text.isNullOrEmpty()) {
                Log.d("TAG", "setListener: clickclikc 종료")
                Toast.makeText(this@RecordActivity, "종료 날짜를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if(bigCategoryId == 0) {
                Toast.makeText(this@RecordActivity, "큰 카테고리를 선택해주세요", Toast.LENGTH_SHORT).show()
            } else if(importanceScore.isNullOrEmpty()) {
                Toast.makeText(this@RecordActivity, "중요도를 설정해주세요", Toast.LENGTH_SHORT).show()
            } else {        // POST api 연결 / 액티비티 피니시
                finish()
            }
        }

    }

    fun getRealPathFromURI(uri: Uri): String {
        val buildName = Build.MANUFACTURER
        if (buildName.equals("Xiaomi")) {
            return uri.path.toString()
        }

        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = contentResolver.query(uri, proj, null, null, null)

        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }

        return cursor.getString(columnIndex)
    }

    fun setViewVisibility() {
        withWhoLayout = binding.recordWithWhoConst
        locationLayout = binding.recordLocationConst
        goalOfYearLayout = binding.recordGoalOfYearConst


        withWhoLayout.setVisibility(View.GONE)
        locationLayout.setVisibility(View.GONE)
        goalOfYearLayout.setVisibility(View.GONE)

        hideOrDisplayOptionIconsButton(withWhoLayout, binding.recordWithWhoChip)
        hideOrDisplayOptionIconsButton(locationLayout, binding.recordLocationChip)
        hideOrDisplayOptionIconsButton(goalOfYearLayout, binding.recordGoalOfYearChip)
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
}

