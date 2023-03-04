package smart.estate.app.data.model.request

import com.google.gson.annotations.SerializedName

data class UpdatedUser(
    @SerializedName("user_name")
    var userName: String,
    @SerializedName("user_login")
    var userLogin: String,
    @SerializedName("user_mail")
    var userMail: String
)
