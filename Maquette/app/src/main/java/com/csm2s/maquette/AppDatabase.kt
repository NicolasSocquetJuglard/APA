package com.csm2s.maquette

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS reponses (" +
                "reponseid INTEGER PRIMARY KEY NOT NULL," +
                "nb_exercices INTEGER NOT NULL," +
                "difficulte INTEGER NOT NULL," +
                "douleur INTEGER NOT NULL)")
    }
}
@Database(entities = [User::class, ReponsesQuestionnairePostAPA::class], version = 3)

abstract class AppDatabase : RoomDatabase() {



    abstract fun userDao(): UserDao
    abstract fun ReponsesQuestionnairePostAPADao(): ReponsesQuestionnairePostAPADao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database").addMigrations(MIGRATION_2_3).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}