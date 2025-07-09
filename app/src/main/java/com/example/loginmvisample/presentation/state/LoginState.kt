package com.example.loginmvisample.presentation.state

//representa os estados da página.
sealed class LoginState {
    object Idle : LoginState() // Login state
    object Approved : LoginState() // Login state
    object Failed : LoginState() // Login state
}