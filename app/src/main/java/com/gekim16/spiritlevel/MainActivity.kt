package com.gekim16.spiritlevel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

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

    override fun onSensorChanged(event: SensorEvent?)
    {
        if(event?.sensor != gravitySensor) return

        event?.let {
            custom_view.setCirclePoint(it.values[0], it.values[1])
        }
    }
}