package com.learn.material3testing

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learn.material3testing.ui.components.HomeScreen
import com.learn.material3testing.ui.components.MaterialNavBar
import com.learn.material3testing.ui.components.MaterialScaffold
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
        bottomBar = { MaterialNavBar(navController, backStackEntry) },
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