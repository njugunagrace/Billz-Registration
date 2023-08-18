package com.gracie.billz.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gracie.billz.model.RegisterRequest
import com.gracie.billz.model.RegisterResponse
import com.gracie.billz.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel :ViewModel(){
     val userRepo = UserRepository()
    val regLiveData = MutableLiveData<RegisterResponse>()
     val errLiveData = MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepo.register(registerRequest)
            if (response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}