package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswerExercisesDaoAPA {
    @Query("SELECT * FROM answerExercisesAPA")
    fun getAllAnswerExercisesAPA(): List<AnswerExercisesAPA>
    @Query("SELECT * FROM answerExercisesAPA WHERE answerExercisesAPAId IN (:idList)")
    fun loadAnswerExercisesAPAByIds(idList: Int): List<AnswerExercisesAPA>
    @Insert
    fun insertAnswerExercisesAPA(vararg response: AnswerExercisesAPA)
    @Delete
    fun deleteAnswerExercisesAPA(response: AnswerExercisesAPA)
    @Update
    fun updateAnswerExercises(response: AnswerExercisesAPA)
}