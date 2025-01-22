package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.R

@Preview(showBackground = true)
@Composable
fun Group(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .background(color = Color(0xfffce7e4), shape = RoundedCornerShape(30.dp))
            .padding(15.dp)
    ) {
        Image(
            modifier = modifier
                .size(60.dp)
                .background(
                    color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(20.dp)
                )
                .padding(10.dp),
            painter = painterResource(id = R.drawable.soccer),
            contentDescription = ""
        )
        Spacer(modifier.height(10.dp))
        Text("Team sports", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier.height(5.dp))
        Text("+11 new posts", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier.height(15.dp))
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                OverlappingImage(drawable = R.drawable.man)
                OverlappingImage(
                    modifier = modifier.padding(start = 30.dp), drawable = R.drawable.profile
                )
                Box(
                    modifier = modifier
                        .padding(start = 60.dp)
                        .size(50.dp)
                        .background(color = Color(0xfffce7e4), shape = CircleShape)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.background,
                            shape = CircleShape
                        ), contentAlignment = Alignment.Center

                ) {
                    Text("+96", fontWeight = FontWeight.W500)
                }
            }
            FilledIconButton(
                modifier = modifier.size(60.dp),
                onClick = { },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color(0xffe48474),
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "")

            }
        }

    }
}

@Composable
fun OverlappingImage(modifier: Modifier = Modifier, drawable: Int) {
    Image(
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp, color = MaterialTheme.colorScheme.background, shape = CircleShape
            ),
        painter = painterResource(id = drawable),
        contentDescription = "",
        contentScale = ContentScale.FillBounds
    )
}