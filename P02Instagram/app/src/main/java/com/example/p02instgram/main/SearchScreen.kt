package com.example.p02instgram.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.p02instgram.IgViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.p02instgram.DestinationScreen

@Composable
fun SearchScreen(navController: NavController, vm: IgViewModel) {
    val searchedPostLoading = vm.searchedPostsProgress.value
    val searchedPosts = vm.searchedPosts.value
    var searchTerm by rememberSaveable { mutableStateOf("") }   //要 import getValue, setValue

    Column {
        SearchBar(
            searchTerm = searchTerm,
            onSearchChange = { searchTerm = it },
            onSearch = { vm.searchPosts(searchTerm) }
        )
        PostList(isContextLoading = false, postsLoading = searchedPostLoading, posts = searchedPosts, modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(8.dp)
        ) { post ->
            navigateTo(
                navController = navController,
                dest = DestinationScreen.SinglePost,
                NavParam("post", post)
            )
        }
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SEARCH,
            navController = navController
        )
    }
}

@Composable
fun SearchBar(searchTerm: String, onSearchChange: (String) -> Unit, onSearch: () -> Unit) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = searchTerm, onValueChange = onSearchChange,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, CircleShape),
        shape = CircleShape,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                focusManager.clearFocus()
            }
        ),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,  //此後三行是把Search 下的底線去掉
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            IconButton(onClick = {
                onSearch()
                focusManager.clearFocus()
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        }

    )
}