package com.learn.material3testing.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import com.learn.material3testing.GameCreationActivity
import com.learn.material3testing.R
import com.learn.material3testing.SignInActivity
import com.learn.material3testing.ui.components.GameCards
import com.learn.material3testing.ui.components.MaterialScaffold
import com.learn.material3testing.ui.components.business.GameService
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.services.StorageService

@Composable
fun Home(){
    val snackbarHostState = remember { SnackbarHostState() }
    MaterialScaffold(
        floatingActionButton = {
            val context = LocalContext.current
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Creating a new game", Toast.LENGTH_SHORT).show()
                    GameCreationDialog()
//                    val intent = Intent(context, GameCreationActivity::class.java)
//                    context.startActivity(intent)
                },
                modifier = Modifier.padding(end = 16.dp, bottom = 72.dp),
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Start a new game",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        ) { paddingValues ->
        val gamesFlow = StorageService().games
        val games = gamesFlow.collectAsState(emptyList())
        GameCards(gameCollection = games.value, modifier = Modifier.padding(paddingValues), snackbarHost = snackbarHostState)
    }

}

@Composable
fun GameCreationDialog() {
    val openDialog = remember { mutableStateOf(false) }
    when {
        openDialog.value -> {
            CreateGameAlertDialog(
                onDismissRequest = { openDialog.value = false },
                onConfirmation = { openDialog.value = false }
            )
        }
    }
}

@Composable
fun CreateGameAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    var gameName by remember { mutableStateOf("") }
    var numberPlayers by remember { mutableStateOf(0) }
   Dialog(onDismissRequest = onDismissRequest) {
       Card (
           modifier = Modifier
               .fillMaxWidth()
               .height(375.dp)
               .padding(16.dp),
           shape = RoundedCornerShape(16.dp),
       ) {
           Column {
               OutlinedTextField(
                   value = gameName,
                   onValueChange = { gameName = it },
                   label = { Text("Game Name") }
               )
               OutlinedTextField(
                   value = numberPlayers.toString(),
                   onValueChange = { numberPlayers = it.toInt() },
                   label = { Text("Number of Players") }
               )
               Row {
                   TextButton(onClick = { onDismissRequest() }) {
                       Text(text = "Cancel")
                   }
                   TextButton(onClick = {
                       GameService().createGame(
                           game = Game(
                               imageIconUrl = R.drawable.placeholder,
                               name = gameName,
                               players = numberPlayers,
                               userId = Firebase.auth.currentUser?.uid,
                               dateCreated = Timestamp.now()
                           )
                       )
                       onConfirmation()
                   }) {
                       Text(text = "Create Game")
                   }
               }
           }
       }
   }
}

@Composable
fun Search(){
    MaterialScaffold(
        ) { paddingValues ->
        Text(text = "Hello Search!", modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun Profile(){
    MaterialScaffold(
        ) { paddingValues ->
        SignInForm(modifier = Modifier.padding(paddingValues))
    }
}