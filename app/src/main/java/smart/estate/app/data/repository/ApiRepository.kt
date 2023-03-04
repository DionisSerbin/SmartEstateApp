package smart.estate.app.data.repository

import retrofit2.Response
import smart.estate.app.data.api.ApiService
import smart.estate.app.data.model.estate.*
import smart.estate.app.data.model.request.Prediction
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

    suspend fun getEstates(limit: Int, offset: Int): Response<EstateResponse> {
        return apiService.getEstates(limit, offset)
    }

    suspend fun getEstatesWhere(limit: Int, offset: Int, estateFilters: EstateFilters): Response<EstateResponse> {
        return apiService.getEstatesWhere(limit, offset, estateFilters)
    }

    suspend fun getFavouriteEstates(mail: String): Response<List<Estate>>{
        return apiService.getFavouriteEstates(mail)
    }

    suspend fun getUserEstates(limit: Int, offset: Int, mail: String): Response<EstateResponse> {
        return apiService.getUserEstates(limit, offset, mail)
    }

    suspend fun createEstate(estate: AddedDataClass): Response<Int> {
        return apiService.createEstate(estate)
    }

    suspend fun addToFavourite(mail: String, estateId: Int): Response<Int> {
        return apiService.addToFavourite(mail, estateId)
    }

}