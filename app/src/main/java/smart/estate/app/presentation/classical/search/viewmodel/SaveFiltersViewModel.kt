package smart.estate.app.presentation.classical.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smart.estate.app.data.model.estate.EstateFilters

class SaveFiltersViewModel: ViewModel() {

    private var _estateFilters = MutableLiveData<EstateFilters?>()
    val estateFilters = _estateFilters

    fun saveEstateFilters(estateFilters: EstateFilters) {
        _estateFilters.value = estateFilters
    }
}