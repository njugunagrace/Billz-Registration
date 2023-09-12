package com.gracie.billz.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.gracie.billz.viewModel.BillsViewModel
import dev.owuor91.billz.R
import dev.owuor91.billz.R.string
import dev.owuor91.billz.databinding.ActivityAddBillBinding
import dev.owuor91.billz.model.Bill
import dev.owuor91.billz.utils.Constants
import dev.owuor91.billz.viewmodel.BillsViewModel
import java.util.Calendar
import java.util.UUID
​
class AddBillActivity : AppCompatActivity() {
    lateinit var binding: AddBillActivity
    val billsViewModel: BillsViewModel by viewModels()
    var selectedDate = 0
    var selectedMonth = 0
    ​
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddBillActivity.inflate(layoutInflater)
        setContentView(binding.root)
    }
    ​
    override fun onResume() {
        super.onResume()
        setupFreqSpinner()
        binding.btnSaveBill.setOnClickListener {
            validateBill()
        }
    }
    ​
    fun setupFreqSpinner() {
        val frequencies = arrayOf(Constants.WEEKLY, Constants.MONTHLY, Constants.ANNUAL)
        val freqAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, frequencies)
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter = freqAdapter
        ​
        binding.spFrequency.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (binding.spFrequency.selectedItem.toString()) {
                    Constants.WEEKLY -> {
                        showSpinner()
                        setUpDueDateSpinner(Array(7) { it + 1 })
                    }
                        ​
                        Constants.MONTHLY -> {
                    showSpinner()
                    setUpDueDateSpinner(Array(31) { it + 1 })
                }
                        ​
                        Constants.ANNUAL -> {
                    showDatePicker()
                    setupDpDueDate()
                }
                }
            }
            ​
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    ​
    fun showSpinner() {
        binding.dpDueDate.hide()
        binding.spDueDate.show()
    }
    ​
    fun showDatePicker() {
        binding.dpDueDate.show()
        binding.spDueDate.hide()
    }
    ​
    fun setUpDueDateSpinner(dates: Array<Int>) {
        val dueDateAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDueDate.adapter = dueDateAdapter
    }
    ​
    fun setupDpDueDate() {
        val cal = Calendar.getInstance()
        binding.dpDueDate.init(
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ) { _, _, month, date ->
            selectedDate = date
            selectedMonth = month+1
        }
    }
    ​
    fun validateBill(){
        val name = binding.etName.text.toString()
        val amount = binding.etAmount.text.toString()
        val frequency = binding.spFrequency.selectedItem.toString()
        val dueDate = if(frequency==Constants.ANNUAL){
            "$selectedDate/$selectedMonth"
        } else{
            binding.spDueDate.selectedItem.toString()
        }
        ​
        var error = false
        if(name.isBlank()){
            error = true
            binding.etName.error = getString(string.name_req)
        }
        ​
        if(amount.isBlank()){
            error = true
            binding.etName.error = getString(string.amount_req)
        }
        ​
        if(!error){
            val prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
            val userId = prefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)
            val newBill = Bill(
                name = name,
                amount = amount.toDouble(),
                frequency = frequency,
                dueDate = dueDate,
                billId = UUID.randomUUID().toString(),
                userId = userId.toString()
            )
            billsViewModel.saveBill(newBill)
            clearForm()
        }
        ​
    }
    ​
    fun clearForm(){
        binding.etName.setText(Constants.EMPTY_STRING)
        binding.etAmount.setText(Constants.EMPTY_STRING)
        binding.spFrequency.setSelection(0)
        showSpinner()
        binding.spDueDate.setSelection(0)
    }
    ​
}
​
fun View.show(){
    this.visibility = View.VISIBLE
}
​
fun View.hide(){
    this.visibility = View.GONE
}