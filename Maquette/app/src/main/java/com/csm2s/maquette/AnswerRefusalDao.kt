package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswerRefusalDao {
    @Query("SELECT * FROM refusal")
    fun getAllAnswerRefusal(): List<AnswerRefusal>
    @Query("SELECT * FROM refusal WHERE refusalId IN (:idList)")
    fun loadAnswerRefusalByIds(idList: Int): List<AnswerRefusal>
    @Insert
    fun insertAnswerRefusal(vararg refusal: AnswerRefusal)
    @Delete
    fun deleteAnswerRefusal(refusal: AnswerRefusal)
    @Update
    fun updateAnswerRefusal(refusal: AnswerRefusal)
}