package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView

import androidx.appcompat.app.AlertDialog


class QuestionnairePostApaActivity : AppCompatActivity() {
    private var nbExercicesRating: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire_post_apa)

        val db = AppDatabase.getInstance(applicationContext)
        val UserDao = db.userDao()
        val SessionDao = db.SessionDao()
        val AnswerExercisesDaoAPA = db.AnswerExercisesDaoAPA()

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
            val AllUsers = UserDao.getAllUsers()
            val current_user = AllUsers[0]
            // Il faudra récupérer les valeurs des 2 seekBars soit pour mettre sur SQLite soit les passer dans l'Intent
            val list_session = SessionDao.getAllSessions()

            val reponse_actuelle = AnswerExercisesAPA(0, nbExercicesRating, seekBarDifficulty.progress, seekBarPain.progress, list_session.size)
            AnswerExercisesDaoAPA.insertAnswerExercisesAPA(reponse_actuelle)
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
