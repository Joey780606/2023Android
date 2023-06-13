package com.example.p02instgram.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.p02instgram.DestinationScreen    // 在MainActivity.kt 裡面
import com.example.p02instgram.R

enum class BottomNavigationItem(val icon: Int, val navDestination: DestinationScreen) { //重要
    FEED(R.drawable.ic_home, DestinationScreen.Feed),   //這表示enum的第一項,下面是第二與第三項
    SEARCH(R.drawable.ic_search, DestinationScreen.Search),
    POSTS(R.drawable.ic_posts, DestinationScreen.MyPosts)
}

@Composable
fun BottomNavigationMenu(selectedItem: BottomNavigationItem, navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 4.dp)
        .background(Color.White)
    ) {
        for(item in BottomNavigationItem.values()) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(navController, item.navDestination)  //這個很重要
                    },
                colorFilter = if (item == selectedItem) ColorFilter.tint(Color.Black)   //ColorFilter.tint 小技巧
                else ColorFilter.tint(Color.Gray)
            )
        }
    }
}