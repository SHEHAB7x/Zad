package com.shehab.zad.data.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SensorProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getAzimuth(): Flow<Float> = callbackFlow{
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        var gravity = FloatArray(3)
        var geomagnetic = FloatArray(3)

        val listener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

            override fun onSensorChanged(event: SensorEvent?) {
                event ?: return

                when (event.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> gravity = event.values.clone()
                    Sensor.TYPE_MAGNETIC_FIELD -> geomagnetic = event.values.clone()
                }

                val rotationMatrix = FloatArray(9)
                val orientation = FloatArray(3)

                if (SensorManager.getRotationMatrix(rotationMatrix, null, gravity, geomagnetic)) {
                    SensorManager.getOrientation(rotationMatrix, orientation)
                    val azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()
                    val normalized = (azimuth + 360) % 360
                    Log.d("SensorProvider", "Azimuth emitted: $normalized")
                    trySend(normalized)
                }
            }
        }

        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(listener, magnetometer, SensorManager.SENSOR_DELAY_UI)

        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }
}