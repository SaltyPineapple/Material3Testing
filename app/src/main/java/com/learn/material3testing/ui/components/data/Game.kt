package com.learn.material3testing.ui.components.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.google.type.DateTime
import com.learn.material3testing.R
import com.learn.material3testing.ui.components.business.GameService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

data class Game(
    @DocumentId var gameId: String? = null,
    var name: String? = null,
    var players: Int? = null,
    var imageIconUrl: Int? = null,
    var userId: String? = null,
    var playerNames: List<String> = emptyList(),
    @ServerTimestamp var dateCreated: Timestamp? = null
)

class GameViewModel(gameId: String) : ViewModel() {
    private val _uiState = MutableStateFlow(Game())
    val uiState: StateFlow<Game> = _uiState.asStateFlow()
    private val coroutineScope = MainScope()

    init {
        getGameById(gameId)
    }

    private fun getGameById(gameId: String){
        coroutineScope.launch {
            _uiState.value = GameService().getGameById(gameId)!!
        }
    }
}

class GameViewModelFactory(private val gameId: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = GameViewModel(gameId) as T
}
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