package com.example.footballpredictions

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.text.style.ForegroundColorSpan as ForegroundColorSpan1


class StartActivity : AppCompatActivity() {

    // TextView 변수
    lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        val btn = findViewById<Button>(R.id.click_bt) as Button

        btn.setOnClickListener {
            val intent = Intent(this@StartActivity, GameActivity::class.java)
            startActivity(intent)
        }


        // 1. TextView 참조
        textView = findViewById(R.id.start_text)
        val Qator_color = resources.getColor(R.color.Qatar)

        // 2. String 문자열 데이터 취득
        val textData: String = textView.text.toString()

        // 3. SpannableStringBuilder 타입으로 변환
        val builder = SpannableStringBuilder(textData)

        // index=4에 해당하는 문자열(4)에 빨간색 적용
        val colorChange = ForegroundColorSpan1(Qator_color)
        builder.setSpan(colorChange, 4, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 5. TextView에 적용
        textView.text = builder




    }
}