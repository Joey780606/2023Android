package com.example.p02instgram.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.p02instgram.IgViewModel
import com.example.p02instgram.data.PostData

@Composable
fun SinglePostScreen(navController: NavController, vm: IgViewModel, post: PostData) {
    Text(text = "Single post screen ${post.postDescription}")
}