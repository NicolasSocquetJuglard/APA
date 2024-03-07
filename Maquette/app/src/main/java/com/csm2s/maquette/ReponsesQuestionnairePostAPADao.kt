package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReponsesQuestionnairePostAPADao {
    @Query("SELECT * FROM reponses")
    fun getAllReponses(): List<ReponsesQuestionnairePostAPA>
    @Query("SELECT * FROM reponses WHERE reponseid IN (:idList)")
    fun loadReponsesByIds(idList: Int): List<ReponsesQuestionnairePostAPA>
    @Insert
    fun insertReponses(vararg reponses: ReponsesQuestionnairePostAPA)
    @Delete
    fun deleteReponse(reponse: ReponsesQuestionnairePostAPA)
    @Update
    fun updateReponse(reponse: ReponsesQuestionnairePostAPA)
}