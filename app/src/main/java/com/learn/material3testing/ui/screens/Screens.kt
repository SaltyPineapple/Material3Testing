package com.learn.material3testing.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.learn.material3testing.ui.components.GameCards
import com.learn.material3testing.ui.components.MaterialNavBar
import com.learn.material3testing.ui.components.MaterialScaffold
import com.learn.material3testing.ui.components.data.games

@Composable
fun Home(){
    MaterialScaffold(
        floatingActionButton = {
            val context = LocalContext.current
            FloatingActionButton(
                onClick = { Toast.makeText(context, "Create a new game", Toast.LENGTH_SHORT).show() },
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

        ) { paddingValues ->
        GameCards(gameCollection = games, modifier = Modifier.padding(paddingValues))
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
        Text(text = "Hello Profile!", modifier = Modifier.padding(paddingValues))
    }
}