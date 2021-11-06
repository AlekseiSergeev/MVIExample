package com.example.mviexample

import com.example.mviexample.redux.Store
import com.example.mviexample.ui.login.LoginAction
import com.example.mviexample.ui.login.LoginReducer
import com.example.mviexample.ui.login.LoginViewState
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginNetworkingMiddlewareTest {

    @Test
    fun submitInvalidEmail() {
        //Givens
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = true

        val inputState = LoginViewState(email = "")
        val inputAction = LoginAction.SignInButtonClicked

        val middlewareUnderTest = LoginNetworkingMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginViewState, LoginAction>()

        val loginStore = Store(
            inputState,
            LoginReducer(),
            listOf(actionCaptureMiddleware,
            middlewareUnderTest),
        )

        //When
        runBlocking {
            middlewareUnderTest.process(inputAction, inputState, loginStore)
        }

        //Then
        actionCaptureMiddleware.assertActionProcessed(LoginAction.InvalidEmailSubmitted)
    }

    @Test
    fun validLogin() {
        //Givens
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = true

        val inputState = LoginViewState(
            email = "test@gmail.com",
            password = "12345")
        val inputAction = LoginAction.SignInButtonClicked

        val middlewareUnderTest = LoginNetworkingMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginViewState, LoginAction>()

        val loginStore = Store(
            inputState,
            LoginReducer(),
            listOf(actionCaptureMiddleware,
                middlewareUnderTest),
        )

        //When
        runBlocking {
            middlewareUnderTest.process(inputAction, inputState, loginStore)
        }

        //Then
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginStarted)
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginCompleted)
    }

    @Test
    fun loginFailure() {
        //Givens
        val fakeLoginRepository = FakeLoginRepository()
        fakeLoginRepository.shouldMockSuccess = false

        val inputState = LoginViewState(
            email = "test@gmail.com",
            password = "12345")
        val inputAction = LoginAction.SignInButtonClicked

        val middlewareUnderTest = LoginNetworkingMiddleware(fakeLoginRepository)
        val actionCaptureMiddleware = ActionCaptureMiddleware<LoginViewState, LoginAction>()

        val loginStore = Store(
            inputState,
            LoginReducer(),
            listOf(actionCaptureMiddleware,
                middlewareUnderTest),
        )

        //When
        runBlocking {
            middlewareUnderTest.process(inputAction, inputState, loginStore)
        }

        //Then
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginStarted)
        actionCaptureMiddleware.assertActionProcessed(LoginAction.LoginFailed(null))
    }
}