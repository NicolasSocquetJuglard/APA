package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PhysioDao {
    @Query("SELECT * FROM physio")
    fun getAllPhysio(): List<Physio>
    @Query("SELECT * FROM physio WHERE physioId IN (:idList)")
    fun loadPhysioByIds(idList: Int): List<Physio>
    @Insert
    fun insertPhysio(vararg physio: Physio)
    @Delete
    fun deletePhysio(physio: Physio)
    @Update
    fun updatePhysio(physio: Physio)
}