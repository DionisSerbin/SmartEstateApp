package smart.estate.app.data.local.test

import smart.estate.app.data.model.estate.DataClass
import smart.estate.app.data.model.estate.SmartDataClassParameters

interface TestInterface {

    suspend fun getPrediction(smartEstateParameters: SmartDataClassParameters): Pair<Long, Long>

    suspend fun getEstates(page: Int): List<DataClass>

}