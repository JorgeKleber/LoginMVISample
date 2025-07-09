package com.example.loginmvisample.presentation.intent

sealed class LoginIntent {
    object Idle : LoginIntent()
    data class SubmitLogin(val username: String, val password: String) : LoginIntent()
}