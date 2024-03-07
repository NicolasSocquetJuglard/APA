package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reponses")
data class ReponsesQuestionnairePostAPA(
    @PrimaryKey(autoGenerate = true) val reponseid : Int,
    @ColumnInfo(name = "nb_exercices") val nb_exercices: Int,
    @ColumnInfo(name = "difficulte") val difficulte: Int,
    @ColumnInfo(name = "douleur") val douleur: Int,
)
