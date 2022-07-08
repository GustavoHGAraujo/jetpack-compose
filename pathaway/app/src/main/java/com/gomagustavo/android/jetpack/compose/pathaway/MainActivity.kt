package com.gomagustavo.android.jetpack.compose.pathaway

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gomagustavo.android.jetpack.compose.pathaway.ui.theme.JetpackComposePathawayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePathawayTheme {
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.surface
            }
        )

        Column(
            modifier = Modifier.clickable { isExpanded = isExpanded.not() }
        ) {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.width(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                color = surfaceColor,
                elevation = 1.dp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    JetpackComposePathawayTheme {
        MessageCard(
            message = Message(
                author = "Colleague",
                body = "Hey, take a look at Jetpack Compose, it's great!"
            )
        )
    }
}

@Preview
@Composable
fun PreviewConversation() {
    JetpackComposePathawayTheme {
        Conversation(SampleData.conversationSample)
    }
}
