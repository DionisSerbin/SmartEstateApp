package smart.estate.app.data.model.pagging

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model.estate.EstateFilters
import smart.estate.app.data.repository.ApiRepository
import javax.inject.Inject

class EstatePagerConfig @Inject constructor(private val apiRepository: ApiRepository){

    fun getEstates(): LiveData<PagingData<Estate>> {
        apiRepository
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                EstatePagingSource(apiRepository)
            }
            , initialKey = 1
        ).liveData
    }

    fun getEstatesWhere(estateFilters: EstateFilters): LiveData<PagingData<Estate>> {
        apiRepository
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                EstateWherePagingSource(apiRepository, estateFilters)
            }
            , initialKey = 1
        ).liveData
    }

    fun getUserEstates(mail: String): LiveData<PagingData<Estate>> {
        apiRepository
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                UserEstatePagingSource(apiRepository, mail)
            }
            , initialKey = 1
        ).liveData
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}