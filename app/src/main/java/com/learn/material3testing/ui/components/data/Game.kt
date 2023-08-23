package com.learn.material3testing.ui.components.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.learn.material3testing.R

interface Game {
    val id: Int
    val name: String
    val players: Int
    val imageIconUrl: Int
}

// FAKE DATA BELOW

val games = listOf(
    FakeGame1,FakeGame2,FakeGame3
)

object FakeGame1 : Game {
    override val id: Int
        get() = 0
    override val name: String
        get() = "Game1"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}

object FakeGame2 : Game {
    override val id: Int
        get() = 1
    override val name: String
        get() = "Game2"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() =  R.drawable.placeholder
}

object FakeGame3 : Game {
    override val id: Int
        get() = 2
    override val name: String
        get() = "Game3"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() =  R.drawable.placeholder
}