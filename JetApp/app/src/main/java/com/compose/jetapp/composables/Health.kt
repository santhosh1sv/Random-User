package com.compose.jetapp.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.R
import com.compose.jetapp.data.ClickedButton
import com.compose.jetapp.data.dailyTaskList


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Health(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                "Mental Health", fontSize = 18.sp, fontWeight = FontWeight.SemiBold
            )
        }, navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xfff8f4f3)))
    }, containerColor = Color(0xfff8f4f3)) {
        Column(
            modifier.padding(
                top = it.calculateTopPadding(), start = 20.dp, bottom = 20.dp, end = 20.dp
            )
        ) {
            HealthHeader()
            Spacer(modifier.height(15.dp))
            HealthBody()
            Spacer(modifier.height(30.dp))
            HeaderTail()
        }
    }

}

@Composable
fun HealthHeader(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            Column(modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                HealthHeaderItemTwo(color = Color.Cyan)
                HealthHeaderItemTwo(color = Color.Blue)
                HealthHeaderItemTwo(color = Color.Red)
            }
            Spacer(modifier.width(15.dp))
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                HealthHeaderItemOne(color = 0xff586ba3, height = 0.4f)
                HealthHeaderItemOne(color = 0xfffec806, height = 0.5f)
                HealthHeaderItemOne(color = 0xffffc704, height = 0.6f)
                HealthHeaderItemOne(color = 0xff94d5fd, height = 0.7f)
                HealthHeaderItemOne(color = 0xff53d170)
                HealthHeaderItemOne(color = 0xff94d5fd)
                HealthHeaderItemOne(color = 0xff53d170, height = 0.8f)
            }


        }
        Spacer(modifier.height(10.dp))
        Row(modifier.fillMaxWidth()) {
            HealthHeaderItemThree(day = "Su")
            HealthHeaderItemThree(day = "Mo")
            HealthHeaderItemThree(day = "Tu")
            HealthHeaderItemThree(day = "We")
            HealthHeaderItemThree(day = "Th")
            HealthHeaderItemThree(
                day = "Fr"
            )
            HealthHeaderItemThree(day = "Sa")


        }


    }
}

@Composable
fun HealthHeaderItemOne(
    modifier: Modifier = Modifier, maxHeight: Int = 120, height: Float = 1.0f, color: Long
) {
    Surface(
        modifier = if (height == 1.0f) modifier
            .width(5.dp)
            .height(maxHeight.dp)
        else modifier
            .width(5.dp)
            .fillMaxHeight(height),
        color = Color(color),
        shape = RoundedCornerShape(10.dp)

    ) {}

}

@Composable
fun HealthHeaderItemTwo(
    modifier: Modifier = Modifier, color: Color
) {
    Icon(
        modifier = modifier.size(20.dp),
        painter = painterResource(id = R.drawable.emoji),
        contentDescription = null,
        tint = color
    )
}

@Composable
fun RowScope.HealthHeaderItemThree(modifier: Modifier = Modifier, day: String) {
    Text(
        modifier = modifier.weight(1.0f),
        text = day,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.End
    )
}

@Composable
fun HealthBody(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier
                .fillMaxWidth()
                .background(color = Color(0xffefeae7), shape = CircleShape)
                .padding(2.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            var clickedButton by remember {
                mutableStateOf(ClickedButton.DAY)
            }

            HealthBodyItem(name = "Daily", isClicked = clickedButton == ClickedButton.DAY) {
                clickedButton = ClickedButton.DAY
            }
            HealthBodyItem(name = "Week", isClicked = clickedButton == ClickedButton.WEEK) {
                clickedButton = ClickedButton.WEEK
            }
            HealthBodyItem(
                name = "Month", isClicked = clickedButton == ClickedButton.MONTH
            ) { clickedButton = ClickedButton.MONTH }
            HealthBodyItem(
                name = "Year", isClicked = clickedButton == ClickedButton.YEAR
            ) { clickedButton = ClickedButton.YEAR }
        }
        Spacer(modifier.height(30.dp))
        Column(
            modifier
                .fillMaxWidth()
                .background(color = Color(0xfffff8de), shape = RoundedCornerShape(25.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(top = 10.dp, start = 20.dp, end = 10.dp, bottom = 20.dp)

        ) {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Daily tip", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                IconButton(onClick = {}, content = {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                })
            }
            Text(
                "In case of mistakes don't punish yourself. Instead, try to find out the causes to understand how to avoid them in future",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }

}

@Composable
fun RowScope.HealthBodyItem(
    modifier: Modifier = Modifier, name: String, isClicked: Boolean, onClick: () -> Unit

) {
    TextButton(
        modifier = modifier
            .weight(1.0f)
            .height(30.dp),
        onClick = { onClick() },
        shape = CircleShape,
        colors = ButtonDefaults.textButtonColors(
            containerColor = if (isClicked) MaterialTheme.colorScheme.background else Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(name, fontSize = 14.sp, fontWeight = FontWeight.W500)
    }

}

@Composable
fun HeaderTail(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Your daily task", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Text(
            "3/4 completed", fontSize = 14.sp, color = Color.Gray
        )
    }
    Spacer(modifier.height(15.dp))
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        horizontalArrangement = Arrangement.spacedBy(3.dp),
//        verticalArrangement = Arrangement.spacedBy(3.dp)
//    ) {
//
//        items(4) { index ->
//            HeaderTailItem(index = index)
//        }
//    }
    val noOfColumns = 2
    val count = dailyTaskList.size + 1 / noOfColumns
    SideEffect {
        Log.d("count", "$count")
    }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        items(count) {
            Row(
                modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                for (i in 0 until noOfColumns) {
                    val index = it * noOfColumns + i
                    if (index < dailyTaskList.size) HeaderTailItem(index = index)
                    else Spacer(modifier.weight(1f))
                }
            }
        }

    }

}

@Composable
fun RowScope.HeaderTailItem(
    modifier: Modifier = Modifier, index: Int
) {
    Column(
        modifier
            .weight(1.0f)
            .fillMaxHeight()
            .background(
                color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(30.dp)
            )
            .padding(10.dp)
    ) {
        Icon(
            modifier = modifier
                .size(50.dp)
                .background(color = Color(dailyTaskList[index].color), shape = CircleShape)
                .padding(15.dp),
            painter = painterResource(id = dailyTaskList[index].drawable),
            contentDescription = null
        )
        Spacer(modifier.height(10.dp))
        Text(
            text = dailyTaskList[index].name, fontSize = 16.sp, fontWeight = FontWeight.W500
        )
    }
}
