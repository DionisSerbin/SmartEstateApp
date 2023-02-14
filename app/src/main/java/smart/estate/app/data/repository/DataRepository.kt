package smart.estate.app.data.repository

import smart.estate.app.data.local.test.TestService
import smart.estate.app.data.model.Estate
import smart.estate.app.data.model.SmartEstateParameters
import javax.inject.Inject

class DataRepository @Inject constructor(private val testService: TestService){
    suspend fun getPrediction(smartEstateParameters: SmartEstateParameters): Pair<Long, Long> {
        return testService.getPrediction(smartEstateParameters)
    }

    suspend fun getEstates(page: Int): List<Estate> {
        return testService.getEstates(page)
    }
}