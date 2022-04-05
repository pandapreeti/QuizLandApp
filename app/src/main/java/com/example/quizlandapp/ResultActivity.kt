package com.example.quizlandapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

  private lateinit var mSharedPreferences: SharedPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)
    window.setFlags(
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);


    mSharedPreferences = getSharedPreferences(Constants.RATING_FLAG, Context.MODE_PRIVATE)
    val flag = mSharedPreferences.getBoolean(Constants.RATING_FLAG, false)
    if(!flag)
    showDialog()

    val userName = intent.getStringExtra(Constants.USER_NAME)
    tv_name.text = userName
    val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
    val correctAnswer=intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

    tv_score.text = "Your score is $correctAnswer out of $totalQuestions"

    btn_finish.setOnClickListener{
      startActivity(Intent(this,MainActivity::class.java))
      finish()
    }

    btn_reset.setOnClickListener{
      mSharedPreferences = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
      val username = mSharedPreferences.getString(Constants.USER_NAME, "")
      val intent = Intent(this,QuizQuestionActivity::class.java)
      intent.putExtra(Constants.USER_NAME,username)
      startActivity(intent)
      finish()
    }


  }

  private fun showDialog(){
    val popDialog: AlertDialog.Builder = AlertDialog.Builder(this)

    val linearLayout = LinearLayout(this)
    val rating = RatingBar(this)

    val lp = LinearLayout.LayoutParams(
      LinearLayout.LayoutParams.WRAP_CONTENT,
      LinearLayout.LayoutParams.WRAP_CONTENT
    )
    rating.layoutParams = lp
    rating.numStars = 5
    rating.stepSize = 1f

    //add ratingBar to linearLayout
    linearLayout.addView(rating)

    popDialog.setTitle("Add Rating ")

    //add linearLayout to dailog
    popDialog.setView(linearLayout)

    rating.onRatingBarChangeListener =
      OnRatingBarChangeListener { ratingBar, v, b -> println("Rated val:$v") }

    // Button OK
    popDialog.setPositiveButton(android.R.string.ok)
       { _, _ ->
       // textView.setText(rating.progress.toString())
         val editor = mSharedPreferences.edit()
         editor.putBoolean(Constants.RATING_FLAG, true)
         editor.apply()
        showThankYouDialog()
      } // Button Cancel
      .setNegativeButton("Skip",
        DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

    popDialog.create()
    popDialog.show()
  }

  private fun showThankYouDialog(){
    val builder = AlertDialog.Builder(this)
    builder.setMessage("Thank you for your participation")
    builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
      dialog.dismiss()
    }
    builder.create()
    builder.show()
  }
}