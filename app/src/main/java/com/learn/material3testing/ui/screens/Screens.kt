package com.learn.material3testing.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.learn.material3testing.ui.components.GameCards
import com.learn.material3testing.ui.components.data.games

@Composable
fun Home(){
    GameCards(gameCollection = games)
}

@Composable
fun Search(){
    Text(text = "Hello Search!")
}

@Composable
fun Profile(){
    Text(text = "Hello Profile!")
}