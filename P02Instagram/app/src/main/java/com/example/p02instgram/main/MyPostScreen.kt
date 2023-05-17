package com.example.p02instgram.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.p02instgram.IgViewModel

@Composable
fun MyPostScreen(navController: NavController, vm: IgViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "MyPosts screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.POSTS,
            navController = navController)
    }
}