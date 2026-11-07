package com.example.serviceexample

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.serviceexample.databinding.ActivityMainBinding

class  MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
          binding.startBtn.setOnClickListener {
              startService(0)
          }
          binding.pauseBtn.setOnClickListener {
              startService(1)
          }
          binding.stopBtn.setOnClickListener {
              startService(2)
          }

    }
    private fun startService(code:Int) {
        Intent(this,ForegroundService::class.java).apply {
            putExtra("code",code)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(this)
            }else{
                startService(this)
            }
        }
    }
}