package com.example.p02instgram.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.p02instgram.IgViewModel

@Composable
fun FeedScreen(navController: NavController, vm: IgViewModel) {
    Text(text = "Feed screen")
}