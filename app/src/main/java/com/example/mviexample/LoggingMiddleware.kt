package com.example.mviexample

import android.util.Log
import com.example.mviexample.redux.Action
import com.example.mviexample.redux.Middleware
import com.example.mviexample.redux.State
import com.example.mviexample.redux.Store

class LoggingMiddleware<S: State, A: Action>: Middleware<S, A> {
    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        Log.i("LoggingMiddleware", "Processing action: $action ; \n Current state: $currentState \n")
    }
}