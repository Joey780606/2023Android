package com.example.p02instgram.data

import android.content.res.AssetFileDescriptor
import android.os.Parcel
import android.os.Parcelable

// 只要有修改,就把 Parcelable 後方拿掉,再 選擇 "Add parcelable implement",就會重長一次對應的
data class PostData(
    val postId: String?= null,
    val userId: String? = null,
    val username: String?= null,
    val userImage: String? = null,
    val postImage: String?= null,
    val postDescription: String?= null,
    val time: Long?= null,
    val likes: List<String>? = null,
    val searchTerms: List<String>? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(postId)
        parcel.writeString(userId)
        parcel.writeString(username)
        parcel.writeString(userImage)
        parcel.writeString(postImage)
        parcel.writeString(postDescription)
        parcel.writeValue(time)
        parcel.writeStringList(likes)
        parcel.writeStringList(searchTerms)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostData> {
        override fun createFromParcel(parcel: Parcel): PostData {
            return PostData(parcel)
        }

        override fun newArray(size: Int): Array<PostData?> {
            return arrayOfNulls(size)
        }
    }
}