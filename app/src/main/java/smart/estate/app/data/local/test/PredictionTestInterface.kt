package smart.estate.app.data.local.test

import smart.estate.app.data.model.SmartEstateParameters

interface PredictionTestInterface {

    suspend fun getPrediction(smartEstateParameters: SmartEstateParameters): Pair<Long, Long>

}