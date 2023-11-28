package sok.sokong.fragmentwithmultiplebackstack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var optionRadioGroup: RadioGroup
    private lateinit var optionOneRadioButton: RadioButton
    private lateinit var optionTwoRadioButton: RadioButton
    private lateinit var optionThreeRadioButton: RadioButton
    private lateinit var optionFourRadioButton: RadioButton
    private lateinit var nextButton: Button

    private val questions = listOf(
        "1. Để phát triển ứng dụng cho Android, bạn cần sử dụng công cụ nào sau đây?",
        "2. ដើម្បីអភិវឌ្ឍន៍កម្មវិធីបង្កើតសោរ Android, អ្នកត្រូវតែប្រើឧបករណ៍អ្នកប្រើប្រាស់ណា?",
        "3. To test the Android Studio, what should you do?",
        "4. Để tạo một giao diện người dùng cho ứng dụng Android, bạn cần sử dụng ngôn ngữ nào sau đây?",
        "5. Để quản lý các phiên bản và mã nguồn của ứng dụng Android, bạn có thể sử dụng dịch vụ nào sau đây?",
        "6. Để kiểm tra và gỡ lỗi ứng dụng Android, bạn có thể sử dụng chế độ nào sau đây?",
        "7. Để đăng ký và phát hành ứng dụng Android, bạn cần có một tài khoản nào sau đây?"
    )

    private val correctAnswers = listOf(
        "A. Android Studio",
        "C. XML",
        "D. XML",
        "A. Android Studio",
        "B. GitHub",
        "A. Debug mode",
        "D. Google Play Developer Account"
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        questionTextView = view.findViewById(R.id.question_text_view)
        optionRadioGroup = view.findViewById(R.id.option_radio_group)
        optionOneRadioButton = view.findViewById(R.id.option_one_radio_button)
        optionTwoRadioButton = view.findViewById(R.id.option_two_radio_button)
        optionThreeRadioButton = view.findViewById(R.id.option_three_radio_button)
        optionFourRadioButton = view.findViewById(R.id.option_four_radio_button)
        nextButton = view.findViewById(R.id.next_button)

        nextButton.setOnClickListener {
            checkAnswer()
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion()
            } else {
                showResult()
            }
        }

        loadQuestion()

        return view
    }

    private fun loadQuestion() {
        optionRadioGroup.clearCheck() // Clear the checked state
        val currentQuestion = questions[currentQuestionIndex]
        val currentCorrectAnswer = correctAnswers[currentQuestionIndex]

        questionTextView.text = currentQuestion

        when (currentCorrectAnswer) {
            "A. Android Studio" -> {
                optionOneRadioButton.text = "A. Android Studio"
                optionTwoRadioButton.text = "B. Visual Studio"
                optionThreeRadioButton.text = "C. Xcode"
                optionFourRadioButton.text = "D. Eclipse"
            }
            "A. Android Studio" -> {
                optionOneRadioButton.text = "A. Android Studio"
                optionTwoRadioButton.text = "B. Visual Studio"
                optionThreeRadioButton.text = "C. Xcode"
                optionFourRadioButton.text = "D. Eclipse"
            }
            "D. XML" -> {
                optionOneRadioButton.text = "A. Java"
                optionTwoRadioButton.text = "B. Kotlin"
                optionThreeRadioButton.text = "C. HXML"
                optionFourRadioButton.text = "D. XML"
            }
            "C. XML" -> {
                optionOneRadioButton.text = "A. Java"
                optionTwoRadioButton.text = "B. Kotlin"
                optionThreeRadioButton.text = "C. XML"
                optionFourRadioButton.text = "D. HTML"
            }
            "B. GitHub" -> {
                optionOneRadioButton.text = "A. Firebase"
                optionTwoRadioButton.text = "B. GitHub"
                optionThreeRadioButton.text = "C. Google Play"
                optionFourRadioButton.text = "D. Android Market"
            }
            "A. Debug mode" -> {
                optionOneRadioButton.text = "A. Debug mode"
                optionTwoRadioButton.text = "B. Release mode"
                optionThreeRadioButton.text = "C. Emulator mode"
                optionFourRadioButton.text = "D. Simulator mode"
            }
            "D. Google Play Developer Account" -> {
                optionOneRadioButton.text = "A. Google Account"
                optionTwoRadioButton.text = "B. Google Developer Account"
                optionThreeRadioButton.text = "C. Google Play Account"
                optionFourRadioButton.text = "D. Google Play Developer Account"
            }
            else -> {
                // Default options if needed
                optionOneRadioButton.text = "Option 1"
                optionTwoRadioButton.text = "Option 2"
                optionThreeRadioButton.text = "Option 3"
                optionFourRadioButton.text = "Option 4"
            }
        }
    }

    private fun checkAnswer() {
        val checkedRadioButtonId = optionRadioGroup.checkedRadioButtonId
        if (checkedRadioButtonId != -1) {
            val selectedOption = view?.findViewById<RadioButton>(checkedRadioButtonId)?.text.toString()
            if (selectedOption == correctAnswers[currentQuestionIndex]) {
                score++
            }
        }
    }

    private fun showResult() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        val resultFragment = ResultFragment()
        val bundle = Bundle()
        bundle.putInt("SCORE", score)
        bundle.putInt("TOTAL_QUESTIONS", questions.size)
        resultFragment.arguments = bundle
        fragmentTransaction.replace(R.id.fragment_container, resultFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

