package smart.estate.app.data.model.estate

import com.google.gson.annotations.SerializedName
import smart.estate.app.data.model.estate.DataValidatorInterface
import java.io.Serializable

data class EstateResponse(
    @SerializedName("items")
    val items: List<Estate>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)

data class Estate(
    @SerializedName("address")
    val address: String,
    @SerializedName("area")
    val totalArea: Float,
    @SerializedName("building_type")
    val buildingType: Int,
    @SerializedName("day")
    val day: Int,
    @SerializedName("estate_id")
    val id: Int,
    @SerializedName("kitchen_area")
    val kitchenArea: Float,
    @SerializedName("latitude")
    val latitude: Float,
    @SerializedName("level")
    val level: Int,
    @SerializedName("levels")
    val levels: Int,
    @SerializedName("longitude")
    val longitude: Float,
    @SerializedName("month")
    val month: Int,
    @SerializedName("object_type")
    val objectType: Int,
    @SerializedName("price")
    val price: Float,
    @SerializedName("region")
    val region: Int,
    @SerializedName("region_name")
    val regionName: Any,
    @SerializedName("rooms")
    val rooms: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("user_id")
    val userId: Any,
    @SerializedName("year")
    val year: Int,
    var photos: List<Int>
): DataValidatorInterface()
