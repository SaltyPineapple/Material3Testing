package com.learn.material3testing.ui.components.business

import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.services.StorageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GameService{
    fun createGame(game: Game) {
        val coroutineScope = MainScope()

        coroutineScope.launch(Dispatchers.Default) {
            StorageService().createGame(game)
        }
    }
}
