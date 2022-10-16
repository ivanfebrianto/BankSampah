package com.example.banksampah.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banksampah.database.dao.DatabaseDao
import com.example.banksampah.model.ModelDatabase


@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}