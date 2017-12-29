package com.randalvance.basicstartedservice

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.app.NotificationChannel
import android.graphics.Color


class DelayedMessageService : IntentService("DelayedMessageService") {

    companion object {
        val EXTRA_MESSAGE : String = "message"
        val NOTIFICATION_ID : Int = 5453
        val NOTIFICATION_CHANNEL_ID : String = "4655"
        val NOTIFICATION_CHANNEL_NAME : String = "MyChannel"
    }

    override fun onHandleIntent(intent: Intent?) {
        synchronized(this) {
            try {
                Thread.sleep(1000)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
        }
        val text = intent?.getStringExtra(EXTRA_MESSAGE)

        showText(text)
    }

    private fun showText(message: String?) {
        // Log.v(DelayedMessageService::class.simpleName, message)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
                .setAutoCancel(true)

        val actionIntent = Intent(this, MainActivity::class.java)
        val actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setContentIntent(actionPendingIntent)

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }


}
