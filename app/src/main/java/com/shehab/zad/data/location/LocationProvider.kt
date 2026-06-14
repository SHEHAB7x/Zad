package com.shehab.zad.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LocationProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val fusedClient = LocationServices
        .getFusedLocationProviderClient(context)

    suspend fun getCurrentLocation(): Pair<Double, Double>?{
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) return null

        val lastLocation = fusedClient.lastLocation.await()
        if (lastLocation != null) {
            return Pair(lastLocation.latitude, lastLocation.longitude)
        }

        val cancellationToken = CancellationTokenSource()
        val freshLocation = fusedClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationToken.token
        ).await()

        return if (freshLocation != null) {
            Pair(freshLocation.latitude, freshLocation.longitude)
        } else {
            null
        }
    }

}