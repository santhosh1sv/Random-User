package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.R

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun BidItem(modifier: Modifier = Modifier, color: Long = 0xffb29a8d) {
    Box(
        modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(250.dp)
            .background(color = Color(color), shape = RoundedCornerShape(20.dp))

    ) {
        Image(
            modifier = modifier
                .size(150.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                Color(color), BlendMode.Darken
            )


        )
        Row(
            modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 5.dp, end = 10.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(0.5f), shape = CircleShape
                    )
                    .padding(10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier
                        .size(15.dp)
                        .background(color = Color.Black, shape = CircleShape)
                        .padding(2.dp),
                    imageVector = Icons.Default.Check,
                    tint = Color.White,
                    contentDescription = null
                )
                Spacer(modifier.width(5.dp))
                Text("2h 45m 32s", fontSize = 14.sp, fontWeight = FontWeight.W500)
            }
            var isToggled by remember {
                mutableStateOf(false)
            }
            IconToggleButton(
                modifier = modifier
                    .size(45.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    ), checked = isToggled, onCheckedChange = {
                    isToggled = it
                }, colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = Color.Transparent, checkedContainerColor = Color.Transparent
                )
            ) {
                Icon(
                    modifier = modifier.size(25.dp),
                    imageVector = if (!isToggled) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
        Row(
            modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background.copy(0.2f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(start = 25.dp, top = 20.dp, end = 15.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Retro camera", fontSize = 16.sp)
                Spacer(modifier.height(5.dp))
                Text("$128.32", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Button(colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(15.dp),
                onClick = {}) {
                Text("Place a bid", fontSize = 16.sp)
            }

        }
    }
}