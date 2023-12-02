package com.learn.material3testing

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.material3testing.ui.components.business.GameService
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.GameViewModel
import com.learn.material3testing.ui.components.data.GameViewModelFactory
import com.learn.material3testing.ui.components.data.Round
import com.learn.material3testing.ui.theme.Material3TestingTheme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameId = intent.getStringExtra("gameId")

        setContent {
            val openNewRoundDialog = remember { mutableStateOf(false) }
            Material3TestingTheme {
                Scaffold(
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            onClick = {
                                openNewRoundDialog.value = true
                                // run new round method
                                Toast.makeText(this, "Add Round!", Toast.LENGTH_SHORT).show()
                            },
                            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                            text = { Text(text = "Add Round") },
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.padding(end = 16.dp, bottom = 72.dp),
                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                ) { paddingValues ->
                    val gameViewModel: GameViewModel = viewModel(factory = gameId?.let {
                        GameViewModelFactory(
                            it
                        )
                    })
                    val gameUiState = gameViewModel.uiState.collectAsState()
                    val game = gameUiState.value
                    GameScreen(game = game, modifier = Modifier.padding(paddingValues))
                    when {
                        openNewRoundDialog.value -> {
                            NewRoundDialog(
                                onDismissRequest = { openNewRoundDialog.value = false },
                                onConfirmation = { openNewRoundDialog.value = false },
                                game = game
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GameScreen(game: Game, modifier: Modifier){
    Material3TestingTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = game.name.toString(), modifier = Modifier.padding(16.dp), fontSize = 20.sp)
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(4.dp)
                )
                LazyHorizontalGrid(rows = GridCells.Fixed(1)){
                    game.players?.let {
                        items(it){ playerIndex ->
                            Text(text = game.playerNames[playerIndex], modifier = Modifier.padding(4.dp))
                            HorizontalDivider(
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        items(it){ roundIndex ->
                            Text(text = game.rounds[roundIndex].scores[it-1].toString(), modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewRoundDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    game: Game,
){
    val localContext = LocalContext.current
    val playerList = game.playerNames
    val playerScores = remember { mutableListOf<String>() }
    for (player in playerList){
        playerScores.add("")
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card (
            modifier = Modifier
                .height(400.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(text = "Add a New Round", fontSize = 20.sp)
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
                        var score by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = score,
                            onValueChange = {
                                score = it
                                playerScores[index] = it
                            },
                            label = { Text(playerList[index]) },
                            placeholder = { Text(playerList[index]) }
                        )
                    }
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
                            val playerScoresInt = mutableListOf<Int>()
                            for (playerScore in playerScores) {
                                playerScoresInt.add(playerScore.toInt())
                            }
                            game.gameId?.let { GameService().createRound(it, Round(scores = playerScoresInt)) }
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
