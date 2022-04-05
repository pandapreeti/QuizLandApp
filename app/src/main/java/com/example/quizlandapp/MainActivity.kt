package com.example.quizlandapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private lateinit var mSharedPreferences: SharedPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    mSharedPreferences = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    btn_Start.setOnClickListener {
      if(edt_name.text.toString().isEmpty())
      {
        Toast.makeText(this,"Please enter your name", Toast.LENGTH_LONG).show()
      }
      else
      {
        val intent = Intent(this,QuizQuestionActivity::class.java)
        val editor = mSharedPreferences.edit()
        editor.putString(Constants.USER_NAME, edt_name.text.toString())
        editor.apply()
        intent.putExtra(Constants.USER_NAME,edt_name.text.toString())
        startActivity(intent)
        finish()
      }
    }
  }
}