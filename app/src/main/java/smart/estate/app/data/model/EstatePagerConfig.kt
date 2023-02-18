package smart.estate.app.data.model

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import smart.estate.app.data.model.estate.DataClass
import smart.estate.app.data.repository.DataRepository
import javax.inject.Inject

class EstatePagerConfig @Inject constructor(private val dataRepository: DataRepository){

    fun getEstates(): LiveData<PagingData<DataClass>> {
        dataRepository
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                EstatePagingSource(dataRepository)
            }
            , initialKey = 1
        ).liveData
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}