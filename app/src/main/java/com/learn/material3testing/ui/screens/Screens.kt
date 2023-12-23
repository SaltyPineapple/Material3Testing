package com.learn.material3testing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.learn.material3testing.R
import com.learn.material3testing.ui.components.GameCards
import com.learn.material3testing.ui.components.MaterialScaffold
import com.learn.material3testing.ui.components.business.GameService
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.services.StorageService
import com.learn.material3testing.ui.theme.Material3TestingTheme

@Composable
fun Home(){
    val snackbarHostState = remember { SnackbarHostState() }
    val openGameCreationDialog = remember { mutableStateOf(false) }
    MaterialScaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openGameCreationDialog.value = true
                },
                modifier = Modifier.padding(end = 16.dp, bottom = 72.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
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
        when {
            openGameCreationDialog.value -> {
                GameCreationDialog(
                    onDismissRequest = { openGameCreationDialog.value = false },
                    onConfirmation = { openGameCreationDialog.value = false },
                )
            }
        }
    }
}

@Composable
fun GameCreationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    var gameName by remember { mutableStateOf("") }
    var numberPlayers by remember { mutableStateOf("") }
    val isAddingPlayers = remember { mutableStateOf(false) }
    val isPlayersValidNum = remember { mutableStateOf(false) }
    Dialog(onDismissRequest = onDismissRequest) {
        Card (
            modifier = Modifier
                .height(400.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                   .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if(!isAddingPlayers.value){

                    Text(text = "Create a New Game", fontSize = 20.sp)
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(4.dp)
                    )
                    OutlinedTextField(
                        value = gameName,
                        onValueChange = { gameName = it },
                        label = { Text("Game Name") },
                        placeholder = { Text(text = "New Game with the Smith's") }
                    )
                   OutlinedTextField(
                        value = numberPlayers,
                        onValueChange = {
                            numberPlayers = it
                            val playersAsNum = numberPlayers.toIntOrNull()
                            isPlayersValidNum.value = playersAsNum != null
                        },
                        label = { Text("Number of Players") },
                        placeholder = { Text(text = "6") }
                   )
                   Row {
                        Button(
                           modifier = Modifier.padding(8.dp),
                           shape = RoundedCornerShape(12.dp),
                           colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                           onClick = { onDismissRequest() }
                       ) {
                           Text(
                               color = MaterialTheme.colorScheme.onError,
                               text = "Cancel"
                           )
                       }
                       Button(
                           modifier = Modifier.padding(8.dp),
                           shape = RoundedCornerShape(12.dp),
                           enabled = isPlayersValidNum.value && gameName != "",
                           colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                           onClick = {
                               isAddingPlayers.value = true
                           }
                       ) {
                           Text(
                               color = MaterialTheme.colorScheme.onTertiary,
                               text = "Next"
                           )
                       }
                   }
                }
                else {
                    val playerList = remember { mutableListOf<String>() }
                    for (i in 0..<numberPlayers.toInt()){
                        playerList.add("")
                    }
                    Text(text = "Add Players", fontSize = 20.sp)
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(4.dp)
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier.height(275.dp)
                    ){
                        items(playerList.size) { index ->
                            var name by remember { mutableStateOf("") }
                            OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                                playerList[index] = it
                            },
                            label = { Text("Player ${index+1}") },
                            placeholder = { Text(text = "Name") }
                        )}
                    }
                    Row {
                        Button(
                            modifier = Modifier.padding(8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            onClick = { onDismissRequest() }
                        ) {
                            Text(
                                color = MaterialTheme.colorScheme.onError,
                                text = "Cancel"
                            )
                        }
                        Button(
                            modifier = Modifier.padding(8.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                            onClick = {
                                GameService().createGame(
                                    game = Game(
                                        imageIconUrl = R.drawable.placeholder,
                                        name = gameName,
                                        players = numberPlayers.toInt(),
                                        playerNames = playerList,
                                        userId = Firebase.auth.currentUser?.uid,
                                        dateCreated = Timestamp.now()
                                    )
                                )
                                onConfirmation()
                            }
                        ) {
                            Text(
                                color = MaterialTheme.colorScheme.onTertiary,
                                text = "Create"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Search(){
    MaterialScaffold { paddingValues ->
        Text(text = "Hello Search!", modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun Profile(){
    MaterialScaffold { paddingValues ->
        SignInForm(modifier = Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
fun GameCreationDialogPreviewDark(){
    Material3TestingTheme(darkTheme = true) {
        GameCreationDialog(
            onDismissRequest = {},
            onConfirmation = {},
        )
    }
}

@Preview
@Composable
fun GameCreationDialogPreviewLight(){
    Material3TestingTheme(darkTheme = false) {
        GameCreationDialog(
            onDismissRequest = {},
            onConfirmation = {},
        )
    }
}