package smart.estate.app.data.model

import com.google.gson.annotations.SerializedName

data class SmartEstateParameters(
    @SerializedName("objectType")
    var objectType: Int,
    @SerializedName("city")
    var city: String,
    @SerializedName("houseType")
    var houseType: Int,
    @SerializedName("levelFrom")
    var levelFrom: String,
    @SerializedName("levelTo")
    var levelTo: String,
    @SerializedName("levelsFrom")
    var levelsFrom: String,
    @SerializedName("levelsTo")
    var levelsTo: String,
    @SerializedName("numberOfRoomsFrom")
    var numberOfRoomsFrom: String,
    @SerializedName("numberOfRoomsTo")
    var numberOfRoomsTo: String,
    @SerializedName("totalAreaFrom")
    var totalAreaFrom: String,
    @SerializedName("totalAreaTo")
    var totalAreaTo: String,
    @SerializedName("kitchenAreaFrom")
    var kitchenAreaFrom: String,
    @SerializedName("kitchenAreaTo")
    var kitchenAreaTo: String
)