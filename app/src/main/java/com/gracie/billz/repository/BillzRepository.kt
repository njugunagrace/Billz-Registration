package com.gracie.billz.repository

import com.gracie.billz.BillzApp
import com.gracie.billz.database.BillsDb
import com.gracie.billz.model.Bill
import com.gracie.billz.model.UpcomingBills
import com.gracie.billz.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.UUID

class BillzRepository {
    val db = BillsDb.getDatabase(BillzApp.appContext)
    val billsDao = db.billsDao()
    val upcomingBillsDao = db.upcomingBillsDao()

    suspend fun saveBill(bill : Bill){
        withContext(Dispatchers.IO){
            billsDao.insertBill(bill)
        }
    }

    suspend fun insertUpcomingBill(upcomingBill: List<UpcomingBill>){
        withContext(Dispatchers.IO){
            upcomingBillsDao.insertUpcomingBill(upcomingBill)
        }
    }

    suspend fun createRecurringMonthlyBills(){
        withContext(Dispatchers.IO){
            val monthlyBills = billsDao.getRecurringBills(Constants.MONTHLY)
            monthlyBills.forEach { bill ->
            val cal = Calendar.getInstance()
            val month = cal.get(Calendar.MONTH)  +1
            val year = cal.get(Calendar.YEAR)
            val startDate = "1/$month/$year"
                val endDate = "31/$month/$year"
                val existing = upcomingBillsDao.queryExistingBills(bill.billId, startDate, endDate)
                if (existing.isEmpty()){
                    val newUpComingBill = UpcomingBill(
                        upComingBillId = UUID.randomUUID().toString(),
                        billId = bill.billId,
                        name = bill.name,
                        amount = bill.name,
                        frequency = bill.frequency,
                        dueDate = "${bill.dueDate}/$month/$year",
                        userId =bill.userId,
                        paid = false
                    )
                    insertUpcomingBill(newUpComingBill)
                }
            }
        }
    }
}