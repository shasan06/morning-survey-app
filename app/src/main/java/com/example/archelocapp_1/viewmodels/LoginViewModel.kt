package com.example.archelocapp_1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archelocapp_1.Models.GeographicalData
import com.example.archelocapp_1.Models.Login
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.Models.Nest
import com.example.archelocapp_1.repository.Repository
import com.example.archelocapp_1.source.api.ServiceBuilder
import com.example.archelocapp_1.utils.AuthListener
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val TAG = "LoginViewModel"
    private var repository = Repository()
    private var isLogin = MutableLiveData<Boolean>(false)


    fun get() = isLogin

    fun login(userName: String, password: String, listener: AuthListener<Boolean>) {
        viewModelScope.launch {
            ServiceBuilder.KEY = ""
            val data = repository.login(userName, password)
            if (data.isSuccessful) {
                listener.onSuccess(true)
                val login = data.body() as Login
                ServiceBuilder.KEY = "Token ${login.key}"
            } else {
                listener.onSuccess(false)
            }

        }
    }



}

