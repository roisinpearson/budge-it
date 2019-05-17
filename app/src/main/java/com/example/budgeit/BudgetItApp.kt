package com.example.budgeit

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class BudgetItApp: Application() {

    lateinit var database: BudgeItDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            BudgeItDatabase::class.java, "database-name"
        ).build()
    }
}