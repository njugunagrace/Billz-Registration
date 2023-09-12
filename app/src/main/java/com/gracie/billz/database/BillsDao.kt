package com.gracie.billz.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gracie.billz.model.Bill

interface BillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill: Bill)

    @Query("SELECT * FROM Bills WHERE frequency = :freq")
    fun getRecurringBills(freq: String) : List<Bill>
}