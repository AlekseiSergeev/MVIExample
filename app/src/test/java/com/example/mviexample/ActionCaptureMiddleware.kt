package com.example.mviexample

import com.example.mviexample.redux.Action
import com.example.mviexample.redux.Middleware
import com.example.mviexample.redux.State
import com.example.mviexample.redux.Store
import com.google.common.truth.Truth.assertThat

class ActionCaptureMiddleware<S: State, A: Action> : Middleware<S, A> {

    private val actionHistory: MutableList<Action> = mutableListOf()

    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        actionHistory.add(action)
    }

    fun assertActionProcessed (expectedAction: A) {
        assertThat(actionHistory).contains(expectedAction)
    }
}