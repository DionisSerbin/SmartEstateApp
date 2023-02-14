package smart.estate.app.data.local.test

import smart.estate.app.data.model.Estate
import smart.estate.app.data.model.SmartEstateParameters

interface TestInterface {

    suspend fun getPrediction(smartEstateParameters: SmartEstateParameters): Pair<Long, Long>

    suspend fun getEstates(page: Int): List<Estate>

}