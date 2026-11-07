package com.example.serviceexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class BackgroundService:Service() {

    val TAG = "TAG"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: Service")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
           while (true){
               Log.d(TAG, "onStartCommand: Service")
               Thread.sleep(1000)
           }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Service")
    }
}