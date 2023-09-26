package com.learn.material3testing.ui.components.data

import com.google.firebase.firestore.FirebaseFirestore

class GameGrabber {
    fun getGame(id: Int) : Game{
        return(games.first { it.id == id })
    }
}