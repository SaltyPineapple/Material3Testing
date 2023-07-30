package com.learn.material3testing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTargetMarker
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learn.material3testing.ui.components.HomeScreen
import com.learn.material3testing.ui.components.NavBarItems
import com.learn.material3testing.ui.components.ProfileScreen
import com.learn.material3testing.ui.components.SearchScreen
import com.learn.material3testing.ui.theme.Material3TestingTheme

@Composable
fun Material3TestingApp() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    Material3TestingTheme {
        Scaffold(
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
}

@Composable
private fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeScreen.route,
){
    NavHost(navController = navController, startDestination = startDestination){
        composable(route = HomeScreen.route){
            HomeScreen.screen
        }
        composable(route = SearchScreen.route){
            SearchScreen.screen
        }
        composable(route = ProfileScreen.route){
            ProfileScreen.screen
        }
    }
}


@Preview
@Composable
fun Material3TestingAppPreview(){
    Material3TestingApp()
}