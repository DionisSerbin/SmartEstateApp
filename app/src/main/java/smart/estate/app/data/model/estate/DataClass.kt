package smart.estate.app.data.model.estate

import com.google.gson.annotations.SerializedName
import smart.estate.app.data.model.estate.DataValidatorInterface

data class DataClass(
    @SerializedName("estate_id")
    var id: Int,
    @SerializedName("estate_price")
    var price: Float,
    @SerializedName("estate_year")
    var year: Int,
    @SerializedName("estate_month")
    var month: Int,
    @SerializedName("estate_day")
    var day: Int,
    @SerializedName("estate_time")
    var time: String,
    @SerializedName("estate_latitude")
    var latitude: Float,
    @SerializedName("estate_longitude")
    var longitude: Float,
    @SerializedName("estate_region")
    var region: Int,
    @SerializedName("estate_building_type")
    var buildingType: Int,
    @SerializedName("estate_level")
    var level: Int,
    @SerializedName("estate_levels")
    var levels: Int,
    @SerializedName("estate_rooms")
    var rooms: Int,
    @SerializedName("estate_area")
    var totalArea: Float,
    @SerializedName("estate_kitchen_area")
    var kitchenArea: Float,
    @SerializedName("estate_object_type")
    var objectType: Int,
    @SerializedName("estate_photos")
    var photos: List<Int>
) : DataValidatorInterface()
