package com.learn.material3testing.ui.components.data.services

import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.Round
import kotlinx.coroutines.flow.Flow

interface IStorageService {
    val games: Flow<List<Game>>
    suspend fun getGame(gameId: String) : Game?
    suspend fun createGame(game: Game)
    suspend fun updateGame(game: Game)
    suspend fun deleteGame(gameId: String)
    suspend fun createRound(gameId: String, round: Round)
    suspend fun getAllRounds(gameId: String) : List<Round>
}