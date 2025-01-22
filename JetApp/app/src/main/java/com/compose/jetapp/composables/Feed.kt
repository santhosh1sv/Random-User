package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.R

@Preview(showBackground = true)
@Composable
fun Feed(modifier: Modifier = Modifier) {
    Column(modifier.padding(vertical = 15.dp)) {
        Column(modifier.padding(horizontal = 15.dp)) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "14 min ago",
                color = Color.Gray,
                textAlign = TextAlign.End
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier.width(10.dp))
                Column {
                    Text("Leslie Alexander", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier.height(3.dp))
                    Text("Architect", fontSize = 16.sp, color = Color.Gray)
                }
            }
            Spacer(modifier.height(15.dp))
            Text(
                "Another perfect day by the sea with my little sunshine. These moments are the ones that truly matter",
                fontSize = 16.sp
            )
            Spacer(modifier.height(3.dp))
            Text(
                "#FatherDaughter bonding #BeachDays", fontSize = 16.sp, color = Color(0xff5e8bef)
            )
        }
        Spacer(modifier.height(10.dp))
        Box {
            Image(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(30.dp)),
                painter = painterResource(id = R.drawable.fadau),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            FilledIconButton(modifier = modifier
                .padding(top = 16.dp, end = 16.dp)
                .size(50.dp)
                .align(Alignment.TopEnd), colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.background.copy(
                    0.5f
                ), contentColor = MaterialTheme.colorScheme.onBackground
            ), onClick = { }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
            }
            Row(
                modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp, end = 8.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(0.5f), shape = CircleShape
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var isToggle by remember {
                    mutableStateOf(false)
                }
                val imageVector: ImageVector
                val color = MaterialTheme.colorScheme.onBackground
                val tint: Color
                if (isToggle) {
                    imageVector = Icons.Default.ThumbUp
                    tint = Color(0xff5e8bef)
                } else {
                    imageVector = Icons.Default.ThumbUpOffAlt
                    tint = color
                }
                FeedItem(imageVector = imageVector, feed = "82 likes", tint = tint) {
                    isToggle = !isToggle
                }
                FeedItem(imageVector = Icons.Default.ChatBubbleOutline, feed = "11 comments")
                FeedItem(imageVector = Icons.Default.Share, feed = "Share") {

                }
            }
        }


    }


}

@Composable
fun FeedItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    feed: String,
    tint: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = modifier
                .size(30.dp)
                .padding(3.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() }, indication = null

                ) { onClick() }, imageVector = imageVector, contentDescription = "", tint = tint
        )
        Spacer(modifier.width(3.dp))
        Text(feed)
    }

}