package smart.estate.app.presentation.classical.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.EstatePagerConfig
import smart.estate.app.data.model.estate.DataClass
import javax.inject.Inject

@HiltViewModel
class ClassicalSearchViewModel @Inject constructor(private val estatePagerConfig: EstatePagerConfig) : ViewModel() {

    fun getEstates(): LiveData<PagingData<DataClass>> {
        return estatePagerConfig.getEstates().cachedIn(viewModelScope)
    }
}