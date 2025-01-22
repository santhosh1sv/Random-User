package com.compose.jetapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun Schedule(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(color = Color(0xfff7f7f9), shape = RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "This week", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text("See all", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier.height(10.dp))
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Day(name = "SUN", value = "04")
            Day(name = "MON", value = "05")
            Day(name = "TUE", value = "06")
            Day(name = "WED", value = "07")
            Day(name = "THU", value = "08")
            Day(name = "FRI", value = "09")
            Day(name = "SAT", value = "10")
        }

    }

}

@Composable
fun Day(modifier: Modifier = Modifier, name: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = name, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier.height(10.dp))
        Text(value, fontSize = 14.sp)
    }

}