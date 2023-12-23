package com.learn.material3testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.material3testing.ui.components.MaterialScaffold
import com.learn.material3testing.ui.components.RoundCard
import com.learn.material3testing.ui.components.business.GameService
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.GameViewModel
import com.learn.material3testing.ui.components.data.GameViewModelFactory
import com.learn.material3testing.ui.components.data.Round
import com.learn.material3testing.ui.theme.Material3TestingTheme

class GameActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameId = intent.getStringExtra("gameId")
        val gameTitle = intent.getStringExtra("gameTitle");

        setContent {
            val openNewRoundDialog = remember { mutableStateOf(false) }
            Material3TestingTheme {
                MaterialScaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                gameTitle?.let { Text(it) }
                            },
                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu Dropdown"
                                    )
                                }
                            },
                            navigationIcon = {
                                IconButton(onClick = { this.finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                        )
                    },
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            onClick = {
                                openNewRoundDialog.value = true
                            },
                            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                            text = { Text(text = "Add Round") },
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(end = 16.dp, bottom = 72.dp),
                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                ) {
                    val gameViewModel: GameViewModel = viewModel(factory = gameId?.let {
                        GameViewModelFactory(
                            it
                        )
                    })
                    val gameUiState = gameViewModel.uiState.collectAsState()
                    val game = gameUiState.value
                    Surface( modifier = Modifier.padding(top = it.calculateTopPadding())) {
                        GameScreen(game = game)
                        when {
                            openNewRoundDialog.value -> {
                                NewRoundDialog(
                                    onDismissRequest = { openNewRoundDialog.value = false },
                                    onConfirmation = { openNewRoundDialog.value = false },
                                    gameViewModel = gameViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(game: Game){
    Material3TestingTheme {
        if (game.gameId == null){
           Surface(modifier = Modifier
               .fillMaxSize(),
               color = MaterialTheme.colorScheme.background
           ) {
               Image(painter = painterResource(R.drawable.app_logo), contentDescription = "Loading")
           }
        }
        else {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LazyColumn(){
                    stickyHeader{
                        RoundCard(round = game.playerNames, isOdd = true, isHeader = true)
                    }
                    items(game.rounds.size){
                        RoundCard(round = game.rounds[it].scores, isOdd = it % 2 == 0)
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
    gameViewModel: GameViewModel,
){
    val gameUiState = gameViewModel.uiState.collectAsState()
    val game = gameUiState.value

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
                                playerScore.toIntOrNull()?.let { playerScoresInt.add(it) }
                            }
                            game.gameId?.let {
                                GameService().createRound(it, Round(scores = playerScoresInt))
                                gameViewModel.createNewRound(round = Round(scores = playerScoresInt))
                            }
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
