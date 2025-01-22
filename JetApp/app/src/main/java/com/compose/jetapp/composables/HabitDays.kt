package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.R
import com.compose.jetapp.data.DayModel
import com.compose.jetapp.data.HabitDayModel
import com.compose.jetapp.data.dayList
import com.compose.jetapp.data.habitDayList

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitDays(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text("Wednesday, 24", fontSize = 16.sp, color = Color.Gray)
        }, navigationIcon = {
            TitleIconButton(
                modifier = modifier.padding(start = 20.dp), imageVector = Icons.Outlined.GridView
            )
        }, actions = {
            TitleIconButton(imageVector = Icons.Outlined.CalendarMonth)
            Spacer(modifier.width(20.dp))
        })
    }) {
        Column(
            modifier
                .padding(
                    top = it.calculateTopPadding() + 30.dp,
                    bottom = 20.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .fillMaxSize()
        ) {
            NotificationMessage()
            Spacer(modifier.height(20.dp))
            DayList()
            Spacer(modifier.height(30.dp))
            HabitDayList()
        }

    }
}

@Composable
fun TitleIconButton(modifier: Modifier = Modifier, imageVector: ImageVector) {
    FilledIconButton(
        modifier = modifier
            .size(45.dp)
            .border(width = 1.dp, color = Color(0xffe1e5f0), shape = CircleShape),
        onClick = { },
        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
    }
}

@Composable
fun NotificationMessage(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onBackground, shape = RoundedCornerShape(10.dp)
            )
            .padding(top = 10.dp, start = 20.dp, bottom = 15.dp, end = 10.dp)
    ) {
        Icon(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(5.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = true
                    )
                ) { },
            imageVector = Icons.Outlined.Info,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.background
        )
        Row(
            modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .size(70.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(
                            0.2f
                        ), shape = CircleShape
                    )
                    .padding(15.dp),
                painter = painterResource(R.drawable.books),
                contentDescription = ""
            )
            Spacer(modifier.width(10.dp))
            Column {
                Text("Notification", fontSize = 16.sp, color = MaterialTheme.colorScheme.background)
                Spacer(
                    modifier.height(5.dp)
                )
                Text(
                    "Now is the time to read the book, you can change it in settings.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }


}

@Composable
fun DayList() {
    val dayItemList = remember { mutableStateListOf<DayModel>() }
    dayItemList.addAll(dayList)
    var clickedItem = -1
    val clickedTextColor = MaterialTheme.colorScheme.background
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(dayItemList.size) {
            DayListItem(dayModel = dayItemList[it]) {
                if (clickedItem != -1) dayItemList[clickedItem] =
                    dayItemList[clickedItem].copy(color = Color.Transparent, textColor = Color.Gray)
                dayItemList[it] = dayItemList[it].copy(
                    color = Color(0xfffd5b32), textColor = clickedTextColor
                )
                clickedItem = it
            }
        }

    }
}

@Composable
fun DayListItem(modifier: Modifier = Modifier, dayModel: DayModel, onClick: () -> Unit) {
    Column(modifier
        .background(color = dayModel.color, shape = RoundedCornerShape(15.dp))
        .border(width = 1.dp, color = Color(0xffe1e5f0), shape = RoundedCornerShape(15.dp))
        .clip(RoundedCornerShape(15.dp))
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(
                bounded = true
            )
        ) { onClick() }
        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(dayModel.day, fontSize = 14.sp, color = dayModel.textColor)
        Spacer(modifier.height(5.dp))
        Text(
            dayModel.date,
            fontSize = 18.sp,
            color = dayModel.textColor,
            fontWeight = FontWeight.W500
        )
    }
}

@Composable
fun HabitDayList(modifier: Modifier = Modifier) {
    HabitDayListHeader()
    Spacer(modifier.height(20.dp))
    val habitDayItemList = remember { mutableStateListOf<HabitDayModel>() }
    habitDayItemList.addAll(habitDayList)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(habitDayItemList.size) {
            HabitDayListItem(habitDayModel = habitDayItemList[it])
        }
    }
}

@Composable
fun HabitDayListHeader(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Tuesday habit", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Text("See all", fontSize = 14.sp, color = Color.Gray)

    }
}

@Composable
fun HabitDayListItem(modifier: Modifier = Modifier, habitDayModel: HabitDayModel) {
    Column(
        modifier
            .height(150.dp)
            .background(color = Color(habitDayModel.color), shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                modifier = modifier.size(40.dp),
                painter = painterResource(id = habitDayModel.drawable),
                contentDescription = ""
            )
            Icon(
                modifier = modifier
                    .size(30.dp)
                    .background(color = MaterialTheme.colorScheme.background, shape = CircleShape)
                    .padding(5.dp),
                imageVector = Icons.Default.Check,
                contentDescription = "",
                tint = Color(habitDayModel.checkColor)
            )
        }
        Box(modifier.weight(1.0f), contentAlignment = Alignment.BottomStart) {
            Column(modifier.fillMaxWidth()) {
                Text(
                    habitDayModel.name, fontSize = 16.sp, fontWeight = FontWeight.W500
                )
                Spacer(modifier.height(5.dp))
                Text(
                    habitDayModel.detail, fontSize = 14.sp, color = Color.Gray
                )
            }
        }


    }
}