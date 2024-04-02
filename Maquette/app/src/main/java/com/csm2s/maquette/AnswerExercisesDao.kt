package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswerExercisesDao {
    @Query("SELECT * FROM answerExercises")
    fun getAllAnswerExercises(): List<AnswerExercises>
    @Query("SELECT * FROM answerExercises WHERE answerExercisesId IN (:idList)")
    fun loadAnswerExercisesByIds(idList: Int): List<AnswerExercises>
    @Insert
    fun insertAnswerExercises(vararg reponses: AnswerExercises)
    @Delete
    fun deleteAnswerExercises(reponse: AnswerExercises)
    @Update
    fun updateAnswerExercises(reponse: AnswerExercises)
}