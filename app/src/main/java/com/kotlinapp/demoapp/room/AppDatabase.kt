package com.kotlinapp.demoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.kotlinapp.demoapp.dataClass.ImageApi
import com.kotlinapp.demoapp.dataClass.ImageApiItem

@Database(entities = [ImageApiItem::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): Dao


    companion object {
        private var DB_INSTANCE: AppDatabase? = null

        fun getAppDBInstance(context: Context): AppDatabase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "APP_DB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }


    }
}

