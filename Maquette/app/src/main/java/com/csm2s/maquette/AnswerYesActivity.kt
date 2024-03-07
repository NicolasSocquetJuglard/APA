package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

const val NUMBER_APA = 12

class AnswerYesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_yes)

        val buttonBackApa = findViewById<Button>(R.id.buttonBackApa)
        buttonBackApa.setOnClickListener {
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }

        val buttonFinishedApa = findViewById<Button>(R.id.buttonFinishedApa)
        buttonFinishedApa.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Finir la séance et passer au questionnaire ?")
            dialogBuilder.setMessage("Une fois la séance terminée, vous ne pourrez plus revenir en arrière !")
            dialogBuilder.setPositiveButton("Confirmer") {
                alertDialog, which ->
                Intent(this, QuestionnairePostApaActivity::class.java).also{
                    startActivity(it)
                }
            }
            dialogBuilder.setNegativeButton("Retour") {
                alertDialog, which ->
            }

            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }

        val imageViewApa1 = findViewById<ImageView>(R.id.imageViewApa1)
        val imageViewApa2 = findViewById<ImageView>(R.id.imageViewApa2)
        val (apaId1, apaId2) = chooseAPA(imageViewApa1)
        imageViewApa1.setImageResource(apaId1)
        imageViewApa2.setImageResource(apaId2)
    }

    fun chooseAPA(imView: ImageView): Pair<Int, Int> {

        val db = AppDatabase.getInstance(applicationContext)
        val reponsesDao = db.ReponsesQuestionnairePostAPADao()
        val listReponses = reponsesDao.getAllReponses()
        val lastThreeReponses = listReponses.takeLast(3)
        var somme_difficulte = 0
        var somme_douleur = 0
        var moyenne_difficulte = 0
        var moyenne_douleur = 0
        var score = 80
        val coef_difficulte = 2
        val coef_douleur = 2

        for (reponse in lastThreeReponses){
            somme_difficulte += reponse.difficulte
            somme_douleur += reponse.douleur
        }
        moyenne_difficulte = somme_difficulte/3
        moyenne_douleur = somme_douleur/3

        score += coef_difficulte*moyenne_difficulte + coef_douleur*moyenne_douleur
        val numApa = Random.nextInt(1,NUMBER_APA+1)
        var numStar: Int = 1
        if(score <= 90){
            numStar = 3
        }
        else if (score >= 110){
            numStar = 1
        }
        else {
            numStar = 2
        }

        val textViewScore = findViewById<TextView>(R.id.textViewScore)
        var displayText = "Score = $score numStar = $numStar"
        textViewScore.text = displayText

        val nameApa1 = "s$numStar"+"e_$numApa"+"p1"
        val nameApa2 = "s$numStar"+"e_$numApa"+"p2"

        val idImageApa1 = resources.getIdentifier(nameApa1, "drawable", imView.context.packageName)
        val idImageApa2 = resources.getIdentifier(nameApa2, "drawable", imView.context.packageName)

        val ids = Pair<Int, Int>(idImageApa1, idImageApa2)
        return ids
    }

    fun getApaId(imView: ImageView): Pair<Int, Int> {


        val numApa = Random.nextInt(1,NUMBER_APA+1)
        val numStar = Random.nextInt(1,3+1)
        val nameApa1 = "s$numStar"+"e_$numApa"+"p1"
        val nameApa2 = "s$numStar"+"e_$numApa"+"p2"

        val idImageApa1 = resources.getIdentifier(nameApa1, "drawable", imView.context.packageName)
        val idImageApa2 = resources.getIdentifier(nameApa2, "drawable", imView.context.packageName)

        val ids = Pair<Int, Int>(idImageApa1, idImageApa2)
        return ids
    }
}

