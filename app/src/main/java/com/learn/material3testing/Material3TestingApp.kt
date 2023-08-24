package com.learn.material3testing

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learn.material3testing.ui.components.HomeScreen
import com.learn.material3testing.ui.components.MaterialScaffold
import com.learn.material3testing.ui.components.NavBarItems
import com.learn.material3testing.ui.components.ProfileScreen
import com.learn.material3testing.ui.components.SearchScreen
import com.learn.material3testing.ui.screens.Home
import com.learn.material3testing.ui.screens.Profile
import com.learn.material3testing.ui.screens.Search
import com.learn.material3testing.ui.theme.Material3TestingTheme

@Composable
fun Material3TestingApp() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    MaterialScaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer){
                NavBarItems.forEach { item ->
                    var selected = item.route == backStackEntry.value?.destination?.route
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
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,

                            )
                    )
                }
            }
        },
    ) { padding ->
        NavGraph(modifier = Modifier.padding(padding), navController = navController)
    }
}

@Composable
private fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = HomeScreen.route,
){
    NavHost(navController = navController, startDestination = startDestination){
        composable(route = HomeScreen.route){
            Home()
        }
        composable(route = SearchScreen.route){
            Search()
        }
        composable(route = ProfileScreen.route){
            Profile()
        }
    }
}

@Preview("Lightmode", backgroundColor = 0xFFF1EFEF)
@Composable
fun Material3TestingAppPreviewLight(){
    Material3TestingTheme(darkTheme = false) {
        Material3TestingApp()
    }
}

@Preview("Darkmode", backgroundColor = 0xFF3F3E3E)
@Composable
fun Material3TestingAppPreviewDark(){
    Material3TestingTheme(darkTheme = true) {
        Material3TestingApp()
    }
}