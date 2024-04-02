package com.csm2s.maquette

import android.Manifest
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver: BroadcastReceiver() {
    private var notificationManager: NotificationManagerCompat? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        val tappedIntent = Intent(context, MainActivity::class.java)
        tappedIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingTappedIntent: PendingIntent = PendingIntent.getActivity(context, 0, tappedIntent, FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT)
        val notifAPA = context?.let{
            NotificationCompat.Builder(it, it.getString(R.string.notif_channel_id))
                .setContentTitle(it.getString(R.string.app_name))
                .setContentText(it.getString(R.string.notif_content_text))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingTappedIntent)
                .build()}
        notificationManager = context?.let{NotificationManagerCompat.from(it)}
        notifAPA?.let{
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notificationManager?.notify(NOTIFICATION_ID, it)}
    }
}