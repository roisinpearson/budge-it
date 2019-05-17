package com.example.budgeit.income

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

const val PRIMARY_INCOME_ID = "012345"

@Entity
data class Income(
    @PrimaryKey val incomeId: String,
    @ColumnInfo(name = "income") val income: Double
)

@Dao
interface IncomeDao {
    @Query("SELECT * FROM income WHERE incomeId = :id LIMIT 1")
    fun getIncome(id: String): Single<Income>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putIncome(income: Income): Completable
}

