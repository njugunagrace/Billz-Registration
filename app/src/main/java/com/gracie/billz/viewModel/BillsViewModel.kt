package com.gracie.billz.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gracie.billz.model.Bill
import com.gracie.billz.repository.BillzRepository
import kotlinx.coroutines.launch

class BillsViewModel : ViewModel(){
    val billsRepo =BillzRepository()

    fun saveBill(bill : Bill){
        viewModelScope.launch {
            billsRepo.saveBill(bill)
        }
    }
}