package smart.estate.app.presentation.smart.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import smart.estate.app.R
import smart.estate.app.data.model.estate.EstateFilters
import smart.estate.app.data.model_processing.TextProcessor
import smart.estate.app.presentation.classical.search.viewmodel.SaveFiltersViewModel
import smart.estate.app.presentation.smart.search.viewmodel.SaveSmartSearchViewModel

class EstateCostPredictedFragment : Fragment() {

    private val saveFiltersViewModel: SaveFiltersViewModel by activityViewModels()

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

        val navigateToClassicalSearchButton = view.findViewById<MaterialButton>(R.id.show_estate_by_price_predicted)

        navigateToClassicalSearchButton.setOnClickListener{
            val estateFilters = EstateFilters(
                priceFrom = costPredictedPair.first.toString(),
                priceTo = costPredictedPair.second.toString(),
                city = "",
                objectType = -2,
                houseType = -2,
                levelFrom = "",
                levelTo = "",
                levelsFrom = "",
                levelsTo = "",
                numberOfRoomsFrom = "",
                numberOfRoomsTo = "",
                totalAreaFrom = "",
                totalAreaTo = "",
                kitchenAreaFrom = "",
                kitchenAreaTo = ""
            )
            saveFiltersViewModel.saveEstateFilters(estateFilters)
            findNavController().navigate(R.id.action_estateCostPredictedFragment_to_ClassicalSearch)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = EstateCostPredictedFragment()
    }
}