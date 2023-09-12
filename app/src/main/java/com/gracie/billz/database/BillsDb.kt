package com.gracie.billz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gracie.billz.model.Bill
import com.gracie.billz.model.UpcomingBills

@Database(entities = [Bill :: class, UpcomingBills::class], version = 2)
abstract class BillsDb: RoomDatabase() {
    abstract fun billsDao(): BillsDao
    abstract  fun upcomingBillsDao(): UpcomingBillsDao
    companion object{
        var database: BillsDb? = null
        fun getDatabase(context: Context) : BillsDb{
            if (database == null){
                database = Room.databaseBuilder(context, BillsDb::class.java, "BillzDb")
                    .fallbackToDestructiveMigration().build()
            }
            return database as BillsDb
        }
    }
}