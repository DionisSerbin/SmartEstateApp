package smart.estate.app.presentation.classical.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText
import smart.estate.app.R
import smart.estate.app.data.model.estate.EstateFilters
import smart.estate.app.data.model_processing.Validator
import smart.estate.app.presentation.classical.search.viewmodel.SaveFiltersViewModel
import smart.estate.app.presentation.smart.search.view.SmartSearchFragment
import smart.estate.app.presentation.smart.search.viewmodel.SaveSmartSearchViewModel


class FiltersClassicalSearchFragment : Fragment(R.layout.fragment_filters_classical_search) {

    private val saveFiltersViewModel: SaveFiltersViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filters_classical_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estateToggle = view.findViewById<MaterialButtonToggleGroup>(R.id.classical_toggleButton)

        var objectType = -1

        estateToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->

            if (isChecked) {
                objectType = when (checkedId) {
                    R.id.button_all -> -1
                    R.id.button_second_using -> 1
                    R.id.button_new_estate -> 0
                    else -> {
                        -2
                    }
                }
            }
        }

        val houseTypes = resources.getStringArray(R.array.house_types)
        val houseTypeAdapter =
            context?.let { ArrayAdapter(it, R.layout.dropdown_item, houseTypes) }
        val houseTypeInput =
            view.findViewById<AutoCompleteTextView>(R.id.classical_filled_exposed_dropdown)
        houseTypeInput.setAdapter(houseTypeAdapter)

        val filtersReadyButton =
            view.findViewById<MaterialButton>(R.id.classical_filters_ready_button)

        filtersReadyButton.setOnClickListener {
            val cityInput =
                view.findViewById<TextInputEditText>(R.id.classical_city_input_edit_text).text.toString()

            val houseTypeInt = when (houseTypeInput.text.toString()) {
                anyType -> -1
                otherType -> 0
                panelType -> 1
                monoType -> 2
                brickType -> 3
                blockType -> 4
                woodenType -> 5
                else -> {
                    -2
                }
            }

            val priceFrom =
                view.findViewById<TextInputEditText>(R.id.classical_cost_from_input_edit_text).text.toString()

            val priceTo =
                view.findViewById<TextInputEditText>(R.id.classical_cost_to_input_edit_text).text.toString()

            val levelFrom =
                view.findViewById<TextInputEditText>(R.id.classical_level_from_input_edit_text).text.toString()
            val levelTo =
                view.findViewById<TextInputEditText>(R.id.classical_level_to_input_edit_text).text.toString()

            val levelsFrom =
                view.findViewById<TextInputEditText>(R.id.classical_levels_from_input_edit_text).text.toString()
            val levelsTo =
                view.findViewById<TextInputEditText>(R.id.classical_levels_to_input_edit_text).text.toString()

            val numberOfRoomsFrom =
                view.findViewById<TextInputEditText>(R.id.classical_number_of_rooms_from_input_edit_text).text.toString()
            val numberOfRoomsTo =
                view.findViewById<TextInputEditText>(R.id.classical_number_of_rooms_to_input_edit_text).text.toString()

            val totalAreaFrom =
                view.findViewById<TextInputEditText>(R.id.classical_total_area_from_input_edit_text).text.toString()
            val totalAreaTo =
                view.findViewById<TextInputEditText>(R.id.classical_total_area_to_input_edit_text).text.toString()

            val kitchenAreaFrom =
                view.findViewById<TextInputEditText>(R.id.classical_kitchen_area_from_input_edit_text).text.toString()
            val kitchenAreaTo =
                view.findViewById<TextInputEditText>(R.id.classical_kitchen_area_to_input_edit_text).text.toString()

            val estateFilters = EstateFilters(
                priceFrom = priceFrom,
                priceTo = priceTo,
                city = cityInput,
                objectType = objectType,
                houseType = houseTypeInt,
                levelFrom = levelFrom,
                levelTo = levelTo,
                levelsFrom = levelsFrom,
                levelsTo = levelsTo,
                numberOfRoomsFrom = numberOfRoomsFrom,
                numberOfRoomsTo = numberOfRoomsTo,
                totalAreaFrom = totalAreaFrom,
                totalAreaTo = totalAreaTo,
                kitchenAreaFrom = kitchenAreaFrom,
                kitchenAreaTo = kitchenAreaTo
            )
            if (Validator(
                    dataValidatorInterface = estateFilters,
                    view = view,
                    context = context
                ).isValidate()
            ) {
                saveFiltersViewModel.saveEstateFilters(estateFilters)
                findNavController().navigate(R.id.action_filtersClassicalSearchFragment_to_navigation_classical_search)
            }

        }

    }


    companion object {
        const val anyType = "Любое"
        const val otherType = "Другие"
        const val panelType = "Панельный"
        const val monoType = "Монолитный"
        const val brickType = "Кирпичный"
        const val blockType = "Блочный"
        const val woodenType = "Деревянный"
    }

}