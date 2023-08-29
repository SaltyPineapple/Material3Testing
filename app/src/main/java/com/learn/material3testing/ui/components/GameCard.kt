package com.learn.material3testing.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learn.material3testing.R
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.GameGrabber
import com.learn.material3testing.ui.components.data.games
import com.learn.material3testing.ui.theme.Material3TestingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(gameId: Int){
    val currentGame = GameGrabber().getGame(gameId)
    val context = LocalContext.current
    Card(
        onClick = { Toast.makeText(context, currentGame.name, Toast.LENGTH_SHORT).show() },
        modifier = Modifier.size(width = 280.dp, height = 80.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Row(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)) {
                Text(
                    text = currentGame.name,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary)
                Text(
                    text = "Players: " + currentGame.players.toString(),
                    color = MaterialTheme.colorScheme.onPrimary)
            }
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "Placeholder",
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(80.dp),
            )
        }
    }
}

@Composable
fun GameCards(gameCollection: List<Game>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 1.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(bottom = 84.dp, top = 4.dp)
    ){
        items(gameCollection.size) { index -> GameCard(gameCollection[index].id)}
    }
}

@Preview
@Composable
fun DarkGameCardPreview(){
    Material3TestingTheme(darkTheme = true) {
        GameCard(1)
    }
}

@Preview
@Composable
fun LightGameCardPreview(){
    Material3TestingTheme(darkTheme = false) {
        GameCard(1)
    }
}

@Preview(name = "Dark theme Card List", backgroundColor = 0xFF555454)
@Composable
fun DarkGameCardsPreview(){
    Material3TestingTheme(darkTheme = true) {
        GameCards(games)
    }
}

@Preview(name = "Light theme Card List", backgroundColor = 0xFFE4E3E3)
@Composable
fun LightGameCardsPreview(){
    Material3TestingTheme(darkTheme = false) {
        GameCards(games)
    }
}