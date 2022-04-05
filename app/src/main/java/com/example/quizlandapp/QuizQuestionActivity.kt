package com.example.quizlandapp

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

  private var mCurrentPosition:Int = 1
  private var mQuestionList:ArrayList<Question>? = null
  private var mSelectedOption:Int = 1
  private var mCorrectAnswer:Int = 0
  private var mUserName:String?= null
  private lateinit var sounds: SoundPool
  private var wrongSoundId= 0
  private var correctSoundId= 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_quiz_question)

    mUserName = intent.getStringExtra(Constants.USER_NAME)
    mQuestionList = Constants.getQuestions()
    createSoundPool()
    setQuestions()

    tv_option_one.setOnClickListener(this)
    tv_option_two.setOnClickListener(this)
    tv_option_three.setOnClickListener(this)
    tv_option_four.setOnClickListener(this)
    btn_Submit.setOnClickListener(this)
  }

  private fun createSoundPool() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      createNewSoundPool()
    } else {
      createOldSoundPool()
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private fun createNewSoundPool() {
    val attributes = AudioAttributes.Builder()
      .setUsage(AudioAttributes.USAGE_GAME)
      .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
      .build()
    sounds = SoundPool.Builder()
      .setMaxStreams(2)
      .setAudioAttributes(attributes)
      .build()

    wrongSoundId = sounds.load(this,R.raw.wrong_answer, 1)
    correctSoundId = sounds.load(this,R.raw.sound1, 1)
  }

  protected fun createOldSoundPool() {
    sounds = SoundPool(5, AudioManager.STREAM_MUSIC, 0)
  }

  private fun setQuestions()
  {

    val question= mQuestionList!!.get(mCurrentPosition-1)
    defaultOptionView()

    if(mCurrentPosition==mQuestionList!!.size)
      btn_Submit.text ="FINISH"
    else
      btn_Submit.text="SUBMIT"

    progressbar.progress=mCurrentPosition
    tv_progress.text = "$mCurrentPosition" + "/" +progressbar.max

    tv_question.text = question!!.question
    iv_image.setImageResource(question.image)
    tv_option_one.text =question.optionOne
    tv_option_two.text= question.optionTwo
    tv_option_three.text=question.optionThree
    tv_option_four.text=question.optionFour
  }

  private fun defaultOptionView()
  {
    val options = ArrayList<TextView>()
    options.add(0,tv_option_one)
    options.add(1,tv_option_two)
    options.add(2,tv_option_three)
    options.add(3,tv_option_four)

    for(option in options)
    {
      option.setTextColor(Color.parseColor("#7A8089"))
      option.typeface = Typeface.DEFAULT
      option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)

    }
  }

  override fun onClick(v: View?) {
    when(v?.id){
      R.id.tv_option_one -> {
        selectedOptionView(tv_option_one,1)
      }

      R.id.tv_option_two -> {
        selectedOptionView(tv_option_two,2)
      }

      R.id.tv_option_three -> {
        selectedOptionView(tv_option_three,3)
      }

      R.id.tv_option_four -> {
        selectedOptionView(tv_option_four,4)
      }

      R.id.btn_Submit -> {
        if(mSelectedOption ==0) {
          mCurrentPosition++

          when {
            mCurrentPosition <=mQuestionList!!.size->{
              setQuestions()
            } else->{
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra(Constants.USER_NAME,mUserName)
            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer)
            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
            startActivity(intent)
          }
          }
        }
        else
        {
          val question = mQuestionList?.get(mCurrentPosition-1)
          if(question!!.correctAnswer!= mSelectedOption)
          {
            answerView(mSelectedOption,R.drawable.wrong_option_border_bg)
            sounds.play(wrongSoundId, 1F, 1F, 1, 0, 1F)

          }
          else{
            mCorrectAnswer++
            sounds.play(correctSoundId, 1F, 1F, 1, 0, 1F)
          }

          answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

          if(mCurrentPosition==mQuestionList!!.size)
            btn_Submit.text="FINISH"
          else
            btn_Submit.text="GO TO NEXT QUESTION"

          mSelectedOption=0
        }

      }

    }
  }

  private fun answerView(answer:Int,drawableView: Int){
    when(answer){
      1 -> {
        tv_option_one.background = ContextCompat.getDrawable(this,drawableView)
      }

      2 -> {
        tv_option_two.background = ContextCompat.getDrawable(this,drawableView)
      }

      3 -> {
        tv_option_three.background = ContextCompat.getDrawable(this,drawableView)
      }

      4 -> {
        tv_option_four.background = ContextCompat.getDrawable(this,drawableView)
      }

    }
  }

  private fun selectedOptionView(tv:TextView,selectedOptionNum :Int){
    defaultOptionView()
    mSelectedOption = selectedOptionNum
    tv.setTextColor(Color.parseColor("#7A8089"))
    tv.setTypeface(tv.typeface,Typeface.BOLD)
    tv.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)

  }
}