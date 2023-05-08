package com.example.p02instgram.main

import android.app.Notification
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.p02instgram.IgViewModel

@Composable
fun NotificationMessage(vm: IgViewModel) {
    val notificationState = vm.popupNotification.value
    val notificationMsg = notificationState?.getContentOrNull()
    if(notificationMsg != null) {
        Toast.makeText(LocalContext.current, notificationMsg, Toast.LENGTH_LONG).show()
    }
}