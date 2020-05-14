package com.example.motionsensorball

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class MyView(context: Context?) : View(context)
{
    private val paint: Paint
    private var screenWidth = 0
    private var screenHeight = 0
    private var posX = 0
    private var posY = 0
    private val ballRadius = 100
    private var gameActive = true

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int)
    {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        screenWidth = width
        screenHeight = height
        posX = 500
        posY = 300
    }

    fun onSensorEvent(event: SensorEvent)
    {
        if (gameActive)
        {
            posX = posX - event.values[0].toInt()
            posY = posY + event.values[1].toInt()
        }
        if (posX <= 0 + ballRadius)
        {
            posX = 0 + ballRadius
            if (screenWidth > 0)
            {
                gameActive = false
            }
        }
        if (posX >= screenWidth - ballRadius)
        {
            posX = screenWidth - ballRadius
            if (screenWidth > 0)
            {
                gameActive = false
            }
        }
        if (posY <= 0 + ballRadius)
        {
            posY = 0 + ballRadius
            if (screenWidth > 0)
            {
                gameActive = false
            }
        }
        if (posY >= screenHeight - ballRadius)
        {
            posY = screenHeight - ballRadius
            if (screenWidth > 0)
            {
                gameActive = false
            }
        }
    }

    override fun onDraw(canvas: Canvas)
    {
        if (!gameActive)
        {
            paint.color = Color.RED
        }
        canvas.drawCircle(posX.toFloat(), posY.toFloat(), ballRadius.toFloat(), paint)
        invalidate()
    }

    init
    {
        paint = Paint()
        paint.color = Color.BLUE
    }
}