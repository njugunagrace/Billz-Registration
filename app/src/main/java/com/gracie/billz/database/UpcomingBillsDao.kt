package com.gracie.billz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gracie.billz.model.UpcomingBills

@Dao
interface UpcomingBillsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertUpcomingBills(vararg upcomingBill: UpcomingBill)

    @Query("SELECT * FROM UpcomingBill WHERE billId = :billId AND dueDate BETWEEN :startDate AND :endDate")
    fun queryExistingBills(billId : String , startDate : String , endDate : String): List<UpcomingBill>

}
