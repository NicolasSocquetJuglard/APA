package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AnswerNoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_no)

        val buttonBack = findViewById<Button>(R.id.buttonFromQuestionnaireToHome)
        buttonBack.setOnClickListener {
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }

    }
}