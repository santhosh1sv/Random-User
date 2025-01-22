package com.compose.jetapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PayStatus(modifier: Modifier = Modifier, status: String, color: Long, backgroundColor: Long) {
    Text(
        modifier = modifier
            .background(
                color = Color(backgroundColor), shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = status,
        color = Color(color),
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    )

}