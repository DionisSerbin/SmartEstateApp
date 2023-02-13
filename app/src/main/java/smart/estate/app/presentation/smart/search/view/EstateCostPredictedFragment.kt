package smart.estate.app.presentation.smart.search.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import smart.estate.app.R
import smart.estate.app.presentation.smart.search.viewmodel.SmartSearchViewModel

class EstateCostPredictedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estate_cost_predicted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val smartSearchViewModel =
            ViewModelProvider(requireActivity()).get(SmartSearchViewModel::class.java)

        val costPredictedPair = smartSearchViewModel.costPredictedPair.value!!


        val costFromTextView = view.findViewById<TextView>(R.id.cost_value_from_text_view)
        val costToTextView = view.findViewById<TextView>(R.id.cost_value_to_text_view)

        val costTextPair = getCostsPredicted(costPredictedPair)

        costFromTextView.text = costTextPair.first
        costToTextView.text = costTextPair.second


    }

    private fun getCostsPredicted(costPredictedPair: Pair<Long, Long>): Pair<String, String> {
        var firstPredictedCost = costPredictedPair.first
        var secondPredictedCost = costPredictedPair.second
        if (secondPredictedCost < firstPredictedCost) {
            secondPredictedCost =
                firstPredictedCost.apply { firstPredictedCost = secondPredictedCost }
        }
        return Pair(
            convertCostToNiceText(firstPredictedCost * MILLION_VALUE),
            convertCostToNiceText(secondPredictedCost * MILLION_VALUE)
        )
    }

    private fun convertCostToNiceText(cost: Long): String {
        var reversedString = cost.toString().reversed()
        var range = INIT_RANGE
        while (range < reversedString.length) {
            reversedString = addChar(reversedString, range)
            range += RANGE_STEP
        }
        return reversedString.reversed() + RUBLE_VALUE
    }

    private fun addChar(str: String, pos: Int): String {
        return str.substring(START_SUBSTRING, pos) + EMPTY_STRING + str.substring(pos)
    }

    companion object {
        const val START_SUBSTRING = 0
        const val EMPTY_STRING = ' '
        const val RUBLE_VALUE = " р"
        const val INIT_RANGE = 3
        const val RANGE_STEP = 4
        const val MILLION_VALUE = 1_000_000

        @JvmStatic
        fun newInstance() = EstateCostPredictedFragment()
    }
}