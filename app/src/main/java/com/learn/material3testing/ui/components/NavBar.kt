package com.learn.material3testing.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.learn.material3testing.ui.screens.Home
import com.learn.material3testing.ui.screens.Profile
import com.learn.material3testing.ui.screens.Search

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
    override val screen: @Composable () -> Unit = { Home() }
}

object SearchScreen: MaterialDestination {
    override val text = "Search"
    override val icon = Icons.Default.Search
    override val route = "SearchScreen"
    override val screen: @Composable () -> Unit = { Search() }
}

object ProfileScreen: MaterialDestination {
    override val text = "Profile"
    override val icon = Icons.Default.Person
    override val route = "ProfileScreen"
    override val screen: @Composable () -> Unit = { Profile() }
}

val NavBarItems = listOf(HomeScreen, SearchScreen, ProfileScreen)

@Composable
fun MaterialNavBar(navController: NavHostController, backStackEntry: State<NavBackStackEntry?>) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        NavBarItems.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(item.route) },
                label = {
                    Text(
                        text = item.text,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = "${item.text} Icon")
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        }
    }
}
