package com.example.mviexample.redux

import com.example.mviexample.ActionCaptureMiddleware
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test


class StoreTest {
    @Test
    fun dispatchSendsActionToReducerAndMiddlewares() {
        //Given
        val inputState = TestState
        val inputAction = TestAction
        val reducer = TestReducer()
        val middleware = ActionCaptureMiddleware<State, Action>()

        val testStore = Store(
            inputState,
            reducer,
            listOf(middleware)
        )

        //When
        runBlocking {
            testStore.dispatch(inputAction)
        }

        //Then
        middleware.assertActionProcessed(inputAction)
        reducer.assertActionProcessed(inputAction)
    }

}

object TestState : State

object TestAction : Action

class TestReducer : Reducer<State, Action> {
    private val actionHistory: MutableList<Action> = mutableListOf()

    override fun reduce(currentState: State, action: Action): State {
        actionHistory.add(action)
        return currentState
    }

    fun assertActionProcessed (expectedAction: Action) {
        assertThat(actionHistory).contains(expectedAction)
    }

}