package com.learn.material3testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.learn.material3testing.ui.components.business.GameService
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.theme.Material3TestingTheme

class GameCreationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3TestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // GameCreationForm()
                }
            }
        }
    }
}

//@Composable
//fun GameCreationForm(){
//    var gameName by remember { mutableStateOf("") }
//    var numberPlayers by remember { mutableStateOf(0) }
//    var gameName by remember { mutableStateOf("") }
//    Column {
//        OutlinedTextField(value = text, onValueChange = )
//        Button(
//            onClick = {
//                GameService().createGame(
//                    game = Game(
//                        imageIconUrl = R.drawable.placeholder,
//                        name = "New Game",
//                        players = 5,
//                        userId = Firebase.auth.currentUser?.uid,
//                        dateCreated = Timestamp.now()
//                    )
//                )
//            }
//        ) {
//            Text(text = "Create Game")
//        }
//    }
//}

