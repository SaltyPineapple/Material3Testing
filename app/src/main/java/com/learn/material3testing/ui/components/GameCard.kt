package com.learn.material3testing.ui.components

import android.content.Intent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.learn.material3testing.GameActivity
import com.learn.material3testing.R
import com.learn.material3testing.ui.components.business.GameService
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.games
import com.learn.material3testing.ui.theme.Material3TestingTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GameCard(currentGame: Game, snackbarHost: SnackbarHostState){
    val context = LocalContext.current
    val haptics = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()
    
    var isDeleting by remember { mutableStateOf(false )}

    val rotate by animateFloatAsState(
        targetValue = if (isDeleting) -1f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(150, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        finishedListener = {
            isDeleting = false
        }, label = "rotate"
    )

    Card(
        modifier = Modifier
            .size(width = 280.dp, height = 80.dp)
            .combinedClickable(
                onClick = {
                    val openGameIntent = Intent(context, GameActivity::class.java)
                    openGameIntent.putExtra("gameId", currentGame.gameId)
                    context.startActivity(openGameIntent)
                },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    isDeleting = true
                    scope.launch {
                        val result = snackbarHost
                            .showSnackbar(
                                message = "Are you sure you want to delete ${currentGame.name}?",
                                actionLabel = "Delete",
                                duration = SnackbarDuration.Indefinite,
                                withDismissAction = true
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                isDeleting = false
                                currentGame.gameId?.let { GameService().deleteGame(it) }
                            }
                            SnackbarResult.Dismissed -> {
                                isDeleting = false
                            }
                        }
                    }
                },
            )
            .graphicsLayer {
                rotationZ = if (isDeleting) rotate else 0f
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Row(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)) {
                Text(
                    text = currentGame.name.toString(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary)
                Text(
                    text = "Players: " + currentGame.players.toString(),
                    color = MaterialTheme.colorScheme.onPrimary)
            }
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)) {
                Text(
                    text = convertToDate(currentGame.dateCreated).toString(),
                    color = MaterialTheme.colorScheme.onPrimary)
            }
            Image(
                painter = painterResource(id = if(currentGame.imageIconUrl != null) currentGame.imageIconUrl!! else R.drawable.placeholder),
                contentDescription = "Placeholder",
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(80.dp),
            )
        }
    }
}

fun convertToDate(date: Timestamp?) : String?{
    val formatter = SimpleDateFormat("MM.dd.yy")
    val dateSeconds = date?.seconds?.times(1000)
    val netDate = dateSeconds?.let { Date(it) }
    return netDate?.let { formatter.format(it) }
}

@Composable
fun GameCards(gameCollection: List<Game>, snackbarHost: SnackbarHostState, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 1.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(bottom = 80.dp, top = 4.dp).fillMaxHeight()
    ){
        items(gameCollection.size) { index -> GameCard(gameCollection[index], snackbarHost) }
    }
}

@Preview
@Composable
fun DarkGameCardPreview(){
    val snackbarHost = remember { SnackbarHostState() }
    Material3TestingTheme(darkTheme = true) {
        GameCard(games[0], snackbarHost)
    }
}

@Preview
@Composable
fun LightGameCardPreview(){
    val snackbarHost = remember { SnackbarHostState() }
    Material3TestingTheme(darkTheme = false) {
        GameCard(games[0], snackbarHost)
    }
}

@Preview(name = "Dark theme Card List", backgroundColor = 0xFF555454)
@Composable
fun DarkGameCardsPreview(){
    val snackbarHost = remember { SnackbarHostState() }
    Material3TestingTheme(darkTheme = true) {
        GameCards(games, snackbarHost)
    }
}

@Preview(name = "Light theme Card List", backgroundColor = 0xFFE4E3E3)
@Composable
fun LightGameCardsPreview(){
    val snackbarHost = remember { SnackbarHostState() }
    Material3TestingTheme(darkTheme = false) {
        GameCards(games, snackbarHost)
    }
}