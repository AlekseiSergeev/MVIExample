package com.example.mviexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mviexample.ui.login.LoginFragment
import com.example.mviexample.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .commitNow()
        }
    }

    /**
     * To navigate to the profile screen, we'll run a Fragment transaction to replace the login
     * screen with the profile screen.
     */
    fun navigateToProfile() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ProfileFragment())
            .commitNow()
    }
}