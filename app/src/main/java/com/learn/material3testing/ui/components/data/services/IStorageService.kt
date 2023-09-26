package com.learn.material3testing.ui.components.data.services

import com.learn.material3testing.ui.components.data.Game

interface IStorageService {
    suspend fun getGame(gameId: String) : Game?
    suspend fun createGame(game: Game)
    suspend fun updateGame(game: Game)
    suspend fun deleteGame(gameId: Int)
}