package smart.estate.app.presentation.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model.EstatePagerConfig
import smart.estate.app.data.model.request.UpdatedUser
import smart.estate.app.data.model.request.User
import smart.estate.app.data.repository.ApiRepository
import smart.estate.app.utils.Resource
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val estatePagerConfig: EstatePagerConfig,
    private val apiRepository: ApiRepository
) : ViewModel() {
    fun getEstates(): LiveData<PagingData<Estate>> {
        return estatePagerConfig.getEstates().cachedIn(viewModelScope)
    }

    suspend fun updateUser(user: UpdatedUser): Int? {
        Resource.loading(null)

        try {
            val response = Resource.success(data = apiRepository.updateUser(user).body())
            return response.data
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Возникла ошибка", data = null)
            return null
        }
    }
}