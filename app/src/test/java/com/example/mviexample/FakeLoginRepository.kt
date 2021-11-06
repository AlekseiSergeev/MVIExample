package com.example.mviexample

import com.example.mviexample.LoginRepository

class FakeLoginRepository: LoginRepository {
    var shouldMockSuccess = true

    override suspend fun login(email: String, password: String): Boolean {
        return shouldMockSuccess
    }
}