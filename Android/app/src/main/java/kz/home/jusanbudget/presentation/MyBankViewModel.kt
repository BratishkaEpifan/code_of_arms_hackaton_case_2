package kz.home.jusanbudget.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.home.jusanbudget.data.AuthenticationRequest
import kz.home.jusanbudget.data.login

import kz.home.jusanbudget.data.password
import kz.home.jusanbudget.domain.Repository

class MyBankViewModel(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun authorization() {
        viewModelScope.launch(ioDispatcher) {
            repository.authUser(AuthenticationRequest(login, password))
        }
    }

    fun register() {
        viewModelScope.launch(ioDispatcher) {
            repository.registerUser(AuthenticationRequest(login, password))
        }
    }
}