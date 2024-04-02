package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "sessions",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Session(
    @PrimaryKey(autoGenerate = true) val sessionId : Int,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "endDate") val endDate: String,
    @ColumnInfo(name = "userId") val userId: Int
)