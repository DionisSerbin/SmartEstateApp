package smart.estate.app.data.api

import smart.estate.app.data.model.estate.SmartDataClassParameters
import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getPrediction(smartEstateParameters: SmartDataClassParameters) = apiService.getPrediction(smartEstateParameters)
}