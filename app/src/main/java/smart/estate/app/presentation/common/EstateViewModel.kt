package smart.estate.app.presentation.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.repository.ApiRepository
import smart.estate.app.utils.Resource
import javax.inject.Inject

@HiltViewModel
class EstateViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {
    private var _estate = MutableLiveData<Estate?>()
    val estate = _estate
    private var _idReturned = MutableLiveData<Int?>()
    val idReturned = _idReturned

    fun saveEstate(estate: Estate) {
        _estate.value = estate
    }

    fun saveIdReturned( idReturned: Int) {
        _idReturned.value = idReturned
    }

    suspend fun addToFavourite(mail: String, estateId: Int): Int? {
        Resource.loading(null)

        try {
            val response = Resource.success(data = apiRepository.addToFavourite(mail, estateId).body())
            return response.data
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Возникла ошибка", data = null)
            return null
        }
    }

}