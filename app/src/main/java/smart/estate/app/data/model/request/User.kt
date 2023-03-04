package smart.estate.app.data.model.request

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_mail")
    var mail: String
)