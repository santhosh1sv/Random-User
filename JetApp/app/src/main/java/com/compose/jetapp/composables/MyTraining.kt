package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
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
import com.compose.jetapp.components.DashedDivider

@Preview(showBackground = true, backgroundColor = 0xffecf0f1)
@Composable
fun MyTraining(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(20.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Text(text = "My training", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier.height(20.dp))
        MyTrainingItem(
            color = 0xff9283ef,
            icon = R.drawable.shoe,
            name = "Step Aerobics",
            date = "4 April",
            time = "4:30 pm",
            user = "Mayer",
            image = R.drawable.woman
        )
        Spacer(modifier.height(15.dp))
        DashedDivider()
        Spacer(modifier.height(15.dp))
        MyTrainingItem(
            color = 0xfff4aa65,
            icon = R.drawable.dumbell,
            name = "Fitness Basics",
            date = "4 April",
            time = "6:30 pm",
            user = "Clark Emard",
            image = R.drawable.man
        )
    }
}

@Composable
fun MyTrainingItem(
    modifier: Modifier = Modifier,
    color: Long,
    icon: Int,
    name: String,
    date: String,
    time: String,
    user: String,
    image: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = modifier
                .size(45.dp)
                .background(color = Color(color), shape = CircleShape)
                .padding(12.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White,

            )

        Spacer(modifier.width(10.dp))
        MyTrainingItemSchedule(name = name, date = date, time = time)
        Spacer(modifier.weight(1f))
        Text(user, fontSize = 12.sp)
        Spacer(modifier.width(10.dp))
        Image(
            modifier = modifier
                .size(35.dp)
                .clip(CircleShape),
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

    }
}

@Composable
fun MyTrainingItemSchedule(
    modifier: Modifier = Modifier, name: String, date: String, time: String
) {
    Column {
        Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = date, color = Color.Gray)
            Spacer(modifier.width(10.dp))
            Icon(
                modifier = modifier.size(15.dp),
                imageVector = Icons.Filled.AccessTimeFilled,
                contentDescription = null,
                tint = Color.Gray
            )
            Spacer(modifier.width(5.dp))
            Text(text = time, color = Color.Gray)
        }
    }
}


