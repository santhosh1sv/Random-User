package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LifeTune(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.padding(top = 20.dp, bottom = 5.dp, start = 5.dp, end = 5.dp),
        topBar = {
            TopAppBar(title = {
                Column {
                    Text(text = "Welcome back,", color = Color(0xff777573), fontSize = 14.sp)
                    Text(
                        text = "Andrew Hawkins",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }, navigationIcon = {
                Image(
                    modifier = modifier
                        .padding(start = 15.dp, end = 15.dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    painter = painterResource(id = R.drawable.man),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }, actions = {
                IconButton(onClick = {}, content = {
                    Box(contentAlignment = BiasAlignment(0.5f, -0.5f)) {
                        Icon(
                            modifier = modifier.size(30.dp),
                            imageVector = Icons.Default.NotificationsNone,
                            contentDescription = null
                        )
                        Surface(
                            modifier.size(6.dp), color = Color.Red, shape = CircleShape
                        ) {}
                    }
                })


            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xfff8f4f3)))
        },
        containerColor = Color(0xfff8f4f3)
    ) {
        LazyColumn(modifier.padding(top = it.calculateTopPadding())) {
            item {
                LifeTuneHeader()
                Spacer(modifier.height(30.dp))
            }

            item {
                LifeTuneBody()
                Spacer(modifier.height(30.dp))
            }

            item {
                LifeTuneTail()
                Spacer(modifier.height(10.dp))
            }
        }
    }

}

@Composable
fun LifeTuneHeader(modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 30.dp, start = 15.dp)) {
        Text("How do you feel today?", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier.height(10.dp))
        Row(modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            LifeTuneHeaderItem(name = "Terrible", color = Color.Red)
            LifeTuneHeaderItem(name = "Bad", color = Color.Magenta)
            LifeTuneHeaderItem(name = "Neutral", color = Color.Blue)
            LifeTuneHeaderItem(name = "Good", color = Color.Yellow)
            LifeTuneHeaderItem(name = "Awesome", color = Color.Green)

        }
    }
}

@Composable
fun LifeTuneHeaderItem(modifier: Modifier = Modifier, name: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FilledIconButton(
            modifier = modifier.size(60.dp),
            onClick = {},
            content = {
                Icon(
                    modifier = modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.emoji),
                    contentDescription = null,
                    tint = color
                )
            },
            colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background)
        )
        Spacer(modifier.height(5.dp))
        Text(
            text = name, fontSize = 16.sp, fontWeight = FontWeight.W500
        )
    }

}

@Composable
fun LifeTuneBody(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .background(color = Color(0xfffff8de), shape = RoundedCornerShape(25.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(top = 10.dp, start = 20.dp, end = 5.dp, bottom = 5.dp)

    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Insight", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            IconButton(onClick = {}, content = {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            })
        }
        Text(
            "It looks like you're stressed yesterday.Is everything ok?",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier.height(15.dp))
        Button(
            modifier = modifier.align(Alignment.End),
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
            contentPadding = PaddingValues(horizontal = 25.dp, vertical = 15.dp)
        ) {
            Text("Let's check in", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

    }
}

@Composable
fun LifeTuneTail(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(start = 15.dp),
        text = "Today's status",
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier.height(10.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        LifeTuneTailItem(
            color = Color(0xffd6ffdf),
            trackColor = Color(0xff5ad175),
            name = "Food",
            detail = "1185 of 2400 cals consumed",
            drawable = R.drawable.apple,
            progress = 0.7f,

            )
        LifeTuneTailItem(
            color = Color(0xffd8efff),
            trackColor = Color(0xff9ad7f8),
            name = "Water",
            detail = "You drank 4 out of 6 glasses of water",
            drawable = R.drawable.glass,
            progress = 0.68f
        )
    }
}

@Composable
fun RowScope.LifeTuneTailItem(
    modifier: Modifier = Modifier,
    color: Color,
    trackColor: Color,
    name: String,
    detail: String,
    drawable: Int,
    progress: Float
) {
    Column(
        modifier
            .weight(1.0f)
            .background(
                color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(30.dp)
            )
            .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = modifier
                    .size(50.dp)
                    .background(color = color, shape = CircleShape)
                    .padding(15.dp),
                painter = painterResource(id = drawable),
                contentDescription = null
            )
            Spacer(modifier.width(10.dp))
            Text(name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier.height(10.dp))
        Text(
            detail, fontSize = 14.sp, color = Color.Gray
        )
        Spacer(modifier.height(10.dp))
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = modifier.size(100.dp),
                progress = { progress },
                trackColor = Color(0xffeee9e6),
                color = trackColor,
                strokeWidth = 6.dp,
                strokeCap = StrokeCap.Round
            )
            Text(
                "${(progress * 100).toInt()}%", fontSize = 18.sp, fontWeight = FontWeight.SemiBold
            )
        }
    }
}
