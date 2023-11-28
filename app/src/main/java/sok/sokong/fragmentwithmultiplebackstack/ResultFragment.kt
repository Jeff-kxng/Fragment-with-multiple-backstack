package sok.sokong.fragmentwithmultiplebackstack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val resultTextView = view.findViewById<TextView>(R.id.result_text_view)
        val resultProgressBar = view.findViewById<ProgressBar>(R.id.result_progress_bar)
        val backButton = view.findViewById<Button>(R.id.back_button)

        val args = arguments
        if (args != null) {
            val score = args.getInt("SCORE", 0)
            val totalQuestions = args.getInt("TOTAL_QUESTIONS", 0)

            val resultPercentage = (score.toFloat() / totalQuestions.toFloat()) * 100
            resultTextView.text = "Your score: $score / $totalQuestions"
            resultProgressBar.progress = resultPercentage.toInt()
        }

        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack("QuestionFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        
        return view
    }
}
