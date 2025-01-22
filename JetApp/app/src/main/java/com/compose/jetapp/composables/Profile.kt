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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun Profile(modifier: Modifier = Modifier) {
    Box {
        Column(
            modifier
                .padding(top = 50.dp, start = 10.dp, end = 10.dp)
                .background(color = Color(0xffe0edfe), shape = RoundedCornerShape(30.dp))
                .padding(top = 70.dp, start = 25.dp, end = 25.dp, bottom = 15.dp)
        ) {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Chris Evans", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier.width(10.dp))
                Text("Teacher", fontSize = 16.sp, color = Color.Gray)
            }
            Spacer(modifier.height(15.dp))
            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                ProfileItem(name = "Followers", value = 325)
                ProfileItem(name = "Following", value = 246)
                ProfileItem(name = "Posts", value = 96)
            }

        }
        Image(
            modifier = modifier
                .align(Alignment.TopCenter)
                .size(100.dp)
                .border(
                    width = 4.dp, color = MaterialTheme.colorScheme.background, shape = CircleShape
                )
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

    }

}

@Composable
fun ProfileItem(modifier: Modifier = Modifier, name: String, value: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(name, fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier.height(5.dp))
        Text("$value", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
    }
}

