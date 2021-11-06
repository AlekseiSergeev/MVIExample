package com.example.mviexample.ui.login

import com.example.mviexample.redux.State

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val showProgressBar: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
) : State
