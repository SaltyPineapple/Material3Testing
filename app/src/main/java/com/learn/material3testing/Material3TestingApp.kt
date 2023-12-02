package com.learn.material3testing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3TestingApp() {
    MaterialScaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Saved Games") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Manage Profile"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,),
                )
        }
    ) {
        Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            Home()
        }
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