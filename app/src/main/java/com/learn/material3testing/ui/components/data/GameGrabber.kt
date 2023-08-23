package com.learn.material3testing.ui.components.data

class GameGrabber {
    fun GetGame(id: Int) : Game{
        return(games.first { it.id == id })
    }
}