package com.example.motionsensorball

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class MainActivity : Activity(), SensorEventListener
{
    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    private var myView: MyView? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        myView = MyView(this)
        setContentView(myView)
    }

    override fun onSensorChanged(event: SensorEvent)
    {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER)
        {
            myView!!.onSensorEvent(event)
        }
    }

    override fun onResume()
    {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause()
    {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(param1: Sensor, param2: Int)
    {
    }
}