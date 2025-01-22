package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
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
fun StudentTitle(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(20.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = R.drawable.woman),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(modifier.width(10.dp))
        Column {
            Text("Erica Hawkins", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier.height(5.dp))
            Text(
                "6th grade",
                fontSize = 14.sp,
                color = Color(0xff5b5e6a),
                fontWeight = FontWeight.W400
            )
        }
        Spacer(modifier.weight(1f))

        FilledIconButton(modifier = modifier
            .size(45.dp)
            .border(
                width = 2.dp, color = Color.Red, shape = RoundedCornerShape(10.dp)
            ),
            onClick = {

            },
            shape = RoundedCornerShape(10.dp),
            colors = IconButtonDefaults.iconButtonColors(containerColor =Color.Cyan),
            content = {
                Box(contentAlignment = BiasAlignment(0.5f, -0.5f)) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        tint = Color.Black,
                        contentDescription = null
                    )
                    Surface(
                        modifier.size(6.dp), color = Color.Green, shape = CircleShape
                    ) {}
                }

            })
    }
}