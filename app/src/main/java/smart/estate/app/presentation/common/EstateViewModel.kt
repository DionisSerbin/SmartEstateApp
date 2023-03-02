package smart.estate.app.presentation.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smart.estate.app.data.model.estate.Estate

class EstateViewModel: ViewModel() {
    private var _estate = MutableLiveData<Estate?>()
    val estate = _estate
    private var _idReturned = MutableLiveData<Int?>()
    val idReturned = _idReturned

    fun saveEstate(estate: Estate) {
        _estate.value = estate
    }

    fun saveIdReturned( idReturned: Int) {
        _idReturned.value = idReturned
    }
}