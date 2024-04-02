package com.csm2s.maquette

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "exercisesSheet",
    foreignKeys = [
        ForeignKey(
            entity = Session::class,
            parentColumns = ["sessionId"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExercisesSheet(
    @PrimaryKey(autoGenerate = true) val sheetId : Int,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "difficulty") val difficulty: Int,
    @ColumnInfo(name = "sessionId") val sessionId: Int
)