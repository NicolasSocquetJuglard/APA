package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messagesEncouragement")
data class MessagesEncouragement(
    @PrimaryKey val messageId: Int,
    @ColumnInfo(name = "message") val message: String,
)