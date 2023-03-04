package smart.estate.app.presentation.smart.search.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.estate.SmartDataClassParameters
import smart.estate.app.data.repository.ApiRepository
import smart.estate.app.data.repository.DataRepository
import smart.estate.app.utils.Resource
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class PredictViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    suspend fun getPrediction(smartEstateParameters: SmartDataClassParameters): Pair<Long, Long>? {

        Resource.loading(null)

        try {
            val resourcePair = Resource.success(data = apiRepository.getPrediction(smartEstateParameters).body())
            return Pair(resourcePair.data!!.cost1, resourcePair.data.cost2)
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Возникла ошибка", data = null)
            return null
        }
    }
}