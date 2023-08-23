package com.learn.material3testing.ui.components.data

class GameGrabber {
    fun getGame(id: Int) : Game{
        return(games.first { it.id == id })
    }
}