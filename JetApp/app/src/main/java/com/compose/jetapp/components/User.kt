package com.compose.jetapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun User(modifier: Modifier = Modifier, image: Int, name: String, email: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = modifier
                .size(30.dp)
                .clip(CircleShape),
            painter = painterResource(id = image),
            contentDescription = null
        )
        Spacer(modifier.width(5.dp))
        Column {
            Text(name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier.height(5.dp))
            Text(email, fontSize = 12.sp, color = Color.Gray)
        }
    }
}