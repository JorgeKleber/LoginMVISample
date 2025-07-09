package com.example.loginmvisample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvisample.data.repository.LoginRepositoryImpl
import com.example.loginmvisample.domain.usecase.ValidateUC
import com.example.loginmvisample.presentation.intent.LoginIntent
import com.example.loginmvisample.presentation.state.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepositoryImpl()
    private val useCase = ValidateUC(repository)

    val intentChannel = Channel<LoginIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    private fun processIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is LoginIntent.Idle -> {}
                    is LoginIntent.SubmitLogin ->
                        submitLogin(intent.username, intent.password)
                }
            }
        }
    }

    fun ValidateUser(intent: LoginIntent.SubmitLogin) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    fun submitLogin(username: String, password: String) {
        val isCorrect = useCase(username, password)

        if (isCorrect) {
            _state.value = LoginState.Approved
        } else {
            _state.value = LoginState.Failed
        }
    }

    init {
        processIntent()
    }


}