package com.gmail.dudarenka.vitali.autocattesttask.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.gmail.dudarenka.vitali.autocattesttask.data.db.dao.CarDao
import com.gmail.dudarenka.vitali.autocattesttask.data.db.entity.CarDB


@Database(entities = [CarDB::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "AutoCatBase"
        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getCarDao(): CarDao

}
