package com.compose.jetapp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.data.PlaceABidItemModel
import com.compose.jetapp.data.placeABidItemsList
import kotlinx.coroutines.launch


@Preview(showBackground = true, backgroundColor = 0xff000000)
@Composable
fun PlaceABidBottomSheet(modifier: Modifier = Modifier) {
    Column(
        modifier
            .background(
                color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(20.dp)
            )
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp)
    ) {
        Surface(
            modifier
                .width(30.dp)
                .height(3.dp)
                .clip(shape = CircleShape)
                .align(Alignment.CenterHorizontally), color = Color(0xffe5e5db)
        ) {

        }
        Spacer(modifier.height(30.dp))
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Place a bid", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            PlaceABidLabelAndValue(label = "Time left ", value = "2h:45m:35s")
        }
        Spacer(modifier.height(10.dp))
        var isToggled by remember {
            mutableStateOf(false)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Auto bid", fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier.width(10.dp))
            IconToggleButton(
                modifier = modifier
                    .width(60.dp)
                    .height(30.dp),
                checked = isToggled,
                onCheckedChange = {
                    isToggled = it
                },
                colors = IconButtonDefaults.filledIconToggleButtonColors(
                    containerColor = if (!isToggled) Color(0xffe5e5db) else MaterialTheme.colorScheme.primary
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier.size(15.dp),
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    ) {}
                    Spacer(modifier.width(3.dp))
                    Text(
                        if (!isToggled) "Off" else "On",
                        fontSize = 14.sp,
                        color = if (!isToggled) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background
                    )
                }

            }
        }
        Spacer(modifier.height(20.dp))
        BidList()
        Spacer(modifier.height(20.dp))
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlaceABidActionButton(icon = Icons.Default.Remove)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("$130.00", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier.height(5.dp))
                PlaceABidLabelAndValue(label = "Current bid ", value = "$128.32")
            }
            PlaceABidActionButton(icon = Icons.Default.Add)

        }
        Spacer(modifier.height(30.dp))
        Button(modifier = modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            ),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
            onClick = {}) {
            Text("Place a bid", fontSize = 16.sp)
        }

    }
}

@Composable
fun BidList() {
    val bidItems = remember { mutableStateListOf<PlaceABidItemModel>() }
    bidItems.addAll(placeABidItemsList)
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp), state = listState) {
        itemsIndexed(bidItems) { index, item ->
            PlaceABidItem(count = item.count, color = item.color) {
                bidItems[index] = bidItems[index].copy(color = 0xfff2f721)
                for (i in 0 until bidItems.size) {
                    if (i != index) bidItems[i] = bidItems[i].copy(color = 0xfff4f4f2)
                }
                coroutineScope.launch { listState.animateScrollToItem(index) }


            }
        }

    }
}

@Composable
fun PlaceABidItem(modifier: Modifier = Modifier, count: Int, color: Long, onClick: () -> Unit) {
    FilledIconButton(
        modifier = modifier.size(60.dp),
        shape = RoundedCornerShape(15.dp),
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = Color(color), contentColor = MaterialTheme.colorScheme.onBackground
        ),
        onClick = onClick
    ) {
        Text(
            text = "$count", fontSize = 18.sp, fontWeight = FontWeight.W500
        )
    }
}

@Composable
fun PlaceABidActionButton(modifier: Modifier = Modifier, icon: ImageVector) {
    IconButton(modifier = modifier.size(40.dp), colors = IconButtonDefaults.filledIconButtonColors(
        containerColor = MaterialTheme.colorScheme.onBackground,
        contentColor = MaterialTheme.colorScheme.background
    ), onClick = {

    }) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Composable
fun PlaceABidLabelAndValue(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = Color.Gray)) {
                append(label)
            }
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.W500
                )
            ) {
                append(value)
            }
        }, fontSize = 16.sp
    )
}