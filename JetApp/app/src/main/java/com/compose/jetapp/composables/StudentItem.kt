package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
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
fun Student(modifier: Modifier = Modifier) {
    Box(
        modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(color = Color(0xffdee2ff), shape = RoundedCornerShape(20.dp))
            .padding(start = 20.dp, top = 10.dp, end = 10.dp, bottom = 20.dp)
    ) {
        Row(
            modifier
                .align(Alignment.TopEnd)
                .background(color = Color(0xffecedff), shape = CircleShape)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Homework")
            Spacer(modifier.width(5.dp))
            Icon(
                modifier = modifier
                    .size(20.dp)
                    .background(color = Color.Black, shape = CircleShape)
                    .padding(2.dp),
                imageVector = Icons.Default.Check,
                tint = Color.White,
                contentDescription = null
            )

        }
        Column(modifier.padding(top = 10.dp)) {
            Icon(
                modifier = modifier
                    .size(40.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                    .padding(horizontal = 5.dp, vertical = 10.dp),
                painter = painterResource(R.drawable.mobile),
                tint = Color.Black,
                contentDescription = null
            )
            Spacer(modifier.height(15.dp))
            Text("Basic mathematics", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier.height(5.dp))
            Text("Today, 8:15am", color = Color.Gray)
            Spacer(modifier.height(15.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(id = R.drawable.woman),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Spacer(modifier.width(10.dp))
                Text("Kathryn Murphy", fontWeight = FontWeight.W500)
            }

        }


    }
}