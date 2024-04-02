package com.csm2s.maquette

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS answerExercises (answerExercisesId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nb_exercices INTEGER NOT NULL, difficulte INTEGER NOT NULL, douleur INTEGER NOT NULL);")
        //database.execSQL("ALTER TABLE users RENAME COLUMN uid TO userId;")
        database.execSQL("CREATE TABLE IF NOT EXISTS sessions (sessionId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, endDate TEXT NOT NULL, userId INTEGER NOT NULL, startDate TEXT NOT NULL, FOREIGN KEY (userId) REFERENCES users(userId) ON DELETE CASCADE);")
        database.execSQL("CREATE TABLE IF NOT EXISTS refusal (refusalId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pain INTEGER NOT NULL, fatigue INTEGER NOT NULL, morale INTEGER NOT NULL, time INTEGER NOT NULL, other INTEGER NOT NULL, sessionId INTEGER NOT NULL, FOREIGN KEY (sessionId) REFERENCES sessions(sessionId) ON DELETE CASCADE);")
        database.execSQL("CREATE TABLE IF NOT EXISTS exercisesSheet (sheetId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, number INTEGER NOT NULL, difficulty INTEGER NOT NULL, sessionId INTEGER NOT NULL, FOREIGN KEY (sessionId) REFERENCES sessions(sessionId) ON DELETE CASCADE);")
        database.execSQL("CREATE TABLE IF NOT EXISTS messagesEncouragement (messageId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, message TEXT NOT NULL);")
        database.execSQL("CREATE TABLE IF NOT EXISTS physio (physioId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, date TEXT NOT NULL, heartBeat INTEGER NOT NULL, steps INTEGER NOT NULL, calories INTEGER NOT NULL, sessionId INTEGER NOT NULL, FOREIGN KEY (sessionId) REFERENCES sessions(sessionId) ON DELETE CASCADE);")

    }
}
@Database(entities = [User::class, Session::class, AnswerExercises::class, AnswerRefusal::class, Physio::class, ExercisesSheet::class, MessagesEncouragement::class], version = 5)

abstract class AppDatabase : RoomDatabase() {



    abstract fun userDao(): UserDao
    abstract fun AnswerExercisesDao(): AnswerExercisesDao
    abstract fun SessionDao(): SessionDao
    abstract fun AnswerRefusalDao(): AnswerRefusalDao
    abstract fun PhysioDao(): PhysioDao
    abstract fun ExercisesSheetDao(): ExercisesSheetDao
    abstract fun MessagesEncouragementDao(): MessagesEncouragementDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database").addMigrations(MIGRATION_4_5).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}