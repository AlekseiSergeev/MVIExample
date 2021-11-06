package com.example.mviexample.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mviexample.MainActivity
import com.example.mviexample.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect{
                processViewState(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailInput.doOnTextChanged { text, _, _, _ ->
            viewModel.emailChanged(text?.toString().orEmpty())
        }

        binding.passwordInput.doOnTextChanged { text, _, _, _ ->
            viewModel.passwordChanged(text?.toString().orEmpty())
        }

        binding.signInButton.setOnClickListener {
           viewModel.signInButtonClicked()
        }
    }

    private fun processViewState(viewState: LoginViewState) {
        binding.progressBar.visibility = if (viewState.showProgressBar) {
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.emailInputLayout.error = viewState.emailError
    }

}