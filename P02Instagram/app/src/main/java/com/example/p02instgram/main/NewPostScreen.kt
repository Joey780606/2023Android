package com.example.p02instgram.main

import android.icu.number.NumberFormatter.UnitWidth
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.p02instgram.IgViewModel
import com.example.p02instgram.data.PostData

/*
data class PostRow(
    var post1: PostData? = null,
    var post2: PostData? = null,
    var post3: PostData? = null
) {
    fun isFull() = post1 != null && post2 != null && post3 != null
    fun add(post: PostData) {
        if(post1 == null)
            post1 = post
        else if(post2 == null)
            post2 = post
        else if(post3 == null)
            post3 = post
    }
}
*/

@Composable
fun NewPostScreen(navController: NavController, vm: IgViewModel, encodeUri: String) {
    val imageUri by remember { mutableStateOf(encodeUri) } //remember 要加 androidx.compose.runtime.getValue
    var description by rememberSaveable { mutableStateOf("") } //rememberSaveable 要加 androidx.compose.runtime.setValue
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier
        .verticalScroll(scrollState)
        .fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Cancel", modifier = Modifier.clickable { navController.popBackStack() })
            Text(text = "Post", modifier = Modifier.clickable {
                focusManager.clearFocus()
                vm.onNewPost(Uri.parse(imageUri), description) {
                    navController.popBackStack()
                }
            })
        }

        CommonDivider()

        Image(
            painter = rememberImagePainter(imageUri),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 150.dp),    //這是最後修改的
            contentScale = ContentScale.FillWidth
        )
        
        Row(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                label = { Text(text = "Description") },
                singleLine = false,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black
                )
            )
        }
    }

    val inProgress = vm.inProgress.value
    if(inProgress)
        CommonProgressSpinner()
}

/*
@Composable
fun PostList(
    isContextLoading: Boolean,
    postsLoading: Boolean,
    posts: List<PostData>,
    modifier: Modifier,
    onPostClick: (PostData) -> Unit
) {
    if(postsLoading) {
        CommonProgressSpinner()
    } else if (posts.isEmpty()) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(!isContextLoading) Text(text = "No posts available")
        }
    } else {
        LazyColumn(modifier = modifier) {
            val rows = arrayListOf<PostRow>()
            var currentRow = PostRow()
            rows.add(currentRow)
            for (post in posts) {
                if(currentRow.isFull()) {
                    currentRow = PostRow()
                    rows.add(currentRow)
                }
                currentRow.add(post = post)
            }

            items(items = rows) { row->
                PostsRow(item = row, onPostClick = onPostClick)
            }
        }
    }
}

@Composable
fun PostsRow(item: PostRow, onPostClick: (PostData) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)) {
        PostImage(
            imageUri = item.post1?.postImage,
            modifier = Modifier
                .weight(1f)
                .clickable { item.post1?.let { post -> onPostClick(post) } }
        )
        PostImage(
            imageUri = item.post2?.postImage,
            modifier = Modifier
                .weight(1f)
                .clickable { item.post2?.let { post -> onPostClick(post) } }
        )
        PostImage(
            imageUri = item.post3?.postImage,
            modifier = Modifier
                .weight(1f)
                .clickable { item.post3?.let { post -> onPostClick(post) } }
        )
    }
}

@Composable
fun PostImage(imageUri: String?, modifier: Modifier) {
    Box(modifier = modifier) {
        var modifier = Modifier
            .padding(1.dp)
            .fillMaxSize()
        if(imageUri == null) {
            modifier = modifier.clickable(enabled = false) {}
        }
        CommonImage(data = imageUri, modifier = modifier, contentScale = ContentScale.Crop)
    }
}
 */