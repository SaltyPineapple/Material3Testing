package com.learn.material3testing.ui.components.business

import android.util.Log
import com.google.firebase.firestore.ktx.toObject
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.services.StorageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GameService{
    fun createGame(game: Game) {
        val coroutineScope = MainScope()

        coroutineScope.launch(Dispatchers.Default) {
            StorageService().createGame(game)
        }
    }

    fun deleteGame(gameId: String){
        val coroutineScope = MainScope()

        coroutineScope.launch(Dispatchers.Default) {
            StorageService().deleteGame(gameId)
        }
    }

    companion object {
        private const val TAG = "GameService"
    }
}
