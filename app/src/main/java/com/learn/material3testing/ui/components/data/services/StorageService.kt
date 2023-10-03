package com.learn.material3testing.ui.components.data.services

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.learn.material3testing.ui.components.data.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class StorageService : IStorageService  {

    override val games: Flow<List<Game>>
        get() = FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION)
            .whereEqualTo("userId", Firebase.auth.currentUser?.uid)
            .dataObjects()

    override suspend fun getGame(gameId: String) : Game? =
        FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION).document(gameId).get().await().toObject<Game>()

    override suspend fun createGame(game: Game) {
        FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION)
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

    override suspend fun deleteGame(gameId: String) {
        FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION)
            .document(gameId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot with ID $gameId successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    companion object {
        private const val GAME_COLLECTION = "games"
        private const val GAMESJR_COLLECTION = "gamesJr"
        private const val TAG = "StorageService"
    }
}
