package smart.estate.app.data.repository

import retrofit2.Response
import smart.estate.app.data.api.ApiService
import smart.estate.app.data.model.request.Prediction
import smart.estate.app.data.model.estate.SmartDataClassParameters
import smart.estate.app.data.model.request.UpdatedUser
import smart.estate.app.data.model.request.User
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPrediction(smartEstateParameters: SmartDataClassParameters): Response<Prediction> {
        return apiService.getPrediction(smartEstateParameters)
    }

    suspend fun createUser(user: User): Response<Int> {
        return apiService.createUser(user)
    }

    suspend fun updateUser(user: UpdatedUser): Response<Int> {
        return apiService.updateUser(user)
    }
}