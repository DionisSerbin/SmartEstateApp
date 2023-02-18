package smart.estate.app.data.model.estate

import com.google.gson.annotations.SerializedName
import smart.estate.app.data.model.estate.DataValidatorInterface

data class AddedDataClass(
    @SerializedName("estate_price")
    var price: String,
    @SerializedName("objectType")
    var objectType: Int,
    @SerializedName("houseType")
    var houseType: Int,
    @SerializedName("city")
    var city: String,
    @SerializedName("level")
    var level: String,
    @SerializedName("levels")
    var levels: String,
    @SerializedName("numberOfRooms")
    var numberOfRooms: String,
    @SerializedName("totalArea")
    var totalArea: String,
    @SerializedName("kitchenArea")
    var kitchenArea: String,
    @SerializedName("time")
    var time: String
) : DataValidatorInterface()
