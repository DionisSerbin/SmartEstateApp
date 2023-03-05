package smart.estate.app.presentation.favourites.viewmodel


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import smart.estate.app.R
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.repository.ApiRepository
import smart.estate.app.utils.Resource
import javax.inject.Inject


@HiltViewModel
class FavouritesViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    suspend fun getFavouriteEstates(mail: String): List<Estate>? {

        Resource.loading(null)
        try {
            val estatesResource = Resource.success(data = apiRepository.getFavouriteEstates(mail).body())
            var estates = estatesResource.data!!
            estates = addPhoto(estates)
            return estates
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Возникла ошибка", data = null)
            return null
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
}