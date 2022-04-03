package com.gekim16.spiritlevel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.atan2

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val sensorManager by lazy { this.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    private val gravitySensor by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor != gravitySensor) return

        event?.let {
            val x = it.values[0].toDouble()
            val y = it.values[1].toDouble()
            val z = event.values[2].toDouble()
            val angleXZ = atan2(x,  z) * 180/Math.PI
            val angleYZ = atan2(y,  z) * 180/Math.PI

            horizontal_level.setCirclePoint(x, y)
            vertical_level.setCirclePoint(x, y)
            center_level.setCirclePoint(x, y)

            text_view.setText(
                String.format(
                    getString(R.string.sensor_value),
                    String.format("%.2f", angleXZ),
                    String.format("%.2f", angleYZ)
                )
            )
        }
    }
}