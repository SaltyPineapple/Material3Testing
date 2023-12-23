package com.learn.material3testing.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learn.material3testing.ui.components.data.games
import com.learn.material3testing.ui.theme.Material3TestingTheme

@Composable
fun <T> RoundCard(round: List<T>, isOdd: Boolean, isHeader: Boolean = false){

    val backgroundColor = if (isHeader) MaterialTheme.colorScheme.secondaryContainer else (if (isOdd) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer)
    val textColor = if (isHeader) MaterialTheme.colorScheme.onSecondaryContainer else (if (isOdd) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer)

    Surface(modifier = Modifier.height(40.dp).fillMaxWidth()) {
        LazyHorizontalGrid(rows = GridCells.Adaptive(40.dp)){
            items(round.size) {
                Box(modifier = Modifier
                    .background(color =  backgroundColor)
                    .aspectRatio(2f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = round[it].toString(),
                        color = textColor,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RoundCardPreview(){
    Material3TestingTheme(darkTheme = false) {
        Column {
            RoundCard(round = listOf("Mark", "Mom", "Ally", "Bri"), isOdd = true, isHeader = true)
            RoundCard(round = listOf(1,2,3,4), isOdd = true)
            RoundCard(round = listOf(1,2,3,4), isOdd = false)
        }
    }
}