package smart.estate.app.data.api

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getPrediction(): Pair<Long, Long>
}