package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

import androidx.appcompat.app.AlertDialog


class QuestionnairePostApaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire_post_apa)

        val progressDifficulty = findViewById<TextView>(R.id.textViewProgressDifficulty)
        val seekBarDifficulty = findViewById<SeekBar>(R.id.seekBarDifficulty)
        val sbDifficultyListener = object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val dif = "$progress/10"
                progressDifficulty.text = dif
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }
        }
        seekBarDifficulty.setOnSeekBarChangeListener(sbDifficultyListener)

        val progressPain = findViewById<TextView>(R.id.textViewProgressPain)
        val seekBarPain = findViewById<SeekBar>(R.id.seekBarPain)
        val sbPainListener = object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val dif = "$progress/10"
                progressPain.text = dif
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do
            }
        }
        seekBarPain.setOnSeekBarChangeListener(sbPainListener)

        val buttonConfirmQuestionnairePostApa =
            findViewById<Button>(R.id.buttonConfirmQuestionnairePostApa)
        buttonConfirmQuestionnairePostApa.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Merci !")
            dialogBuilder.setMessage("Vos réponses ont bien été envoyées")
            // Il faudra récupérer les valeurs des 2 seekBars soit pour mettre sur SQLite soit les passer dans l'Intent
            dialogBuilder.setPositiveButton("Retour à l'accueil") { alertDialog, which ->
                Intent(this, ProfileActivity::class.java).also {
                    startActivity(it)
                }
            }
            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }
    }
}
