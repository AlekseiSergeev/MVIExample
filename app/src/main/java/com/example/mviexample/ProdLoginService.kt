package com.example.mviexample

import kotlinx.coroutines.delay

class ProdLoginService: LoginRepository {
    override suspend fun login(email: String, password: String): Boolean {
        delay(3000)

        return true
    }
}