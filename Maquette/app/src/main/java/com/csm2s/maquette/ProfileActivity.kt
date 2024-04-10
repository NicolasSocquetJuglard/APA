package com.csm2s.maquette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlin.random.Random

class ProfileActivity : AppCompatActivity() {
    // utiliser des SharedPreferences (voir sur internet)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val txtUsername = findViewById<TextView>(R.id.textViewUsernameProfile)

        val db = AppDatabase.getInstance(applicationContext)

        val userDao = db.userDao()
        val listUser = userDao.getAllUsers()
        val usersByIds = userDao.loadUsersByIds(intArrayOf(1))
        val currentUsername: String
        val currentUser: User = usersByIds[0]
        currentUsername = currentUser.username
        txtUsername.text = currentUsername

        val AnswerExercisesDaoAPA = db.AnswerExercisesDaoAPA()
        val listReponses = AnswerExercisesDaoAPA.getAllAnswerExercisesAPA()

        val physioDao = db.PhysioDao()
        val listPhysio = physioDao.getAllPhysio()

        val SessionDao = db.SessionDao()
        val listSession = SessionDao.getAllSessions()

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
            val updatedUser = User(
                currentUser.userId,
                currentUser.firstName,
                currentUser.lastName,
                currentUser.age,
                newUsername)
            userDao.updateUser(updatedUser)
        }

        val random = Random
/*
        for(session in listSession)
        {
            //var newSession = Session(i, "début", "fin", 1)
            //SessionDao.insertSession(newSession)
            var current_sessionId = session.sessionId

            var hearthBeat = random.nextInt(80, 140)
            var steps = random.nextInt(0, 1000)
            var calories = random.nextInt(0, 2500)


            var newPhysio = Physio(0, "date", hearthBeat, steps, calories, current_sessionId)
            physioDao.insertPhysio(newPhysio)

            var nb_exercises = random.nextInt(0,6)
            var difficulty = random.nextInt(0, 10)
            var pain = random.nextInt(0, 10)
            var newAnswer = AnswerExercisesAPA(0, nb_exercises, difficulty, pain, current_sessionId)
            AnswerExercisesDaoAPA.insertAnswerExercisesAPA(newAnswer)
        }
*/
/*
        val textViewReponses = findViewById<TextView>(R.id.textViewReponses)
        var displayText = ""

        for (physio in listPhysio) {
            displayText += "ID: ${physio.physioId}, session: ${physio.sessionId}, heart: ${physio.heartBeat}\n"//, Nb Exercises: ${reponse.nb_exercises}, Difficulty: ${reponse.difficulty}, Pain: ${reponse.pain}, Session: ${reponse.sessionId}\n"
        }

        textViewReponses.text = displayText

*/

        val buttonProfToMain = findViewById<Button>(R.id.buttonProfileToMain)
        buttonProfToMain.setOnClickListener {
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }
//Graphique affichant les battements de coeur de chaque session
        val chart1 = findViewById<LineChart>(R.id.chart1)

        val entries1 = ArrayList<Entry>()
        var x1 = 0f;
        var y1 = 0f;
        for (physio in listPhysio) {
            x1 = physio.sessionId.toFloat()
            y1 = physio.heartBeat.toFloat()
            entries1.add(Entry(x1, y1))
        }
        val dataSet1 = LineDataSet(entries1, "Label")
        dataSet1.color = android.graphics.Color.RED
        dataSet1.valueTextColor = android.graphics.Color.BLACK

        val lineData1 = LineData(dataSet1)
        chart1.data = lineData1

        chart1.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                // Réagir à la sélection de valeur
            }

            override fun onNothingSelected() {
                // Réagir à aucune sélection de valeur
            }
        })

        chart1.invalidate()
//Graphique affichant les calories de chaque session
        val chart2 = findViewById<LineChart>(R.id.chart2)

        val entries2 = ArrayList<Entry>()
        var x2 = 0f;
        var y2 = 0f;
        for (physio in listPhysio) {
            x2 = physio.sessionId.toFloat()
            y2 = physio.calories.toFloat()
            entries2.add(Entry(x2, y2))
        }
        val dataSet2 = LineDataSet(entries2, "Label")
        dataSet2.color = android.graphics.Color.BLUE
        dataSet2.valueTextColor = android.graphics.Color.BLACK

        val lineData2 = LineData(dataSet2)
        chart2.data = lineData2

        chart2.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                // Réagir à la sélection de valeur
            }

            override fun onNothingSelected() {
                // Réagir à aucune sélection de valeur
            }
        })

        chart2.invalidate()

    }
}