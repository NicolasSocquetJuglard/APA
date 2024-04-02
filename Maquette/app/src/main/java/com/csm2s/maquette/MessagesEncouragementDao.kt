package com.csm2s.maquette

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MessagesEncouragementDao {
    @Query("SELECT * FROM messagesEncouragement")
    fun getAllMessagesEncouragement(): List<MessagesEncouragement>
    @Query("SELECT * FROM messagesEncouragement WHERE messageId IN (:idList)")
    fun loadMessagesEncouragementByIds(idList: IntArray): List<MessagesEncouragement>
    @Query("SELECT * FROM messagesEncouragement WHERE message LIKE :first")
    fun findMessagesEncouragementByMessage(first: String): MessagesEncouragement
    @Insert
    fun insertMessagesEncouragement(vararg messagesEncouragement: MessagesEncouragement)
    @Delete
    fun deleteMessagesEncouragement(messagesEncouragement: MessagesEncouragement)
    @Update
    fun updateMessagesEncouragement(messagesEncouragement: MessagesEncouragement)
}