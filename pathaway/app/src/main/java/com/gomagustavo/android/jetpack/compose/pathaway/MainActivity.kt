package com.gomagustavo.android.jetpack.compose.pathaway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(
                Message(
                    "Android",
                    "Jetpack Compose"
                )
            )
        }
    }
}

data class Message(
    val author: String,
    val body: String,
)

@Composable
fun MessageCard(message: Message) {
    Column {
        Text(text = message.author)
        Text(text = message.body)
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(
        message = Message(
            author = "Colleague",
            body = "Hey, take a look at Jetpack Compose, it's great!"
        )
    )
}