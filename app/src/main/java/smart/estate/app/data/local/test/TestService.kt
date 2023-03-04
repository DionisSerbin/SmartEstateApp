package smart.estate.app.data.local.test

import smart.estate.app.R
import smart.estate.app.data.model.estate.Estate
import smart.estate.app.data.model.estate.SmartDataClassParameters
import javax.inject.Inject

class TestService @Inject constructor() : TestInterface {

    override suspend fun getPrediction(smartEstateParameters: SmartDataClassParameters): Pair<Long, Long> {
        return Pair((5..10).random().toLong(), (5..10).random().toLong())
    }

    override suspend fun getEstates(page: Int): List<Estate> {
        TODO("Not yet implemented")
    }

//    override suspend fun getEstates(page: Int): List<Estate> {
//        val estateList: MutableList<Estate> = mutableListOf()
//        for (i in 0..9) {
//            val estate = Estate(
//                id = (page - 1) * 10 + i,
//                price = (5..10).random().toFloat(),
//                year = (2019..2022).random(),
//                month = (1..12).random(),
//                day = (1..30).random(),
//                time = "${(1..24).random()}:${(1..60).random()}",
//                latitude = (1..360).random().toFloat(),
//                longitude = (1..360).random().toFloat(),
//                region = (1..80).random(),
//                buildingType = (1..5).random(),
//                level = (1..100).random(),
//                levels = (1..300).random(),
//                rooms = (1..10).random(),
//                totalArea = (5..100).random().toFloat(),
//                kitchenArea = (5..40).random().toFloat(),
//                objectType = (1..5).random(),
//                photos = listOf(getPhotoId(), getPhotoId(), getPhotoId())
//                )
//            estateList.add(estate)
//        }
//        return estateList
//    }

    fun getPhotoId(): Int {
        return listOf(
            R.drawable.default_card_photo_1,
            R.drawable.default_card_photo_2,
            R.drawable.default_card_photo_3
        ).random()
    }
}