package smart.estate.app.presentation.smart.search.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.estate.SmartDataClassParameters
import smart.estate.app.data.repository.DataRepository
import smart.estate.app.utils.Resource
import javax.inject.Inject

@HiltViewModel
class PredictViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {

    suspend fun getPrediction(smartEstateParameters: SmartDataClassParameters): Pair<Long, Long>? {

        Resource.loading(null)

        try {
            val resourcePair = Resource.success(data = dataRepository.getPrediction(smartEstateParameters))
            return resourcePair.data
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Возникла ошибка", data = null)
        }
        return null
    }
}