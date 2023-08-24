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
    FakeGame1,
    FakeGame2,
    FakeGame3,
    FakeGame4,
    FakeGame5,
    FakeGame6,
    FakeGame7,
    FakeGame8,
    FakeGame9,
)

object FakeGame1 : Game {
    override val id: Int
        get() = 0
    override val name: String
        get() = "Game 1"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}

object FakeGame2 : Game {
    override val id: Int
        get() = 1
    override val name: String
        get() = "Game 2"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() =  R.drawable.placeholder
}

object FakeGame3 : Game {
    override val id: Int
        get() = 2
    override val name: String
        get() = "Game 3"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() =  R.drawable.placeholder
}

object FakeGame4 : Game {
    override val id: Int
        get() = 3
    override val name: String
        get() = "Game 4"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}

object FakeGame5 : Game {
    override val id: Int
        get() = 4
    override val name: String
        get() = "Game 5"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}

object FakeGame6 : Game {
    override val id: Int
        get() = 5
    override val name: String
        get() = "Game 6"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}

object FakeGame7 : Game {
    override val id: Int
        get() = 6
    override val name: String
        get() = "Game 7"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}

object FakeGame8 : Game {
    override val id: Int
        get() = 7
    override val name: String
        get() = "Game 8"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}

object FakeGame9 : Game {
    override val id: Int
        get() = 8
    override val name: String
        get() = "Game 9"
    override val players: Int
        get() = 4
    override val imageIconUrl: Int
        get() = R.drawable.placeholder
}