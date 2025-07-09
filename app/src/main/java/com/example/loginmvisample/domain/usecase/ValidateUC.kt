package com.example.loginmvisample.domain.usecase

import com.example.loginmvisample.domain.repository.LoginRepository

class ValidateUC(
    private val repo : LoginRepository
) {

    operator fun invoke(username: String, password: String): Boolean {
        return repo.validateCredentials(username, password)
    }
}