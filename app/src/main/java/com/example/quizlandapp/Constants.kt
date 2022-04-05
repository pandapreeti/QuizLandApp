package com.example.quizlandapp

object Constants {

  const val USER_NAME:String = "user_name"
  const val TOTAL_QUESTIONS:String = "total_question"
  const val CORRECT_ANSWERS:String = "correct_answers"
  const val PREFERENCE_NAME:String = "quiz_user_name"
  const val RATING_FLAG = "rating_flag"

  fun getQuestions(): ArrayList<Question> {

    val questionsList = ArrayList<Question>()

    // 1
    val que1 = Question(
      1, "What country does Taj Mahal belong to?",
      R.drawable.ic_taj_mahal,
      "Argentina", "India",
      "Armenia", "Austria", 2
    )

    questionsList.add(que1)

    // 2
    val que2 = Question(
      2, "What country does Bhurj Khalifa belong to?",
      R.drawable.ic_taj_mahal,
      "India", "Austria",
      "Australia", "UAE", 4
    )

    questionsList.add(que2)

    // 3
    val que3 = Question(
      3, "What country does Mount Rushmore National Memorial belong to?",
      R.drawable.ic_taj_mahal,
      "Belarus", "Belize",
      "United Status", "Australia", 3
    )

    questionsList.add(que3)

    // 4
    val que4 = Question(
      4, "What country does this flag belong to?",
      R.drawable.ic_taj_mahal,
      "Bahamas", "Belgium",
      "France", "Belize", 3
    )

    questionsList.add(que4)

    // 5
    val que5 = Question(
      5, "What country does this flag belong to?",
      R.drawable.ic_taj_mahal,
      "Italy", "France",
      "Fiji", "Finland", 1
    )

    questionsList.add(que5)

    // 6
    val que6 = Question(
      6, "What country does this flag belong to?",
      R.drawable.ic_taj_mahal,
      "Germany", "Georgia",
      "Greece", "none of these", 1
    )

    questionsList.add(que6)

    // 7
    val que7 = Question(
      7, "What country does this flag belong to?",
      R.drawable.ic_taj_mahal,
      "Dominica", "Egypt",
      "Denmark", "Ethiopia", 3
    )

    questionsList.add(que7)

    // 8
    val que8 = Question(
      8, "What country does this flag belong to?",
      R.drawable.ic_taj_mahal,
      "Ireland", "Iran",
      "Hungary", "India", 4
    )

    questionsList.add(que8)

    // 9
    val que9 = Question(
      9, "What country does this flag belong to?",
      R.drawable.ic_taj_mahal,
      "Australia", "New Zealand",
      "Tuvalu", "United States of America", 2
    )

    questionsList.add(que9)

    // 10
    val que10 = Question(
      10, "What country does this flag belong to?",
      R.drawable.ic_taj_mahal,
      "Kuwait", "Jordan",
      "Sudan", "Palestine", 1
    )

    questionsList.add(que10)

    return questionsList

  }
}