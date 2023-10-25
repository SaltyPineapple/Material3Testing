package com.learn.material3testing.ui.components.business

import android.util.Log
import com.google.firebase.firestore.ktx.toObject
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.Round
import com.learn.material3testing.ui.components.data.services.StorageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GameService{
    fun createGame(game: Game) {
        coroutineScope.launch(Dispatchers.Default) {
            StorageService().createGame(game)
        }
    }

   suspend fun getGameById(gameId: String) : Game? = StorageService().getGame(gameId)

    fun deleteGame(gameId: String){
        coroutineScope.launch(Dispatchers.Default) {
            StorageService().deleteGame(gameId)
        }
    }

    fun createRound(gameId: String, round: Round){
        coroutineScope.launch {
            StorageService().createRound(gameId, round)
        }
    }

    companion object {
        private const val TAG = "GameService"
        private val coroutineScope = MainScope()
    }
}
