package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

        val trueEmail = "go@gmail.com"
        val truePassword = "go"

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
}