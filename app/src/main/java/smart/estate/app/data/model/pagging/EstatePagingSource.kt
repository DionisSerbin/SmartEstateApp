package smart.estate.app.data.model.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import smart.estate.app.R
import smart.estate.app.data.model.Prefs
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model.estate.EstateResponse
import smart.estate.app.data.repository.ApiRepository
import java.lang.Exception


class EstatePagingSource(private val apiRepository: ApiRepository) :
    PagingSource<Int, Estate>() {

    override fun getRefreshKey(state: PagingState<Int, Estate>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_PAGE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_PAGE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Estate> {
        return try {
            val position = params.key ?: ONE_PAGE
            return withContext(Dispatchers.IO) {
                val response = apiRepository.getEstates(
                    limit = NETWORK_PAGE_SIZE,
                    offset = (position - 1) * NETWORK_PAGE_SIZE
                )

                var estates = response.body()!!.items
                estates = addPhoto(estates)
                LoadResult.Page(
                    data = estates,
                    prevKey = if (position == ONE_PAGE) null else position - ONE_PAGE,
                    nextKey = if (estates.isEmpty()) null else position + ONE_PAGE
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun addPhoto(estates: List<Estate>): List<Estate> {
        for (element in estates) {
            element.photos = listOf(getPhotoId(), getPhotoId(), getPhotoId())
        }
        return estates
    }

    private fun getPhotoId(): Int {
        return listOf(
            R.drawable.default_card_photo_1,
            R.drawable.default_card_photo_2,
            R.drawable.default_card_photo_3
        ).random()
    }

    companion object {
        const val ONE_PAGE = 1
        const val NETWORK_PAGE_SIZE = EstatePagerConfig.NETWORK_PAGE_SIZE
    }
}