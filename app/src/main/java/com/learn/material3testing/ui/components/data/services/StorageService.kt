package com.learn.material3testing.ui.components.data.services

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.learn.material3testing.ui.components.data.Game
import kotlinx.coroutines.tasks.await

class StorageService : IStorageService  {
    override suspend fun getGame(gameId: String) : Game? =
        FirebaseFirestore.getInstance().collection(GAME_COLLECTION).document(gameId).get().await().toObject<Game>()

    override suspend fun createGame(game: Game) {
        FirebaseFirestore.getInstance().collection(GAME_COLLECTION)
            .add(game)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    override suspend fun updateGame(game: Game) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGame(gameId: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val GAME_COLLECTION = "games"
        private const val TAG = "StorageService"
    }
}
