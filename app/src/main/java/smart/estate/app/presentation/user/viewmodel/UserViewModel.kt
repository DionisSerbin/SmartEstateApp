package smart.estate.app.presentation.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.data.model.Estate
import smart.estate.app.data.model.EstatePagerConfig
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val estatePagerConfig: EstatePagerConfig) : ViewModel() {
    fun getEstates(): LiveData<PagingData<Estate>> {
        return estatePagerConfig.getEstates().cachedIn(viewModelScope)
    }
}