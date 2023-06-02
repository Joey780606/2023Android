package com.example.p02instgram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p02instgram.auth.LoginScreen
import com.example.p02instgram.auth.ProfileScreen
import com.example.p02instgram.auth.SignupScreen
import com.example.p02instgram.data.PostData
import com.example.p02instgram.main.FeedScreen
import com.example.p02instgram.main.MyPostScreen
import com.example.p02instgram.main.NewPostScreen
import com.example.p02instgram.main.SearchScreen
import com.example.p02instgram.main.SinglePostScreen
import com.example.p02instgram.ui.theme.P02InstgramTheme
import dagger.hilt.android.AndroidEntryPoint

/*
    Author: Joey
    Reference: uDemy class
      https://www.udemy.com/course/instagram_jetpack/learn/lecture/30041834#overview
    Info:
     1. 重要點請加: 重要
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P02InstgramTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InstagramApp()
                }
            }
        }
    }
}

sealed class DestinationScreen(val route: String) {
    object Signup: DestinationScreen("signup")
    object Login: DestinationScreen("login")
    object Feed: DestinationScreen("feed")
    object Search: DestinationScreen("search")
    object MyPosts: DestinationScreen("myposts")
    object Profile: DestinationScreen("profile")
    object NewPost: DestinationScreen("newpost/{imageUri}") {
        fun createRoute(uri: String) = "newpost/$uri"
    }
    object SinglePost: DestinationScreen("singlepgst")
}

@Composable
fun InstagramApp() {
    val vm = hiltViewModel<IgViewModel>()
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = DestinationScreen.Signup.route) {
        composable(DestinationScreen.Signup.route) {
            SignupScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Feed.route) {
            FeedScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Search.route) {
            SearchScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.MyPosts.route) {
            MyPostScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Profile.route) {
            ProfileScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.NewPost.route) { navBackStackEntry ->
            val imageUri = navBackStackEntry.arguments?.getString("imageUri")
            imageUri?.let {
                NewPostScreen(navController = navController, vm = vm, encodeUri = it)
            }
        }
        composable(DestinationScreen.SinglePost.route) {
            val postData = navController.previousBackStackEntry?.arguments?.getParcelable<PostData>("post")
            postData?.let {
                SinglePostScreen(navController = navController, vm = vm, post = postData)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    P02InstgramTheme {
    }
}