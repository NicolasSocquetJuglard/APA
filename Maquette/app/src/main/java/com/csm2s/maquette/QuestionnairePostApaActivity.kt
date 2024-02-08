package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class QuestionnairePostApaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire_post_apa)

        val buttonConfirmQuestionnairePostApa =
            findViewById<Button>(R.id.buttonConfirmQuestionnairePostApa)
        buttonConfirmQuestionnairePostApa.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Merci !")
            dialogBuilder.setMessage("Vos réponses ont bien été envoyées")
            dialogBuilder.setPositiveButton("Retour à l'accueil") { alertDialog, which ->
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }
    }
}