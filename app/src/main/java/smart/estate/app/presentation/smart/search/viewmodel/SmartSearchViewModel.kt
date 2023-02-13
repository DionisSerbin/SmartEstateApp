package smart.estate.app.presentation.smart.search.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smart.estate.app.data.model.SmartEstateParameters

class SmartSearchViewModel : ViewModel() {

    private var _estateParameters = MutableLiveData<SmartEstateParameters?>()
    val estateParameters = _estateParameters
    private var _costPredictedPair = MutableLiveData<Pair<Long, Long>?>()
    val costPredictedPair = _costPredictedPair

    fun saveEstateParameters(estateParameters: SmartEstateParameters) {
        _estateParameters.value = estateParameters
    }

    fun saveNullEstateParameters() {
        _estateParameters.value = null
    }

    fun saveCostPredictedPair(costPredictedPair: Pair<Long, Long>) {
        _costPredictedPair.value = costPredictedPair
    }

}