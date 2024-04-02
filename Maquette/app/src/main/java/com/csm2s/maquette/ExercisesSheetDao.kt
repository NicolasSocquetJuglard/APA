package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExercisesSheetDao {
    @Query("SELECT * FROM exercisesSheet")
    fun getAllExercisesSheet(): List<ExercisesSheet>
    @Query("SELECT * FROM exercisesSheet WHERE sheetId IN (:idList)")
    fun loadExercisesSheetByIds(idList: Int): List<ExercisesSheet>
    @Insert
    fun insertExercisesSheet(vararg exercisesSheet: ExercisesSheet)
    @Delete
    fun deleteExercisesSheet(exercisesSheet: ExercisesSheet)
    @Update
    fun updateExercisesSheet(exercisesSheet: ExercisesSheet)
}