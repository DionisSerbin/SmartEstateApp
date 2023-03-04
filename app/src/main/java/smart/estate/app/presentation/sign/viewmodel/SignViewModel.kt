package smart.estate.app.presentation.sign.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.request.User
import smart.estate.app.data.repository.ApiRepository
import smart.estate.app.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel(){

    suspend fun createUser(user: User): Int? {
        Resource.loading(null)

        try {
            val response = Resource.success(data = apiRepository.createUser(user).body())
            return response.data
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Возникла ошибка", data = null)
            return null
        }
    }
}