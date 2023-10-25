package com.learn.material3testing.ui.components.data

import com.google.firebase.firestore.DocumentId


data class Round(
    @DocumentId var id: String? = null,
    var roundNumber: Int? = null,
    val scores: List<Int> = emptyList(),
)
