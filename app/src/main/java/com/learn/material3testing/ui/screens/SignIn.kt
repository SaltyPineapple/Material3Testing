package com.learn.material3testing.ui.screens

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.learn.material3testing.SignInActivity
import com.learn.material3testing.ui.theme.Material3TestingTheme
import kotlinx.coroutines.launch

@Composable
fun SignInForm(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val user = Firebase.auth.currentUser
    if(user != null){
        user.let {
            val name = it.displayName
            val email = it.email
            val uid = it.uid
            Column {
                Text(text = "Hello, $name", modifier = modifier)
                Text(text = "Email: $email", modifier = modifier)
                Text(text = "uid: $uid", modifier = modifier)
                Button(onClick = { Firebase.auth.signOut() }) {
                    Text(text = "Sign out of Google")
                }
            }
        }
    }
    else {
        Button(
            onClick = {
                val intent = Intent(context, SignInActivity::class.java)
                context.startActivity(intent)
            },
            elevation =  ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Sign in with Google")
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