package com.learn.material3testing.ui.components.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learn.material3testing.ui.components.data.accounts.SignUpViewModel
import com.learn.material3testing.ui.theme.Material3TestingTheme
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInForm(
    viewModel: SignUpViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        OutlinedTextField(
            value = email,
            onValueChange = { text -> email = text },
            label = { Text("Email") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { text -> password = text },
            label = { Text("Password") }
        )

        Button(
            onClick = {
                coroutineScope.launch{
                    viewModel.onSignUpClick(email = email, password = password)
                }
            },
            elevation =  ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier.padding(16.dp)
            ) {
            Text("Sign In")
        }
    }
}

@Preview
@Composable
fun LightModeSignInForm(){
    Material3TestingTheme(darkTheme = false) {
        SignInForm()
    }
}

@Preview
@Composable
fun DarkModeSignInForm(){
    Material3TestingTheme(darkTheme = true) {
        SignInForm()
    }
}