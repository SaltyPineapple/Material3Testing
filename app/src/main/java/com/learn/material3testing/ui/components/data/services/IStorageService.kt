package com.learn.material3testing.ui.components.data.services

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.learn.material3testing.ui.components.data.Game
import kotlinx.coroutines.flow.Flow

interface IStorageService {
    val games: Flow<List<Game>>
    suspend fun getGame(gameId: String) : Game?
    suspend fun createGame(game: Game)
    suspend fun updateGame(game: Game)
    suspend fun deleteGame(gameId: String)
}