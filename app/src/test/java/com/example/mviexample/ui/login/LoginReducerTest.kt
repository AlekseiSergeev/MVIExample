package com.example.mviexample.ui.login

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginReducerTest {

    @Test
    fun emailChangedUpdatesEmail() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.EmailChanged("test@gmail.com")

        val expectedState = inputState.copy(
            email = "test@gmail.com"
        )

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun loginStartedShowsProgressBar() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.LoginStarted

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assertThat(newState.showProgressBar).isTrue()
    }

    @Test
    fun unsupportedActionReturnsOriginalState() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.SignInButtonClicked

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assertThat(newState).isEqualTo(inputState)
    }
}