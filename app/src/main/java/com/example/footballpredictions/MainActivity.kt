package com.example.footballpredictions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupListener(this@MainActivity)
    }

    fun setupListener(activity: Activity) {

        login.setOnClickListener {
            val username = username_inputbox.text.toString()
            val password = password_inputbox.text.toString()
            (application as MasterApplication).service.login(
                username, password
            ).enqueue(object : Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        val token = user!!.token!!
                        saveUserToken(username, token, activity)
                        (application as MasterApplication).createRetrofit()

                        Toast.makeText(activity, "로그인 하셨습니다.", Toast.LENGTH_LONG).show()
                        startActivity(Intent(activity, StartActivity::class.java))
                    } else {
                        Toast.makeText(activity, "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(activity, "서버 오류", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    fun saveUserToken(username: String, token: String, activity: Activity) {
        val sp = activity.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("username", username)
        editor.putString("token", token)
        editor.commit()
    }
}