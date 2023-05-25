package com.example.p02instgram.data

import android.content.res.AssetFileDescriptor

data class PostData(
    val postId: String?= null,
    val userId: String? = null,
    val username: String?= null,
    val userImage: String? = null,
    val postImage: String?= null,
    val postDescription: String?= null,
    val time: Long?= null,
)
