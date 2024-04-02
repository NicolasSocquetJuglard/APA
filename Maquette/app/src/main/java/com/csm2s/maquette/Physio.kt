package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "physio",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["sessionId"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Physio(
    @PrimaryKey(autoGenerate = true) val physioId : Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "heartBeat") val heartBeat: Int,
    @ColumnInfo(name = "steps") val steps: Int,
    @ColumnInfo(name = "calories") val calories: Int,
    @ColumnInfo(name = "sessionId") val sessionId: Int
)