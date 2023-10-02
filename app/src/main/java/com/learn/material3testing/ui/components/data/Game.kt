package com.learn.material3testing.ui.components.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.google.type.DateTime
import com.learn.material3testing.R
import java.time.LocalDate
import java.time.LocalDateTime

data class Game(
    @DocumentId var gameId: String? = null,
    var name: String? = null,
    var players: Int? = null,
    var imageIconUrl: Int? = null,
    var userId: String? = null,
    @ServerTimestamp var dateCreated: Timestamp? = null
)

// FAKE DATA BELOW

val games = listOf(
    Game(
        gameId = "",
        name = "Game 1",
        players = 4,
        imageIconUrl = R.drawable.placeholder,
        userId = "",
        dateCreated = Timestamp.now()
    ),
    Game(
        gameId = "",
        name = "Game 2",
        players = 4,
        imageIconUrl = R.drawable.placeholder,
        userId = "",
        dateCreated = Timestamp.now()
    ),
    Game(
        gameId = "",
        name = "Game 3",
        players = 4,
        imageIconUrl = R.drawable.placeholder,
        userId = "",
        dateCreated = Timestamp.now()
    ),
    Game(
        gameId = "",
        name = "Game 4",
        players = 4,
        imageIconUrl = R.drawable.placeholder,
        userId = "",
        dateCreated = Timestamp.now()
    ),
    Game(
        gameId = "",
        name = "Game 5",
        players = 4,
        imageIconUrl = R.drawable.placeholder,
        userId = "",
        dateCreated = Timestamp.now()
    ),
)