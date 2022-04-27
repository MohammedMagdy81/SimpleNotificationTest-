package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID="channelID"
    val CHANNEL_NAME="channelName"
    lateinit var btnShow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnShow= findViewById(R.id.btn_show_notific)
        createNotificationChannel()
        val intent= Intent(this,MainActivity::class.java)

        // this pending Intent is to open activity when click On notification
        val pendingIntent= TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        //proeper Notification
        val notification= NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Mohamed Magdy")
            .setContentText("Hi , How Are You")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_phone)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

            .build()

        val notificationManager= NotificationManagerCompat.from(this)
        btnShow.setOnClickListener {
            notificationManager.notify(0,notification)
        }

    }

    private fun createNotificationChannel(){
        //check Version Of Android
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH).apply {
                lightColor = Color.RED
                enableLights(true)
            }
            val notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

    }
}