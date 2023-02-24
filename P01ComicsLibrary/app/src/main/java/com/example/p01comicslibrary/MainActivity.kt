package com.example.p01comicslibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p01comicslibrary.ui.theme.P01ComicsLibraryTheme
import com.example.p01comicslibrary.view.CharactersBottomNav
import com.example.p01comicslibrary.view.CollectionScreen
import com.example.p01comicslibrary.view.LibraryScreen
import com.example.p01comicslibrary.viewmodel.LibraryApiViewModel
import dagger.hilt.android.AndroidEntryPoint

sealed class Destination(val route: String) {
    object Library: Destination("library")
    object Collection: Destination("collection")
    object CharacterDetail: Destination("character/{characterId") {
        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val lvm by viewModels<LibraryApiViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P01ComicsLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    CharactersScaffold(navController = navController, lvm) // CharactersScaffold是一個composable
                }
            }
        }
    }
}

@Composable
fun CharactersScaffold(navController: NavHostController, lvm: LibraryApiViewModel) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { CharactersBottomNav(navController = navController)}
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Library.route) {
            composable(Destination.Library.route) {
                LibraryScreen(navController, lvm, paddingValues)
            }
            composable(Destination.Collection.route) {
                CollectionScreen()
            }
            composable(Destination.CharacterDetail.route) { navBackStackEntry ->
            }
        }
    }
}