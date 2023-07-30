package com.learn.material3testing.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MaterialScaffold(
    modifier: Modifier = Modifier,
    bottomBar: @Composable (() -> Unit) = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = bottomBar,
        content = content
    )
}