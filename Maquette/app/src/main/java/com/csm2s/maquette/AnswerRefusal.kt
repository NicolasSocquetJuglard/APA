package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "refusal",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["sessionId"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnswerRefusal(
    @PrimaryKey(autoGenerate = true) val refusalId : Int,
    @ColumnInfo(name = "pain") val pain: Int,
    @ColumnInfo(name = "fatigue") val fatigue: Int,
    @ColumnInfo(name = "morale") val morale: Int,
    @ColumnInfo(name = "time") val time: Int,
    @ColumnInfo(name = "other") val other: Int,
    @ColumnInfo(name = "sessionId") val sessionId: Int
)