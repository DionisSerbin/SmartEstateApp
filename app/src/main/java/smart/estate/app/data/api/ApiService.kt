package smart.estate.app.data.api

import retrofit2.Response
import retrofit2.http.*
import smart.estate.app.data.model.estate.*
import smart.estate.app.data.model.request.Prediction
import smart.estate.app.data.model.request.UpdatedUser
import smart.estate.app.data.model.request.User
import smart.estate.app.data.model.response.FavEstates

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/api/prediction")
    suspend fun getPrediction(@Body smartEstateParameters: SmartDataClassParameters): Response<Prediction>

    @Headers("Content-Type: application/json")
    @POST("/api/users")
    suspend fun createUser(@Body user: User): Response<Int>

    @Headers("Content-Type: application/json")
    @PUT("/api/users")
    suspend fun updateUser(@Body user: UpdatedUser): Response<Int>

    @GET("/api/estate/all")
    suspend fun getEstates(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<EstateResponse>

    @GET("/api/estate/user")
    suspend fun getUserEstates(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("mail") mail: String
    ): Response<EstateResponse>

    @GET("/api/favourites")
    suspend fun getFavouriteEstates(
        @Query("mail") mail: String
    ): Response<List<Estate>>

    @POST("/api/estate/where")
    suspend fun getEstatesWhere(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Body smartEstateFilters: EstateFilters
    ): Response<EstateResponse>

    @POST("/api/estate/user")
    suspend fun createEstate(
        @Body estate: AddedDataClass
    ): Response<Int>

    @POST("/api/favourites")
    suspend fun addToFavourite(
        @Query("user_mail") mail: String,
        @Query("estate_id") estateId: Int
    ): Response<Int>
}