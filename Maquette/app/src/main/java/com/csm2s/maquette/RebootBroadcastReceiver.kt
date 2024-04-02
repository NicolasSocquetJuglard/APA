package com.csm2s.maquette

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.Calendar

class RebootBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val intentAction = intent?.action
        if (intentAction == Intent.ACTION_BOOT_COMPLETED){
            scheduleNotification(context)
        }
    }


    private fun scheduleNotification(context: Context?){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val calendar = Calendar.getInstance().apply{
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 17)
            set(Calendar.MINUTE, 37)
            set(Calendar.SECOND, 0)
            if(timeInMillis <= System.currentTimeMillis()){add(Calendar.DAY_OF_MONTH, 1)}
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent)
    }

}