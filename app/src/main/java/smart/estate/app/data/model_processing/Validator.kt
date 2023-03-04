package smart.estate.app.data.model_processing

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import smart.estate.app.R
import smart.estate.app.data.model.estate.AddedDataClass
import smart.estate.app.data.model.estate.DataValidatorInterface
import smart.estate.app.data.model.estate.SmartDataClassParameters
import smart.estate.app.data.model.response.UserSettings
import smart.estate.app.data.model.estate.EstateFilters


class Validator(
    private val dataValidatorInterface: DataValidatorInterface,
    private val view: View,
    private val context: Context?
) {

    fun isValidate(): Boolean {
        if (dataValidatorInterface is SmartDataClassParameters) {
            return isValidateSmartEstateParameters()
        } else if (dataValidatorInterface is AddedDataClass) {
            return isValidateAddedEstate()
        } else if (dataValidatorInterface is UserSettings) {
            return isValidateUserSettings()
        } else if (dataValidatorInterface is EstateFilters) {
            return isValidateEstateFilters()
        }
        return false
    }

    private fun isValidateSmartEstateParameters(): Boolean {

        val cityInput = view.findViewById<TextInputEditText>(R.id.city_input_edit_text)

        val levelFromInput = view.findViewById<TextInputEditText>(R.id.level_from_input_edit_text)
        val levelToInput = view.findViewById<TextInputEditText>(R.id.level_to_input_edit_text)

        val levelsFromInput = view.findViewById<TextInputEditText>(R.id.levels_from_input_edit_text)
        val levelsToInput = view.findViewById<TextInputEditText>(R.id.levels_to_input_edit_text)

        val numberOfRoomsFromInput =
            view.findViewById<TextInputEditText>(R.id.numbers_of_rooms_from_input_edit_text)
        val numberOfRoomsToInput =
            view.findViewById<TextInputEditText>(R.id.numbers_of_rooms_to_input_edit_text)

        val totalAreaFromInput =
            view.findViewById<TextInputEditText>(R.id.total_area_from_input_edit_text)
        val totalAreaToInput =
            view.findViewById<TextInputEditText>(R.id.total_area_to_input_edit_text)

        val kitchenAreaFromInput =
            view.findViewById<TextInputEditText>(R.id.kitchen_area_from_input_edit_text)
        val kitchenAreaToInput =
            view.findViewById<TextInputEditText>(R.id.kitchen_area_to_input_edit_text)

        return isValidateCity(
            (dataValidatorInterface as SmartDataClassParameters).city,
            cityInput = cityInput
        ) && isValidateObjectType(
            dataValidatorInterface.objectType
        ) && isValidateHouseType(
            dataValidatorInterface.houseType
        ) && isValidateDigitsFromTo(
            digitFrom = dataValidatorInterface.levelFrom,
            digitTo = dataValidatorInterface.levelTo,
            digitFromInput = levelFromInput,
            digitToInput = levelToInput,
            upLimit = LEVEL_UP_LIMIT,
            downLimit = LEVEL_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsFromTo(
            digitFrom = dataValidatorInterface.levelsFrom,
            digitTo = dataValidatorInterface.levelsTo,
            digitFromInput = levelsFromInput,
            digitToInput = levelsToInput,
            upLimit = LEVEL_UP_LIMIT,
            downLimit = LEVEL_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsFromTo(
            digitFrom = dataValidatorInterface.numberOfRoomsFrom,
            digitTo = dataValidatorInterface.numberOfRoomsTo,
            digitFromInput = numberOfRoomsFromInput,
            digitToInput = numberOfRoomsToInput,
            upLimit = NUMBER_OF_ROOMS_UP_LIMIT,
            downLimit = NUMBER_OF_ROOMS_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsFromToFloat(
            digitFrom = dataValidatorInterface.totalAreaFrom,
            digitTo = dataValidatorInterface.totalAreaTo,
            digitFromInput = totalAreaFromInput,
            digitToInput = totalAreaToInput,
            upLimit = TOTAL_AREA_UP_LIMIT,
            downLimit = TOTAL_AREA_DOWN_LIMIT,
            digitRegex = FLOAT_REGEX
        ) && isValidateDigitsFromToFloat(
            digitFrom = dataValidatorInterface.kitchenAreaFrom,
            digitTo = dataValidatorInterface.kitchenAreaTo,
            digitFromInput = kitchenAreaFromInput,
            digitToInput = kitchenAreaToInput,
            upLimit = KITCHEN_AREA_UP_LIMIT,
            downLimit = KITCHEN_AREA_DOWN_LIMIT,
            digitRegex = FLOAT_REGEX
        )
    }

    private fun isValidateAddedEstate(): Boolean {

        val cityInput = view.findViewById<TextInputEditText>(R.id.add_estate_city_input_edit_text)

        val costInput = view.findViewById<TextInputEditText>(R.id.add_estate_cost_input_edit_text)

        val levelInput =
            view.findViewById<TextInputEditText>(R.id.add_estate_level_from_input_edit_text)

        val levelsInput =
            view.findViewById<TextInputEditText>(R.id.add_estate_levels_from_input_edit_text)

        val numberOfRoomsInput =
            view.findViewById<TextInputEditText>(R.id.add_estate_number_of_rooms_from_input_edit_text)

        val totalAreaInput =
            view.findViewById<TextInputEditText>(R.id.add_estate_total_area_from_input_edit_text)

        val kitchenAreaInput =
            view.findViewById<TextInputEditText>(R.id.add_estate_kitchen_area_from_input_edit_text)

        return isValidateCity(
            (dataValidatorInterface as AddedDataClass).city,
            cityInput = cityInput
        ) && isValidateObjectType(
            dataValidatorInterface.objectType
        ) && isValidateHouseType(
            dataValidatorInterface.houseType
        ) && isValidateDigitLong(
            parameter = dataValidatorInterface.price,
            parameterView = costInput,
            upLimit = COST_UP_LIMIT,
            downLimit = COST_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigit(
            parameter = dataValidatorInterface.level,
            parameterView = levelInput,
            upLimit = LEVEL_UP_LIMIT,
            downLimit = LEVEL_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigit(
            parameter = dataValidatorInterface.levels,
            parameterView = levelsInput,
            upLimit = LEVEL_UP_LIMIT,
            downLimit = LEVEL_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsComparingFromTo(
            parameterFrom = dataValidatorInterface.level,
            parameterTo = dataValidatorInterface.levels,
            parameterToView = levelInput
        ) && isValidateDigit(
            parameter = dataValidatorInterface.numberOfRooms,
            parameterView = numberOfRoomsInput,
            upLimit = NUMBER_OF_ROOMS_UP_LIMIT,
            downLimit = NUMBER_OF_ROOMS_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitFloat(
            parameter = dataValidatorInterface.totalArea,
            parameterView = totalAreaInput,
            upLimit = TOTAL_AREA_UP_LIMIT,
            downLimit = TOTAL_AREA_DOWN_LIMIT,
            digitRegex = FLOAT_REGEX
        ) && isValidateDigitFloat(
            parameter = dataValidatorInterface.kitchenArea,
            parameterView = kitchenAreaInput,
            upLimit = KITCHEN_AREA_UP_LIMIT,
            downLimit = KITCHEN_AREA_DOWN_LIMIT,
            digitRegex = FLOAT_REGEX
        ) && isValidateDigitsComparingFromToFloat(
            parameterFrom = dataValidatorInterface.kitchenArea,
            parameterTo = dataValidatorInterface.totalArea,
            parameterToView = totalAreaInput
        )
    }

    private fun isValidateEstateFilters(): Boolean {

        val priceFrom =
            view.findViewById<TextInputEditText>(R.id.classical_cost_from_input_edit_text)
        val priceTo = view.findViewById<TextInputEditText>(R.id.classical_cost_to_input_edit_text)

        val cityInput = view.findViewById<TextInputEditText>(R.id.classical_city_input_edit_text)

        val levelFromInput =
            view.findViewById<TextInputEditText>(R.id.classical_level_from_input_edit_text)
        val levelToInput =
            view.findViewById<TextInputEditText>(R.id.classical_level_to_input_edit_text)

        val levelsFromInput =
            view.findViewById<TextInputEditText>(R.id.classical_levels_from_input_edit_text)
        val levelsToInput =
            view.findViewById<TextInputEditText>(R.id.classical_levels_to_input_edit_text)

        val numberOfRoomsFromInput =
            view.findViewById<TextInputEditText>(R.id.classical_number_of_rooms_from_input_edit_text)
        val numberOfRoomsToInput =
            view.findViewById<TextInputEditText>(R.id.classical_number_of_rooms_to_input_edit_text)

        val totalAreaFromInput =
            view.findViewById<TextInputEditText>(R.id.classical_total_area_from_input_edit_text)
        val totalAreaToInput =
            view.findViewById<TextInputEditText>(R.id.classical_total_area_to_input_edit_text)

        val kitchenAreaFromInput =
            view.findViewById<TextInputEditText>(R.id.classical_kitchen_area_from_input_edit_text)
        val kitchenAreaToInput =
            view.findViewById<TextInputEditText>(R.id.classical_kitchen_area_to_input_edit_text)

        return isValidateCity(
            (dataValidatorInterface as EstateFilters).city,
            cityInput = cityInput
        ) && isValidateObjectType(
            dataValidatorInterface.objectType
        ) && isValidateHouseType(
            dataValidatorInterface.houseType
        ) && isValidateDigitsFromToLong(
            digitFrom = dataValidatorInterface.priceFrom,
            digitTo = dataValidatorInterface.priceTo,
            digitFromInput = levelFromInput,
            digitToInput = levelToInput,
            upLimit = COST_UP_LIMIT,
            downLimit = COST_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsFromTo(
            digitFrom = dataValidatorInterface.levelFrom,
            digitTo = dataValidatorInterface.levelTo,
            digitFromInput = levelFromInput,
            digitToInput = levelToInput,
            upLimit = LEVEL_UP_LIMIT,
            downLimit = LEVEL_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsFromTo(
            digitFrom = dataValidatorInterface.levelsFrom,
            digitTo = dataValidatorInterface.levelsTo,
            digitFromInput = levelsFromInput,
            digitToInput = levelsToInput,
            upLimit = LEVEL_UP_LIMIT,
            downLimit = LEVEL_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsFromTo(
            digitFrom = dataValidatorInterface.numberOfRoomsFrom,
            digitTo = dataValidatorInterface.numberOfRoomsTo,
            digitFromInput = numberOfRoomsFromInput,
            digitToInput = numberOfRoomsToInput,
            upLimit = NUMBER_OF_ROOMS_UP_LIMIT,
            downLimit = NUMBER_OF_ROOMS_DOWN_LIMIT,
            digitRegex = INT_REGEX
        ) && isValidateDigitsFromToFloat(
            digitFrom = dataValidatorInterface.totalAreaFrom,
            digitTo = dataValidatorInterface.totalAreaTo,
            digitFromInput = totalAreaFromInput,
            digitToInput = totalAreaToInput,
            upLimit = TOTAL_AREA_UP_LIMIT,
            downLimit = TOTAL_AREA_DOWN_LIMIT,
            digitRegex = FLOAT_REGEX
        ) && isValidateDigitsFromToFloat(
            digitFrom = dataValidatorInterface.kitchenAreaFrom,
            digitTo = dataValidatorInterface.kitchenAreaTo,
            digitFromInput = kitchenAreaFromInput,
            digitToInput = kitchenAreaToInput,
            upLimit = KITCHEN_AREA_UP_LIMIT,
            downLimit = KITCHEN_AREA_DOWN_LIMIT,
            digitRegex = FLOAT_REGEX
        )
    }

    private fun isValidateUserSettings(): Boolean {
        val nameInput =
            view.findViewById<TextInputEditText>(R.id.name_input_edit_text)

        val loginInput =
            view.findViewById<TextInputEditText>(R.id.login_input_edit_text)

        return isValidateLatinic(
            name = (dataValidatorInterface as UserSettings).name,
            nameInput = nameInput
        ) && isValidateLatinic(
            name = dataValidatorInterface.login,
            nameInput = loginInput
        )
    }

    private fun isValidateLatinic(name: String, nameInput: TextInputEditText): Boolean {
        if (name.isEmpty()) {
            nameInput.error = "Обязательное поле"
            return false
        }
        if (name.length > 30) {
            nameInput.error = "Слишком большое навзание"
            return false
        }
        if (!checkByRegex(name, LATINIC_REGEX)) {
            nameInput.error = "Напишите на английском"
            return false
        }
        return true
    }

    private fun isValidateCity(city: String, cityInput: TextInputEditText): Boolean {

        if (city.isEmpty()) {
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

    private fun isValidateDigitsFromTo(
        digitFrom: String, digitTo: String, digitFromInput: TextInputEditText,
        digitToInput: TextInputEditText, upLimit: Int, downLimit: Int, digitRegex: String
    ): Boolean {

        return isValidateDigit(
            parameter = digitFrom,
            digitRegex = digitRegex,
            upLimit = upLimit,
            downLimit = downLimit,
            parameterView = digitFromInput
        ) && isValidateDigit(
            parameter = digitTo,
            digitRegex = digitRegex,
            upLimit = upLimit,
            downLimit = downLimit,
            parameterView = digitToInput
        ) && isValidateDigitsComparingFromTo(
            parameterFrom = digitFrom,
            parameterTo = digitTo,
            parameterToView = digitToInput
        )
    }

    private fun isValidateDigitsFromToFloat(
        digitFrom: String, digitTo: String, digitFromInput: TextInputEditText,
        digitToInput: TextInputEditText, upLimit: Float, downLimit: Float, digitRegex: String
    ): Boolean {

        return isValidateDigitFloat(
            parameter = digitFrom,
            digitRegex = digitRegex,
            upLimit = upLimit,
            downLimit = downLimit,
            parameterView = digitFromInput
        ) && isValidateDigitFloat(
            parameter = digitTo,
            digitRegex = digitRegex,
            upLimit = upLimit,
            downLimit = downLimit,
            parameterView = digitToInput
        ) && isValidateDigitsComparingFromToFloat(
            parameterFrom = digitFrom,
            parameterTo = digitTo,
            parameterToView = digitToInput
        )
    }

    private fun isValidateDigitsFromToLong(
        digitFrom: String, digitTo: String, digitFromInput: TextInputEditText,
        digitToInput: TextInputEditText, upLimit: Long, downLimit: Long, digitRegex: String
    ): Boolean {

        return isValidateDigitLong(
            parameter = digitFrom,
            digitRegex = digitRegex,
            upLimit = upLimit,
            downLimit = downLimit,
            parameterView = digitFromInput
        ) && isValidateDigitLong(
            parameter = digitTo,
            digitRegex = digitRegex,
            upLimit = upLimit,
            downLimit = downLimit,
            parameterView = digitToInput
        ) && isValidateDigitsComparingFromToFloat(
            parameterFrom = digitFrom,
            parameterTo = digitTo,
            parameterToView = digitToInput
        )
    }

    private fun isValidateDigit(
        parameter: String,
        digitRegex: String,
        upLimit: Int,
        downLimit: Int,
        parameterView: TextInputEditText
    ): Boolean {
        if (parameter != "") {
            if (!checkByRegex(parameter, digitRegex)) {
                parameterView.error = "Неправильные данные"
                return false
            }
            if (parameter.toInt() < downLimit) {
                parameterView.error = "Слишком мало"
            }
            if (parameter.toInt() > upLimit) {
                parameterView.error = "Слишком много"
                return false
            }
        } else {
            parameterView.error = "Обязательное поле"
            return false
        }
        return true
    }

    private fun isValidateDigitLong(
        parameter: String,
        digitRegex: String,
        upLimit: Long,
        downLimit: Long,
        parameterView: TextInputEditText
    ): Boolean {
        if (parameter != "") {
            if (!checkByRegex(parameter, digitRegex)) {
                parameterView.error = "Неправильные данные"
                return false
            }
            if (parameter.toLong() < downLimit) {
                parameterView.error = "Слишком мало"
            }
            if (parameter.toLong() > upLimit) {
                parameterView.error = "Слишком много"
                return false
            }
        } else {
            parameterView.error = "Обязательное поле"
            return false
        }
        return true
    }

    private fun isValidateDigitFloat(
        parameter: String,
        digitRegex: String,
        upLimit: Float,
        downLimit: Float,
        parameterView: TextInputEditText
    ): Boolean {
        if (parameter != "") {
            if (!checkByRegex(parameter, digitRegex)) {
                parameterView.error = "Неправильные данные"
                return false
            }
            if (parameter.toFloat() < downLimit) {
                parameterView.error = "Слишком мало"
            }
            if (parameter.toFloat() > upLimit) {
                parameterView.error = "Слишком много"
                return false
            }
        } else{
            parameterView.error = "Обязательное поле"
            return false
        }
        return true
    }

    private fun isValidateDigitsComparingFromTo(
        parameterFrom: String,
        parameterTo: String,
        parameterToView: TextInputEditText
    ): Boolean {
        if (parameterFrom != "" && parameterTo != "") {
            if (parameterFrom.toInt() >= parameterTo.toInt()) {
                parameterToView.error = "Неправильно указаны От и До"
                return false
            }
        } else{
            parameterToView.error = "Обязательное поле"
            return false
        }
        return true
    }

    private fun isValidateDigitsComparingFromToFloat(
        parameterFrom: String,
        parameterTo: String,
        parameterToView: TextInputEditText
    ): Boolean {
        if (parameterFrom != "" && parameterTo != "") {
            if (parameterFrom.toFloat() >= parameterTo.toFloat()) {
                parameterToView.error = "Неправильно указаны От и До"
                return false
            }
        } else{
            parameterToView.error = "Обязательное поле"
            return false
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
        const val LATINIC_REGEX = "[A-Za-z]+"
        const val COST_UP_LIMIT: Long = 100_000_000
        const val COST_DOWN_LIMIT: Long = 500_000
        const val LEVEL_UP_LIMIT = 100
        const val LEVEL_DOWN_LIMIT = 1
        const val NUMBER_OF_ROOMS_UP_LIMIT = 20
        const val NUMBER_OF_ROOMS_DOWN_LIMIT = 1
        const val TOTAL_AREA_UP_LIMIT: Float = 200F
        const val TOTAL_AREA_DOWN_LIMIT: Float = 5F
        const val KITCHEN_AREA_UP_LIMIT: Float = 100F
        const val KITCHEN_AREA_DOWN_LIMIT: Float = 2F

    }
}