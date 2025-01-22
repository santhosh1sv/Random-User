package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.compose.jetapp.data.FlowModel
import com.compose.jetapp.data.GrowTaskModel
import com.compose.jetapp.data.flowList
import com.compose.jetapp.data.growTaskList
import com.compose.jetapp.utils.dashedBorder


@Preview(showBackground = true)
@Composable
fun Grow(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(color = Color(0xfff6f6ef))
            .padding(20.dp)
    ) {
        GrowTitle()
        Spacer(modifier.height(20.dp))
        GrowSearch()
        Spacer(modifier.height(30.dp))
        GrowBody()
        Spacer(modifier.height(30.dp))
        GrowTailHeader()
        Spacer(modifier.height(20.dp))
        GrowTailList()
    }
}

@Composable
fun GrowTitle(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Welcome back", fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier.height(5.dp))
            Text("Carolina Terner", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Box(contentAlignment = BiasAlignment(-1.25f, 1.25f)) {
            Image(
                modifier = modifier
                    .size(45.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.woman),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = modifier
                    .size(20.dp)
                    .background(color = Color.Black, shape = CircleShape)
                    .border(width = 2.dp, color = Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    fontSize = 10.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,

                    )
            }


        }
    }
}

@Composable
fun GrowSearch(modifier: Modifier = Modifier) {

}

@Composable
fun GrowBody(modifier: Modifier = Modifier) {
    Text(text = "Task-based explanation process", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier.height(20.dp))
    Row(
        modifier
            .horizontalScroll(rememberScrollState())
            .height(IntrinsicSize.Max)
    ) {
        growTaskList.forEachIndexed { index, item ->
            GrowBodyItem(position = index, growTaskModel = item)
        }

    }


}

@Composable
fun GrowBodyItem(modifier: Modifier = Modifier, position: Int, growTaskModel: GrowTaskModel) {
    Column(
        if (position == 0) modifier
            .padding(start = 1.dp, end = 10.dp)
            .width(100.dp)
            .fillMaxHeight()
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .dashedBorder(color = Color.Gray, shape = RoundedCornerShape(20.dp))
            .padding(20.dp)
        else modifier
            .padding(end = 10.dp)
            .width(120.dp)
            .fillMaxHeight()
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(20.dp)


    ) {
        Text(
            modifier = modifier
                .background(
                    color = Color(if (position == 0) 0xffdafa6c else 0xfff6f6ef),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(5.dp),
            text = growTaskModel.taskName,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier.height(10.dp))
        Text(text = growTaskModel.taskDetail, fontSize = 14.sp)

    }

}

@Composable
fun GrowTailHeader(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("Flows list") }
    val options = listOf("Option 1", "Option 2", "Option 3")
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Row(
                modifier.clickable(
                    interactionSource = interactionSource, indication = null
                ) { expanded = !expanded }, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOptionText, fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
                Spacer(modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },

                ) {
                options.forEach { option ->
                    DropdownMenuItem(text = {
                        Text(text = option)
                    }, onClick = {
                        selectedOptionText = option
                        expanded = false
                    })
                }
            }
        }
        Text("See all", fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun GrowTailList(modifier: Modifier = Modifier) {
    LazyColumn {
        items(flowList) {
            GrowTailListItem(modifier = modifier, flowModel = it)
            Spacer(modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier.height(10.dp))
        }
    }

}

@Composable
fun GrowTailListItem(modifier: Modifier = Modifier, flowModel: FlowModel) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = flowModel.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier.height(5.dp))
            Text(text = flowModel.time, fontSize = 12.sp, color = Color.Gray)
        }
        IconButton(colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xffd3f26a)),
            onClick = {},
            content = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            })
    }

}
