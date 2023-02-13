package smart.estate.app.presentation.smart.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText
import smart.estate.app.R
import smart.estate.app.data.model.SmartEstateParameters
import smart.estate.app.presentation.smart.search.viewmodel.SmartSearchViewModel
import smart.estate.app.utils.Validator


class SmartSearchFragment : Fragment() {

    private val smartSearchViewModel: SmartSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_smart_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estateToggle = view.findViewById<MaterialButtonToggleGroup>(R.id.toggleButton)

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
        val houseTypeAdapter = context?.let { ArrayAdapter(it, R.layout.dropdown_item, houseTypes) }
        val houseTypeInput = view.findViewById<AutoCompleteTextView>(R.id.filled_exposed_dropdown)
        houseTypeInput.setAdapter(houseTypeAdapter)

        val predictButton = view.findViewById<MaterialButton>(R.id.predict_cost_button)

        predictButton.setOnClickListener {
            val cityInput =
                view.findViewById<TextInputEditText>(R.id.city_input_edit_text).text.toString()

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

            val levelFrom =
                view.findViewById<TextInputEditText>(R.id.level_from_input_edit_text).text.toString()
            val levelTo =
                view.findViewById<TextInputEditText>(R.id.level_to_input_edit_text).text.toString()

            val levelsFrom =
                view.findViewById<TextInputEditText>(R.id.levels_from_input_edit_text).text.toString()
            val levelsTo =
                view.findViewById<TextInputEditText>(R.id.levels_to_input_edit_text).text.toString()

            val numberOfRoomsFrom =
                view.findViewById<TextInputEditText>(R.id.numbers_of_rooms_from_input_edit_text).text.toString()
            val numberOfRoomsTo =
                view.findViewById<TextInputEditText>(R.id.numbers_of_rooms_to_input_edit_text).text.toString()

            val totalAreaFrom =
                view.findViewById<TextInputEditText>(R.id.total_area_from_input_edit_text).text.toString()
            val totalAreaTo =
                view.findViewById<TextInputEditText>(R.id.total_area_to_input_edit_text).text.toString()

            val kitchenAreaFrom =
                view.findViewById<TextInputEditText>(R.id.kitchen_area_from_input_edit_text).text.toString()
            val kitchenAreaTo =
                view.findViewById<TextInputEditText>(R.id.kitchen_area_to_input_edit_text).text.toString()

            val smartEstate = SmartEstateParameters(
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
            if (Validator(smartEstate = smartEstate, view = view, context = context).isValidate()) {
                smartSearchViewModel.saveEstateParameters(smartEstate)
                findNavController().navigate(R.id.action_navigation_smart_search_home_to_predictFragment)
            }

        }
    }


    companion object {
        const val anyType = "Любое"
        val otherType = "Другие"
        val panelType = "Панельный"
        val monoType = "Монолитный"
        val brickType = "Кирпичный"
        val blockType = "Блочный"
        val woodenType = "Деревянный"
    }
}