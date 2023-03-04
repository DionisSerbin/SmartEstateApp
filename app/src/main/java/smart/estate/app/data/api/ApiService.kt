package smart.estate.app.data.api

import retrofit2.Response
import retrofit2.http.*
import smart.estate.app.data.model.request.Prediction
import smart.estate.app.data.model.estate.SmartDataClassParameters
import smart.estate.app.data.model.request.UpdatedUser
import smart.estate.app.data.model.request.User

interface ApiService {
//    @HTTP(method = "POST", path = "prediction", hasBody = true)
//    @Body smartEstateParameters: SmartDataClassParameters
    @Headers("Content-Type: application/json")
    @POST("/api/prediction")
    suspend fun getPrediction(@Body smartEstateParameters: SmartDataClassParameters): Response<Prediction>

    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun createUser(@Body user: User): Response<Int>

    @Headers("Content-Type: application/json")
    @PUT("/api/users")
    suspend fun updateUser(@Body user: UpdatedUser): Response<Int>
}