package smart.estate.app.data.model_processing

import android.content.Context
import android.location.Geocoder
import java.util.*

class LocationGeocoder {
    fun getAddress(context: Context, latitude: Float, longitude: Float): String {
        val addresses = getAddresses(
            context = context,
            latitude = latitude,
            longitude = longitude
        )
        return "${addresses?.get(0)?.locality}, ${addresses?.get(0)?.getAddressLine(0)}"
    }

    private fun getAddresses(context: Context, latitude: Float, longitude: Float) =
        Geocoder(context, Locale.getDefault()).getFromLocation(
            latitude.toDouble(),
            longitude.toDouble(),
            1
        )
}