package com.compose.jetapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, backgroundColor = 0xffecf0f1)
@Composable
fun YourActivity(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier.weight(1f)) {
            Text(text = "Your Activity", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier.height(20.dp))
            Row {
                Text(
                    modifier = modifier.alignByBaseline(),
                    text = "48",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier.width(5.dp))
                Text(
                    modifier = modifier.alignByBaseline(),
                    text = "Days",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier.height(10.dp))
            Row {
                ActivityItem(color = 0xff9283ef, name = "Cardio")
                Spacer(modifier.width(10.dp))
                ActivityItem(color = 0xfff4aa65, name = "Workout")
            }
        }
        CircleIndicator()
    }

}

@Composable
fun ActivityItem(modifier: Modifier = Modifier, color: Long, name: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier
                .width(20.dp)
                .height(10.dp),
            color = Color(color),
            shape = RoundedCornerShape(10.dp)
        ) {

        }
        Spacer(modifier.width(5.dp))
        Text(name)
    }
}

@Composable
fun CircleIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier
            .width(100.dp)
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            progress = { 0.84f },
            modifier = modifier.fillMaxSize(),
            trackColor = Color(0xffecf0f1),
            color = Color(0xff9283ef),
            strokeWidth = 8.dp,
            strokeCap = StrokeCap.Round
        )

        CircularProgressIndicator(
            progress = { 0.7f },
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize(),
            trackColor = Color(0xffecf0f1),
            color = Color(0xfff4aa65),
            strokeWidth = 8.dp,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "84%", fontSize = 20.sp, fontWeight = FontWeight.SemiBold
        )
    }
}