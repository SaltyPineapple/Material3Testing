package com.learn.material3testing.ui.components.data.services

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.learn.material3testing.ui.components.data.Game
import com.learn.material3testing.ui.components.data.Round
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class StorageService : IStorageService  {

    override val games: Flow<List<Game>>
        get() = FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION)
            .whereEqualTo("userId", Firebase.auth.currentUser?.uid).orderBy("dateCreated", Query.Direction.ASCENDING)
            .dataObjects()

    override suspend fun getGame(gameId: String) : Game? {
        val game = FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION).document(gameId).get().await().toObject<Game>()
        game?.rounds = getAllRounds(gameId)
        return game
    }

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

    /*
    Quick note: if we are able to add the rounds to the game view model from the onset, we won't need special routes for accessing
     */
    override suspend fun createRound(gameId: String, round: Round) {
        val allRounds = getAllRounds(gameId)

        round.roundNumber = allRounds.size + 1
        FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION).document(gameId).collection(
            ROUNDS_COLLECTION)
            .add(round)
    }

    override suspend fun getAllRounds(gameId: String): List<Round> {
        val rounds = mutableListOf<Round>()
        val gameRef = FirebaseFirestore.getInstance().collection(GAMESJR_COLLECTION).document(gameId)
        val roundsCollection = gameRef.collection(ROUNDS_COLLECTION).orderBy("roundNumber", Query.Direction.ASCENDING).get().await()

        roundsCollection?.documents?.forEach {
            it.toObject<Round>()?.let { round -> rounds.add(round) }
        }

        return rounds
    }

    companion object {
        private const val GAME_COLLECTION = "games"
        private const val GAMESJR_COLLECTION = "gamesJr"
        private const val ROUNDS_COLLECTION = "rounds"
        private const val TAG = "StorageService"
    }
}
