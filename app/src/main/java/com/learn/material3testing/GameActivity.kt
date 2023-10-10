package com.learn.material3testing

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.material3testing.ui.components.data.GameViewModel
import com.learn.material3testing.ui.components.data.GameViewModelFactory
import com.learn.material3testing.ui.theme.Material3TestingTheme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameId = intent.getStringExtra("gameId")

        setContent {
            Material3TestingTheme {
                Scaffold(
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            onClick = {
                                Toast.makeText(this, "New Round!", Toast.LENGTH_SHORT).show()
                            },
                            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                            text = { Text(text = "New Round") },
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.padding(end = 16.dp, bottom = 72.dp),
                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                ) { paddingValues ->
                    GameScreen(gameId = gameId.toString(), modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun GameScreen(gameId: String, modifier: Modifier){
    val gameViewModel: GameViewModel = viewModel(factory = GameViewModelFactory(gameId))
    val gameUiState = gameViewModel.uiState.collectAsState()
    val game = gameUiState.value
    Material3TestingTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = game.name.toString(), modifier = Modifier.padding(16.dp), fontSize = 20.sp)
                HorizontalDivider()
                Row{
                    for (player in game.playerNames){
                        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = player)
                        }
                    }
                }
            }
        }
    }
}
