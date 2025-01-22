package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun Event(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .background(color = Color(0xfffbf0ff), shape = RoundedCornerShape(20.dp))
            .padding(start = 2.dp, end = 20.dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .width(80.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(20.dp)),
            painter = painterResource(id = R.drawable.movie),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier.width(10.dp))
        Column {
            Text("Comedy show", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier.height(5.dp))
            Text(
                "26 Apr, 6:30 pm",
                fontSize = 14.sp,
                color = Color(0xff5b5e6a),
                fontWeight = FontWeight.W400
            )
        }
        Spacer(modifier.weight(1f))
        IconButton(modifier = modifier
            .size(35.dp)
            .background(
                color = Color.White, shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp), onClick = {

        }, content = {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                tint = Color.Black,
                contentDescription = null
            )
        })
    }
}