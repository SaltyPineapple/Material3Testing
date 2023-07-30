package com.learn.material3testing.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.Navigation

interface MaterialDestination {
    val text: String
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

object HomeScreen: MaterialDestination {
    override val text = "Home"
    override val icon = Icons.Default.Home
    override val route = "HomeScreen"
    override val screen: () -> Unit
        get() = TODO("Not yet implemented")
}

object SearchScreen: MaterialDestination {
    override val text = "Search"
    override val icon = Icons.Default.Search
    override val route = "SearchScreen"
    override val screen: () -> Unit
        get() = TODO("Not yet implemented")
}

object ProfileScreen: MaterialDestination {
    override val text = "Profile"
    override val icon = Icons.Default.Person
    override val route = "ProfileScreen"
    override val screen: () -> Unit
        get() = TODO("Not yet implemented")
}


val NavBarItems = listOf(HomeScreen, SearchScreen, ProfileScreen)
