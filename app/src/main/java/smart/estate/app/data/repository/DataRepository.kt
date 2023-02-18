package smart.estate.app.data.repository

import smart.estate.app.data.local.test.TestService
import smart.estate.app.data.model.estate.DataClass
import smart.estate.app.data.model.estate.SmartDataClassParameters
import javax.inject.Inject

class DataRepository @Inject constructor(private val testService: TestService){
    suspend fun getPrediction(smartEstateParameters: SmartDataClassParameters): Pair<Long, Long> {
        return testService.getPrediction(smartEstateParameters)
    }

    suspend fun getEstates(page: Int): List<DataClass> {
        return testService.getEstates(page)
    }
}