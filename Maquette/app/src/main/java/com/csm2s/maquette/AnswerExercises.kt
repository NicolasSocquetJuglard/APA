package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "answerExercises",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["sessionId"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerExercises(
    @PrimaryKey(autoGenerate = true) val answerExercisesId : Int,
    @ColumnInfo(name = "nb_exercises") val nb_exercices: Int,
    @ColumnInfo(name = "difficulty") val difficulte: Int,
    @ColumnInfo(name = "pain") val douleur: Int,
    @ColumnInfo(name = "sessionId") val sessionId: Int
)
