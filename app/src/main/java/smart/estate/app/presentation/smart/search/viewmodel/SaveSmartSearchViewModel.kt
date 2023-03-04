package smart.estate.app.presentation.smart.search.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smart.estate.app.data.model.estate.SmartDataClassParameters

class SaveSmartSearchViewModel : ViewModel() {

    private var _estateParameters = MutableLiveData<SmartDataClassParameters?>()
    val estateParameters = _estateParameters
    private var _costPredictedPair = MutableLiveData<Pair<Long, Long>?>()
    val costPredictedPair = _costPredictedPair

    fun saveEstateParameters(estateParameters: SmartDataClassParameters) {
        _estateParameters.value = estateParameters
    }

    fun saveNullEstateParameters() {
        _estateParameters.value = null
    }

    suspend fun saveCostPredictedPair(costPredictedPair: Pair<Long, Long>) {
        _costPredictedPair.value = costPredictedPair
    }

}