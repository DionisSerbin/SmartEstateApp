package smart.estate.app.data.repository

import smart.estate.app.data.local.test.PredictionTestService
import smart.estate.app.data.model.SmartEstateParameters
import javax.inject.Inject

class DataRepository @Inject constructor(private val predictionTestService: PredictionTestService){
    suspend fun getPrediction(smartEstateParameters: SmartEstateParameters): Pair<Long, Long> {
        return predictionTestService.getPrediction(smartEstateParameters)
    }
}