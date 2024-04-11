package com.csm2s.maquette

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.AlarmManager.RTC_WAKEUP
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

const val NOTIFICATION_ID = 666

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createNotificationChannel()
        scheduleNotification(this)

        val db = AppDatabase.getInstance(applicationContext)
        val userDao = db.userDao()
        val listUsers = userDao.getAllUsers()
        if(listUsers.isEmpty()){
            val firstUser = User(1,
                "Nicolas",
                "Socquet-Juglard",
                22,
                "javelin_boss")
            userDao.insertUsers(firstUser)
        }

        val emailView = findViewById<EditText>(R.id.editTextEmail)
        val passwordView = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.isEnabled = false

        val myTextWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val email = emailView.text.toString().trim()
                val password = passwordView.text.toString().trim()
                buttonLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
            }
        }
        passwordView.addTextChangedListener(myTextWatcher)
        emailView.addTextChangedListener(myTextWatcher)

        val trueEmail = "g"
        val truePassword = "g"

        buttonLogin.setOnClickListener {
            val email = emailView.text.toString().trim()
            val password = passwordView.text.toString().trim()

            if(email == trueEmail && password == truePassword){
                Intent(this, MainActivity::class.java).also{
                    startActivity(it)
                }
            }else{
                Toast.makeText(this, "Email ou mot de passe incorrect.", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun createNotificationChannel(){
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            this.getString(R.string.notif_channel_id),
            this.getString(R.string.notif_channel_name),
            importance).apply{description = "Notification for APA"}
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


    private fun scheduleNotification(context: Context?){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, FLAG_IMMUTABLE)
        val calendar = Calendar.getInstance().apply{
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 17)
            set(Calendar.MINUTE, 37)
            set(Calendar.SECOND, 0)
            if(timeInMillis <= System.currentTimeMillis()){add(Calendar.DAY_OF_MONTH, 1)}
        }
        alarmManager.setRepeating(
            RTC_WAKEUP,
            calendar.timeInMillis,
            INTERVAL_DAY,
            pendingIntent)
    }
}





