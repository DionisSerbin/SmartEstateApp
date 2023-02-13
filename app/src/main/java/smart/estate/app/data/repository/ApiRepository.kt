package smart.estate.app.data.repository

import smart.estate.app.data.api.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPrediction(): Pair<Long, Long> {
        return apiService.getPrediction()
    }
}