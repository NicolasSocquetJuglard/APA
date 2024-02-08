package com.csm2s.maquette

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    // utiliser des SharedPreferences (voir sur internet)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val txtUsername = findViewById<TextView>(R.id.textViewUsernameProfile)

        val buttonModifyUsername = findViewById<ImageButton>(R.id.imageButtonModifyUsername)
        val txtNewUsername = findViewById<EditText>(R.id.editTextModifyUsername)
        val buttonConfirmNewUsername = findViewById<Button>(R.id.buttonConfirmNewUsername)
        val myTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val username = txtNewUsername.text.toString().trim()
                buttonConfirmNewUsername.isEnabled = username.isNotEmpty()
            }
        }
        txtNewUsername.addTextChangedListener(myTextWatcher)
        buttonModifyUsername.setOnClickListener {
            buttonModifyUsername.isEnabled = false
            txtNewUsername.visibility = View.VISIBLE
            buttonConfirmNewUsername.visibility = View.VISIBLE
        }
        buttonConfirmNewUsername.setOnClickListener {
            buttonModifyUsername.isEnabled = true
            val newUsername = txtNewUsername.text.toString().trim()
            txtUsername.text = newUsername
            buttonConfirmNewUsername.isEnabled = false
            buttonConfirmNewUsername.visibility = View.GONE
            txtNewUsername.visibility = View.GONE
        }
        val buttonProfToMain = findViewById<Button>(R.id.buttonProfileToMain)
        buttonProfToMain.setOnClickListener {
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}