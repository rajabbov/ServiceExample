package com.example.serviceexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class ForegroundService : Service() {

    val TAG = "TAG"
    val CHANNEL_ID = "CHANNEL_ID"

    private var player: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.music)
        Log.d(TAG, "onCreate: Service")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.getIntExtra("code", -1)) {
            0 -> {
                player?.start()
            }

            1 -> {
                player?.apply {
                    if (isPlaying) {
                        pause()
                    } else
                        start()
                }
            }

            else -> {
                stopSelf()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "ChannelName",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lightColor = Color.GREEN
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )

            val notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("This is the Title")
                .setContentText("This is the Text")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build()
            startForeground(1111, notification)

        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
        Log.d(TAG, "onDestroy: Service")
    }
}