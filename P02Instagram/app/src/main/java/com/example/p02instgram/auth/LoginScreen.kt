package com.example.p02instgram.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.p02instgram.DestinationScreen
import com.example.p02instgram.IgViewModel
import com.example.p02instgram.main.navigateTo

@Composable
fun LoginScreen(navController: NavController, vm:IgViewModel) {
    Text(text = "New here? Go to signup ->",
        color = Color.Blue,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navigateTo(navController, DestinationScreen.Signup)
            }
    )
}