package com.rhythmlife.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

import com.rhythmlife.app.ui.screens.AddScreen
import com.rhythmlife.app.ui.screens.HomeScreen
import com.rhythmlife.app.ui.screens.LogScreen

private sealed class Dest(val route: String, val label: String) {
    data object Home : Dest("home", "Home")
    data object Add : Dest("add", "Add")
    data object Log : Dest("log", "Log")
}

@Composable
fun RhythmLifeApp(vm: MainViewModel = viewModel()) {
    val nav = rememberNavController()
    val currentRoute = currentRoute(nav)

    MaterialTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == Dest.Home.route,
                        onClick = { nav.navigate(Dest.Home.route) { launchSingleTop = true } },
                        icon = { Icon(Icons.Filled.Home, null) },
                        label = { Text(Dest.Home.label) }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Dest.Add.route,
                        onClick = { nav.navigate(Dest.Add.route) { launchSingleTop = true } },
                        icon = { Icon(Icons.Filled.AddCircle, null) },
                        label = { Text(Dest.Add.label) }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Dest.Log.route,
                        onClick = { nav.navigate(Dest.Log.route) { launchSingleTop = true } },
                        icon = { Icon(Icons.Filled.List, null) },
                        label = { Text(Dest.Log.label) }
                    )
                }
            }
        ) { pad ->
            NavHost(
                navController = nav,
                startDestination = Dest.Home.route,
                modifier = Modifier.padding(pad)
            ) {
                composable(Dest.Home.route) { HomeScreen(vm) }
                composable(Dest.Add.route) { AddScreen(vm) }
                composable(Dest.Log.route) { LogScreen(vm) }
            }
        }
    }
}

@Composable
private fun currentRoute(nav: NavHostController): String? {
    val backStack by nav.currentBackStackEntryAsState()
    return backStack?.destination?.route
}
