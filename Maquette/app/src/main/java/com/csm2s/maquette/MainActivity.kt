package com.csm2s.maquette

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getInstance(applicationContext)
        val userDao = db.userDao()
        val usersByIds = userDao.loadUsersByIds(intArrayOf(1))
        val currentUser = usersByIds[0]
        val username = currentUser.username

        val viewWelcomeQuestion = findViewById<TextView>(R.id.welcomeQuestion)
        val txtWelcomeQuestion = "Salut "+username+".\n"+applicationContext.getString(R.string.label_first_question_short)
        viewWelcomeQuestion.text = txtWelcomeQuestion

        val random = Random
        var index = random.nextInt(1, 7);
        val viewMessage = findViewById<TextView>(R.id.textViewAboutAPA)
        val message = getString(resources.getIdentifier("message$index", "string", packageName))
        viewMessage.text = message

        val buttonYes = findViewById<Button>(R.id.buttonAnswerYes)
        buttonYes.setOnClickListener {
            Intent(this, AnswerYesActivity::class.java).also {
            startActivity(it)
            }
        }
        val buttonNo = findViewById<Button>(R.id.buttonAnswerNo)
        buttonNo.setOnClickListener {
            Intent(this, AnswerNoActivity::class.java).also {
                startActivity(it)
            }
        }
        val buttonProfile = findViewById<Button>(R.id.buttonGoToProfile)
        buttonProfile.setOnClickListener {
            Intent(this, ProfileActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}