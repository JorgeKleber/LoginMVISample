package com.example.loginmvisample.domain.repository

interface LoginRepository {

    fun validateCredentials(username: String, password: String): Boolean

}