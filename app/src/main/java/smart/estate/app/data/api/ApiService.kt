package smart.estate.app.data.api

import retrofit2.http.GET
import smart.estate.app.data.model.SmartEstateParameters

interface ApiService {
    @GET("users")
    suspend fun getPrediction(): Pair<Long, Long>
}