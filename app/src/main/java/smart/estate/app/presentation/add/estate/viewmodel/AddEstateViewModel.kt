package smart.estate.app.presentation.add.estate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.estate.AddedDataClass
import smart.estate.app.data.repository.ApiRepository
import smart.estate.app.utils.Resource
import javax.inject.Inject

@HiltViewModel
class AddEstateViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    private var _addedEstate = MutableLiveData<AddedDataClass>()
    val addedEstate = _addedEstate

    fun saveAddedEstate(addedEstate: AddedDataClass) {
        _addedEstate.value = addedEstate
    }

    suspend fun createEstate(estate: AddedDataClass): Int? {
        Resource.loading(null)

        try {
            val response = Resource.success(data = apiRepository.createEstate(estate).body())
            return response.data
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Возникла ошибка", data = null)
            return null
        }
    }

}