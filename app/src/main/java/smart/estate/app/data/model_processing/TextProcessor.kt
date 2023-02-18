package smart.estate.app.data.model_processing

import smart.estate.app.presentation.smart.search.view.EstateCostPredictedFragment

class TextProcessor {

    fun getCostsPredicted(costPredictedPair: Pair<Long, Long>): Pair<String, String> {
        var firstPredictedCost = costPredictedPair.first
        var secondPredictedCost = costPredictedPair.second
        if (secondPredictedCost < firstPredictedCost) {
            secondPredictedCost =
                firstPredictedCost.apply { firstPredictedCost = secondPredictedCost }
        }
        return Pair(
            convertCostToNiceText(firstPredictedCost),
            convertCostToNiceText(secondPredictedCost)
        )
    }

    fun convertCostToNiceText(cost: Long): String {
        val costMillion = cost * MILLION_VALUE
        var reversedString = costMillion.toString().reversed()
        var range = INIT_RANGE
        while (range < reversedString.length) {
            reversedString = addChar(reversedString, range)
            range += RANGE_STEP
        }
        return reversedString.reversed() + RUBLE_VALUE
    }

    fun convertCostToNiceText(cost: Float): String {
        val costMillion = (cost * MILLION_VALUE).toLong()
        var reversedString = costMillion.toString().reversed()
        var range = INIT_RANGE
        while (range < reversedString.length) {
            reversedString = addChar(reversedString, range)
            range += RANGE_STEP
        }
        return reversedString.reversed() + RUBLE_VALUE
    }

    fun convertAreaToNiceText(area: Float): String {
        return "$area м^2"
    }

    fun convertDateToNiceText(day: Int, month: Int, year: Int, time: String): String {
        return "$day ${monthMap[month - 1]} $year, $time"
    }

    private fun addChar(str: String, pos: Int): String {
        return str.substring(START_SUBSTRING, pos) + EMPTY_STRING + str.substring(pos)
    }

    companion object {
        val monthMap =
            listOf(
                "января",
                "февраля",
                "марта",
                "апреля",
                "мая",
                "июня",
                "июля",
                "августа",
                "сентября",
                "октября",
                "ноября",
                "декабря"
            )
        const val START_SUBSTRING = 0
        const val EMPTY_STRING = ' '
        const val RUBLE_VALUE = " р"
        const val INIT_RANGE = 3
        const val RANGE_STEP = 4
        const val MILLION_VALUE = 1_000_000
    }
}