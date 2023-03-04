package smart.estate.app.presentation.add.estate.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.data.model.estate.AddedDataClass
import smart.estate.app.data.model_processing.Validator
import smart.estate.app.presentation.add.estate.viewmodel.AddEstateViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddEstateFragment : Fragment(R.layout.fragment_add_estate) {

    private val addedEstateViewModel: AddEstateViewModel by activityViewModels()

    lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_estate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = context?.let { Prefs(it) }!!

        val estateToggle =
            view.findViewById<MaterialButtonToggleGroup>(R.id.add_estate_toggle_button)

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
        val houseTypeInput =
            view.findViewById<AutoCompleteTextView>(R.id.add_estate_filled_exposed_dropdown)
        houseTypeInput.setAdapter(houseTypeAdapter)

        val addButton = view.findViewById<MaterialButton>(R.id.add_estate_button)

        addButton.setOnClickListener {

            val costInput =
                view.findViewById<TextInputEditText>(R.id.add_estate_cost_input_edit_text).text.toString()

            val cityInput =
                view.findViewById<TextInputEditText>(R.id.add_estate_city_input_edit_text).text.toString()

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

            val levelInput =
                view.findViewById<TextInputEditText>(R.id.add_estate_level_from_input_edit_text).text.toString()

            val levelsInput =
                view.findViewById<TextInputEditText>(R.id.add_estate_levels_from_input_edit_text).text.toString()

            val numberOfRoomsInput =
                view.findViewById<TextInputEditText>(R.id.add_estate_number_of_rooms_from_input_edit_text).text.toString()

            val totalAreaInput =
                view.findViewById<TextInputEditText>(R.id.add_estate_total_area_from_input_edit_text).text.toString()

            val kitchenAreaInput =
                view.findViewById<TextInputEditText>(R.id.add_estate_kitchen_area_from_input_edit_text).text.toString()

            val date = Date()
            val currentDay = SimpleDateFormat("dd").format(date)
            val currentMonth = SimpleDateFormat("M").format(date)
            val currentYear = SimpleDateFormat("yyyy").format(date)
            val currentTime = SimpleDateFormat("hh:mm:ss").format(date)

            val addedEstate = AddedDataClass(
                price = costInput,
                objectType = objectType,
                houseType = houseTypeInt,
                city = cityInput,
                level = levelInput,
                levels = levelsInput,
                numberOfRooms = numberOfRoomsInput,
                totalArea = totalAreaInput,
                kitchenArea = kitchenAreaInput,
                time = currentTime,
                mail = prefs.getMail()!!,
                day = currentDay.toInt(),
                month = currentMonth.toInt(),
                year = currentYear.toInt()

            )
            if (Validator(dataValidatorInterface = addedEstate, view = view, context = context).isValidate()) {
                addedEstateViewModel.saveAddedEstate(addedEstate)
                findNavController().navigate(R.id.action_navigation_add_estate_to_addingEstateFragment)
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