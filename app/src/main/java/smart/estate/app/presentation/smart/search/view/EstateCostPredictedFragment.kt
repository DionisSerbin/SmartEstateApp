package smart.estate.app.presentation.smart.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import smart.estate.app.R
import smart.estate.app.data.model_processing.TextProcessor
import smart.estate.app.presentation.smart.search.viewmodel.SaveSmartSearchViewModel

class EstateCostPredictedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estate_cost_predicted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveSmartSearchViewModel =
            ViewModelProvider(requireActivity()).get(SaveSmartSearchViewModel::class.java)

        val costPredictedPair = saveSmartSearchViewModel.costPredictedPair.value!!


        val costFromTextView = view.findViewById<TextView>(R.id.cost_value_from_text_view)
        val costToTextView = view.findViewById<TextView>(R.id.cost_value_to_text_view)

        val costTextPair = TextProcessor().getCostsPredicted(costPredictedPair)

        costFromTextView.text = costTextPair.first
        costToTextView.text = costTextPair.second


    }


    companion object {

        @JvmStatic
        fun newInstance() = EstateCostPredictedFragment()
    }
}