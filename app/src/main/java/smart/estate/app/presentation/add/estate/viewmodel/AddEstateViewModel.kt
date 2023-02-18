package smart.estate.app.presentation.add.estate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import smart.estate.app.data.model.estate.AddedDataClass

class AddEstateViewModel: ViewModel() {

    private var _addedEstate = MutableLiveData<AddedDataClass>()
    val addedEstate = _addedEstate

    fun saveAddedEstate(addedEstate: AddedDataClass) {
        _addedEstate.value = addedEstate
    }
}