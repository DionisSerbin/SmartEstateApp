package smart.estate.app.data.model.request

import com.google.gson.annotations.SerializedName

data class Prediction(
    @SerializedName("cost1")
    var cost1: Long,
    @SerializedName("cost2")
    var cost2: Long,
)
