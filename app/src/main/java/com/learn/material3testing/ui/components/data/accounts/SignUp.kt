package com.learn.material3testing.ui.components.data.accounts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(private val accountService: AccountService) :
    ViewModel() {
    suspend fun onSignUpClick(email: String, password: String) {
        accountService.linkAccount(email, password)
    }
}
