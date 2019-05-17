package com.example.budgeit

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budgeit.income.Income
import com.example.budgeit.income.IncomeDao

@Database(entities = arrayOf(Income::class), version = 1)
abstract class BudgeItDatabase : RoomDatabase() {
    abstract fun incomeDao(): IncomeDao
}