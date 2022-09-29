package com.metehanmengen.app.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.metehanmengen.app.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stopTime: Long = 0

    private fun startButtonClickCallback()
    {
        binding.choronometer.base = SystemClock.elapsedRealtime() + stopTime

        binding.choronometer.start()
        binding.startButton.visibility = View.GONE
        binding.pauseButton.visibility = View.VISIBLE
        binding.imageViewStart.setImageDrawable(getDrawable(R.drawable.pause))

        Toast.makeText(this, "Chorometer Started !", Toast.LENGTH_LONG).show()
    }

    private fun pauseButtonClickCallback()
    {
        stopTime = binding.choronometer.base - SystemClock.elapsedRealtime()
        binding.choronometer.stop()
        binding.pauseButton.visibility = View.GONE
        binding.startButton.visibility = View.VISIBLE
        binding.imageViewStart.setImageDrawable(getDrawable(R.drawable.start))

        Toast.makeText(this, "Chorometer Stopped !", Toast.LENGTH_SHORT).show()
    }

    private fun resetButtonClickCallback()
    {
        binding.choronometer.base = SystemClock.elapsedRealtime()

        binding.choronometer.stop()
        binding.pauseButton.visibility = View.GONE
        binding.startButton.visibility = View.VISIBLE
        binding.imageViewStart.setImageDrawable(getDrawable(R.drawable.start))

        Toast.makeText(this, "-- Chorometer Cleared --", Toast.LENGTH_SHORT).show()
    }

    private fun timeCalculator()
    {
        binding.startButton.setOnClickListener{startButtonClickCallback()}
        binding.pauseButton.setOnClickListener{pauseButtonClickCallback()}
        binding.resetButton.setOnClickListener{resetButtonClickCallback()}
    }

    private fun initialize()
    {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timeCalculator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }
}