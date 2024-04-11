package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import kotlin.random.Random

import androidx.appcompat.app.AlertDialog


class QuestionnairePostApaActivity : AppCompatActivity() {
    private var nbExercicesRating: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire_post_apa)

        val db = AppDatabase.getInstance(applicationContext)
        val UserDao = db.userDao()
        var SessionDao = db.SessionDao()
        var list_session = SessionDao.getAllSessions()
        var lastSession = list_session.last()
        var newIndex = lastSession.sessionId

        var AnswerExercisesAPADao = db.AnswerExercisesAPADao()

        var physioDao = db.PhysioDao()

        val ratingBarNumberExercises = findViewById<RatingBar>(R.id.ratingBarNumberExercises)
        ratingBarNumberExercises.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            nbExercicesRating = rating.toInt() // Mise à jour du nombre d'étoiles sélectionnées
        }

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

        val buttonConfirmQuestionnairePostApa = findViewById<Button>(R.id.buttonConfirmQuestionnairePostApa)
        buttonConfirmQuestionnairePostApa.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Merci !")
            dialogBuilder.setMessage("Vos réponses ont bien été envoyées")

            var reponse_actuelle = AnswerExercisesAPA(newIndex, nbExercicesRating, seekBarDifficulty.progress, seekBarPain.progress, newIndex)
            AnswerExercisesAPADao.insertAnswerExercisesAPA(reponse_actuelle)

            val random = Random
            var hearthBeat = random.nextInt(80,150)
            var steps = random.nextInt(0, 1000)
            var calories = random.nextInt(0, 2500)
            var newPhysio = Physio(newIndex, "date", hearthBeat, steps, calories, newIndex)
            physioDao.insertPhysio(newPhysio)

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
