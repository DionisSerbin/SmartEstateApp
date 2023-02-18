package smart.estate.app.data.model

import com.google.gson.annotations.SerializedName
import smart.estate.app.data.model.estate.DataValidatorInterface

data class UserSettings(
    @SerializedName("name")
    var name: String,
    @SerializedName("login")
    var login: String
): DataValidatorInterface()