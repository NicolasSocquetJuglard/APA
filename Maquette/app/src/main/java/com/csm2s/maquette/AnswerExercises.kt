package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "answerExercises")
data class AnswerExercises(
    @PrimaryKey(autoGenerate = true) val answerExercisesId : Int,
    @ColumnInfo(name = "nb_exercices") val nb_exercices: Int,
    @ColumnInfo(name = "difficulte") val difficulte: Int,
    @ColumnInfo(name = "douleur") val douleur: Int,
)
