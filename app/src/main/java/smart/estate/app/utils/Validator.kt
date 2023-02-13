package smart.estate.app.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import smart.estate.app.R
import smart.estate.app.data.model.SmartEstateParameters
import smart.estate.app.presentation.smart.search.view.SmartSearchFragment


class Validator(
    private val smartEstate: SmartEstateParameters,
    private val view: View,
    private val context: Context?
) {

    fun isValidate(): Boolean {
        return isValidateCity(smartEstate.city)
                && isValidateObjectType(smartEstate.objectType)
                && isValidateHouseType(smartEstate.houseType)
                && isValidateLevel(smartEstate.levelFrom, smartEstate.levelTo)
                && isValidateLevels(smartEstate.levelsFrom, smartEstate.levelsTo)
                && isValidateNumberOfRooms(
            smartEstate.numberOfRoomsFrom,
            smartEstate.numberOfRoomsTo,
            view
        )
                && isValidateTotalArea(smartEstate.totalAreaFrom, smartEstate.totalAreaTo)
                && isValidateKitchenArea(
            smartEstate.kitchenAreaFrom,
            smartEstate.kitchenAreaTo
        )
    }

    private fun isValidateCity(city: String): Boolean {
        val cityInput = view.findViewById<TextInputEditText>(R.id.city_input_edit_text)

        if (city.length == 0) {
            cityInput.error = "Обязательное поле"
            return false
        }
        if (city.length > 30) {
            cityInput.error = "Слишком большое навзание"
            return false
        }
        if (!checkByRegex(city, CYRILLIC_REGEX)) {
            cityInput.error = "Напишите на русском"
            return false
        }
        return true
    }

    private fun isValidateObjectType(objectType: Int): Boolean {
        if (objectType == -2) {
            Toast.makeText(context, "Неправильно выбран тип объекта", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun isValidateHouseType(houseType: Int): Boolean {

        if (houseType == -2) {
            Toast.makeText(context, "Неправильно выбран тип недвижимости", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun isValidateLevel(levelFrom: String, levelTo: String): Boolean {
        val levelFromInput = view.findViewById<TextInputEditText>(R.id.level_from_input_edit_text)
        val levelToInput = view.findViewById<TextInputEditText>(R.id.level_to_input_edit_text)
        return isValidateDigitFromTo(
            parameterFrom = levelFrom,
            parameterTo = levelTo,
            digitRegex = INT_REGEX,
            downLimit = 1,
            upLimit = 50,
            parameterFromView = levelFromInput,
            parameterToView = levelToInput
        )
    }

    private fun isValidateLevels(levelsFrom: String, levelsTo: String): Boolean {
        val levelsFromInput = view.findViewById<TextInputEditText>(R.id.levels_from_input_edit_text)
        val levelsToInput = view.findViewById<TextInputEditText>(R.id.levels_to_input_edit_text)
        return isValidateDigitFromTo(
            parameterFrom = levelsFrom,
            parameterTo = levelsTo,
            digitRegex = INT_REGEX,
            downLimit = 5,
            upLimit = 100,
            parameterFromView = levelsFromInput,
            parameterToView = levelsToInput
        )
    }

    private fun isValidateNumberOfRooms(
        numberOfRoomsFrom: String,
        numberOfRoomsTo: String,
        view: View
    ): Boolean {
        val numberOfRoomsFromInput =
            view.findViewById<TextInputEditText>(R.id.numbers_of_rooms_from_input_edit_text)
        val numberOfRoomsToInput =
            view.findViewById<TextInputEditText>(R.id.numbers_of_rooms_to_input_edit_text)
        return isValidateDigitFromTo(
            parameterFrom = numberOfRoomsFrom,
            parameterTo = numberOfRoomsTo,
            digitRegex = INT_REGEX,
            downLimit = 0,
            upLimit = 20,
            parameterFromView = numberOfRoomsFromInput,
            parameterToView = numberOfRoomsToInput
        )
    }

    private fun isValidateTotalArea(totalAreaFrom: String, totalAreaTo: String): Boolean {
        val totalAreaFromInput =
            view.findViewById<TextInputEditText>(R.id.total_area_from_input_edit_text)
        val totalAreaToInput =
            view.findViewById<TextInputEditText>(R.id.total_area_to_input_edit_text)
        return isValidateDigitFromTo(
            parameterFrom = totalAreaFrom,
            parameterTo = totalAreaTo,
            digitRegex = FLOAT_REGEX,
            downLimit = 5,
            upLimit = 200,
            parameterFromView = totalAreaFromInput,
            parameterToView = totalAreaToInput
        )
    }

    private fun isValidateKitchenArea(kitchenAreaFrom: String, kitchenAreaTo: String): Boolean {
        val kitchenAreaFromInput =
            view.findViewById<TextInputEditText>(R.id.kitchen_area_from_input_edit_text)
        val kitchenAreaToInput =
            view.findViewById<TextInputEditText>(R.id.kitchen_area_to_input_edit_text)
        return isValidateDigitFromTo(
            parameterFrom = kitchenAreaFrom,
            parameterTo = kitchenAreaTo,
            digitRegex = FLOAT_REGEX,
            downLimit = 2,
            upLimit = 100,
            parameterFromView = kitchenAreaFromInput,
            parameterToView = kitchenAreaToInput
        )
    }

    private fun isValidateDigitFromTo(
        parameterFrom: String,
        parameterTo: String,
        digitRegex: String,
        upLimit: Int,
        downLimit: Int,
        parameterFromView: TextInputEditText,
        parameterToView: TextInputEditText,
    ): Boolean {
        if (parameterTo != "") {
            if (!checkByRegex(parameterTo, INT_REGEX)) {
                parameterToView.error = "Неправильные данные"
                return false
            }
            if (parameterTo.toInt() < downLimit) {
                parameterToView.error = "Слишком мало"
            }
            if (parameterTo.toInt() > upLimit) {
                parameterToView.error = "Слишком много"
                return false
            }
        }
        if (parameterFrom != "") {
            if (!checkByRegex(parameterFrom, digitRegex)) {
                parameterFromView.error = "Неправильные данные"
                return false
            }
            if (parameterFrom.toInt() < downLimit) {
                parameterFromView.error = "Слишком мало"
            }
            if (parameterFrom.toInt() > upLimit) {
                parameterFromView.error = "Слишком много"
                return false
            }
        }
        if (parameterFrom != "" && parameterTo != "") {
            if (parameterFrom.toInt() >= parameterTo.toInt()) {
                parameterToView.error = "Неправильно указаны От и До"
                return false
            }
        }
        return true
    }

    private fun checkByRegex(str: String, regex: String): Boolean {
        return str.matches(Regex(regex))
    }

    companion object {
        const val INT_REGEX = "[0-9]+"
        const val FLOAT_REGEX = "([0-9]*[.])?[0-9]+"
        const val CYRILLIC_REGEX = "\\p{IsCyrillic}+"
    }
}