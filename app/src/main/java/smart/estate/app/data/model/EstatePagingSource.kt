package smart.estate.app.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import smart.estate.app.data.model.estate.DataClass
import smart.estate.app.data.repository.DataRepository
import java.lang.Exception


class EstatePagingSource(private val dataRepository: DataRepository) :
    PagingSource<Int, DataClass>() {
    override fun getRefreshKey(state: PagingState<Int, DataClass>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_PAGE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_PAGE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataClass> {
        return try{
            val position = params.key ?: ONE_PAGE
            return withContext(Dispatchers.IO) {
                val response = dataRepository.getEstates(position)
                LoadResult.Page(
                    data = response,
                    prevKey = if (position == ONE_PAGE) null else position - ONE_PAGE,
                    nextKey = position + ONE_PAGE
                )
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object {
        const val ONE_PAGE = 1
    }
}