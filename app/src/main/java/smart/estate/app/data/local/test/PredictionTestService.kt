package smart.estate.app.data.local.test

import android.util.Log
import smart.estate.app.data.model.SmartEstateParameters
import javax.inject.Inject

class PredictionTestService @Inject constructor() : PredictionTestInterface{

    override suspend fun getPrediction(smartEstateParameters: SmartEstateParameters): Pair<Long, Long> {
        return Pair((5..10).random().toLong(), (5..10).random().toLong())
    }
}