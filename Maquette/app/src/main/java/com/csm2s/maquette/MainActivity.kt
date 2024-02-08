package com.csm2s.maquette

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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