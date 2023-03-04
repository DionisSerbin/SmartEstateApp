package smart.estate.app.presentation.classical.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.pagging.EstatePagerConfig
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model.estate.EstateFilters
import javax.inject.Inject

@HiltViewModel
class ClassicalSearchViewModel @Inject constructor(private val estatePagerConfig: EstatePagerConfig) : ViewModel() {

    fun getEstates(): LiveData<PagingData<Estate>> {
        return estatePagerConfig.getEstates().cachedIn(viewModelScope)
    }

    fun getEstatesWhere(estateFilters: EstateFilters): LiveData<PagingData<Estate>> {
        return estatePagerConfig.getEstatesWhere(estateFilters).cachedIn(viewModelScope)
    }
}