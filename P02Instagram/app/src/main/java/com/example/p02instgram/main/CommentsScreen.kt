package com.example.p02instgram.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.p02instgram.IgViewModel

@Composable
fun CommentsScreen(navController: NavController, vm: IgViewModel, postId: String) {
    Text(text = "Comments screen")
}