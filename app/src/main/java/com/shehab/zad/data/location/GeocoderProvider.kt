package com.shehab.zad.data.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume

class GeocoderProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getCityName(latitude: Double, longitude: Double): String? {
        val geocoder = Geocoder(context, Locale("ar"))

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            suspendCancellableCoroutine { continuation ->
                geocoder.getFromLocation(
                    latitude, longitude, 1,
                    object : Geocoder.GeocodeListener {
                        override fun onGeocode(addresses: List<Address?>) {
                            continuation.resume(
                                addresses.firstOrNull()?.adminArea
                            )
                        }

                        override fun onError(errorMessage: String?) {
                            continuation.resume(null)
                        }
                    }
                )
            }
        } else {
            @Suppress("DEPRECATION")
            geocoder.getFromLocation(latitude,longitude,1)
                ?.firstOrNull()
                ?.let { it.locality ?: it.adminArea}
        }
    }
}