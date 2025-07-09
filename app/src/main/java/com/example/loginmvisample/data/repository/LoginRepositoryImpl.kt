package com.example.loginmvisample.data.repository

import com.example.loginmvisample.domain.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override fun validateCredentials(
        username: String,
        password: String
    ): Boolean {
        if (username == "admin" && password == "123123") {
            return true
        }
        else{
            return false
        }
    }
}