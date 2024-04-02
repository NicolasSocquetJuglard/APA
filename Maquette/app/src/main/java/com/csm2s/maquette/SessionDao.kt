package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SessionDao {
    @Query("SELECT * FROM sessions")
    fun getAllSessions(): List<Session>
    @Query("SELECT * FROM sessions WHERE sessionId IN (:idList)")
    fun loadSessionByIds(idList: IntArray): List<Session>
    @Query("SELECT * FROM sessions WHERE startDate LIKE :first")
    fun findSessionByStartDate(first: String): Session
    @Insert
    fun insertSession(vararg sessions: Session)
    @Delete
    fun deleteSession(session: Session)
    @Update
    fun updateSession(session: Session)
}