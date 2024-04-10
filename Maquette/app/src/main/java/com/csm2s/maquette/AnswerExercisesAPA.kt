package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "answerExercisesAPA",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["sessionId"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerExercisesAPA(
    @PrimaryKey(autoGenerate = true) val answerExercisesAPAId : Int,
    @ColumnInfo(name = "nb_exercises") val nb_exercises: Int,
    @ColumnInfo(name = "difficulty") val difficulty: Int,
    @ColumnInfo(name = "pain") val pain: Int,
    @ColumnInfo(name = "sessionId") val sessionId: Int
)