package com.compose.jetapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.data.DestinationModel
import com.compose.jetapp.data.DestinationTypeModel
import com.compose.jetapp.data.destinationList
import com.compose.jetapp.data.destinationTypeList


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreIn(modifier: Modifier = Modifier) {
    Scaffold(containerColor = Color(0xfffafafa), topBar = {
        CenterAlignedTopAppBar(title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Location", fontSize = 14.sp, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = modifier.size(15.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "",
                        tint = Color(0xff3e94ec)
                    )
                    Text(
                        "Denpasar, INA",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

            }
        }, navigationIcon = {
            FilledIconButton(
                modifier = modifier
                    .padding(start = 20.dp)
                    .size(45.dp),
                onClick = { },
                colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }

        }, actions = {
            FilledIconButton(
                modifier = modifier
                    .padding(end = 20.dp)
                    .size(45.dp),
                onClick = { },
                colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Box(contentAlignment = BiasAlignment(0.6f, -0.6f)) {
                    Icon(imageVector = Icons.Default.NotificationsNone, contentDescription = "")
                    Surface(
                        modifier = modifier.size(5.dp), color = Color.Red, shape = CircleShape
                    ) {

                    }
                }

            }
        })
    }) {
        Column(
            modifier.padding(
                top = it.calculateTopPadding() + 30.dp, start = 20.dp, end = 20.dp
            )
        ) {
            SearchDestination()
            Spacer(modifier.height(30.dp))
            DestinationTypeList()
            Spacer(modifier.height(30.dp))
            DestinationList()
            Spacer(modifier.height(30.dp))
            Map()
        }
    }
}

@Composable
fun SearchDestination(modifier: Modifier = Modifier) {
    var searchText by remember {
        mutableStateOf("")
    }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color(0xff3e94ec), backgroundColor = Color(0xff3e94ec).copy(alpha = 0.4f)

    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = {
                searchText = it
            },
            placeholder = {
                Text("Search destination...")
            },
            prefix = {
                Icon(
                    modifier = modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color(0xff3e94ec)
                )
            },

            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0xff3e94ec),

                ),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
            ),

            )
    }
}

@Composable
fun DestinationTypeList() {
    val destinationTypeItemList = remember { mutableStateListOf<DestinationTypeModel>() }
    destinationTypeItemList.addAll(destinationTypeList)
    var selectedIndex = 0
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(destinationTypeItemList.size) {
            DestinationTypeItem(destinationTypeModel = destinationTypeItemList[it]) {
                destinationTypeItemList[selectedIndex] =
                    destinationTypeItemList[selectedIndex].copy(
                        isSelected = false
                    )

                destinationTypeItemList[it] = destinationTypeItemList[it].copy(
                    isSelected = true
                )
                selectedIndex = it
            }
        }

    }
}

@Composable
fun DestinationTypeItem(
    modifier: Modifier = Modifier,
    destinationTypeModel: DestinationTypeModel,
    onSelected: () -> Unit
) {
    Row(
        modifier
            .background(
                color = if (!destinationTypeModel.isSelected) MaterialTheme.colorScheme.background else Color(
                    0xff3e94ec
                ), shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true
                )
            ) { onSelected() }
            .padding(start = 8.dp, end = 15.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = modifier
                .size(40.dp)
                .background(
                    color = if (!destinationTypeModel.isSelected) Color(0xfffafafa) else MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp),
            painter = painterResource(id = destinationTypeModel.drawable),
            contentDescription = ""
        )
        Spacer(modifier.width(10.dp))
        Text(
            destinationTypeModel.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = if (!destinationTypeModel.isSelected) Color.DarkGray else MaterialTheme.colorScheme.background
        )
    }
}

@Composable
fun DestinationList(modifier: Modifier = Modifier) {
    ExploreInHeader(text = "Recommended", button = "Explore")
    Spacer(modifier.height(15.dp))
    val destinationItemList = remember { mutableStateListOf<DestinationModel>() }
    destinationItemList.addAll(destinationList)
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(destinationItemList.size) { index ->
            DestinationItem(destinationModel = destinationItemList[index]) {
                destinationItemList[index] = destinationItemList[index].copy(isFavourite = it)
            }
        }
    }
}

@Composable
fun ExploreInHeader(modifier: Modifier = Modifier, text: String, button: String) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Text(button, fontSize = 14.sp, color = Color(0xff3e94ec), fontWeight = FontWeight.W500)
    }
}

@Composable
fun DestinationItem(
    modifier: Modifier = Modifier,
    destinationModel: DestinationModel,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier
            .width(205.dp)
            .background(
                color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        Box {
            Image(
                modifier = modifier
                    .padding(bottom = 25.dp)
                    .fillMaxWidth()
                    .height(205.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(id = destinationModel.drawable),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            IconToggleButton(
                modifier = modifier.align(Alignment.BottomEnd),
                checked = destinationModel.isFavourite,
                onCheckedChange = { onCheckedChange(it) },
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = Color(0xff3e94ec),
                    contentColor = MaterialTheme.colorScheme.background,
                    checkedContainerColor = Color(0xff3e94ec),
                    checkedContentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Icon(
                    imageVector = if (!destinationModel.isFavourite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = null
                )
            }

        }
        Text(destinationModel.name, fontSize = 16.sp, fontWeight = FontWeight.W500)
        Spacer(modifier.height(5.dp))
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.size(15.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "",
                    tint = Color(0xff3e94ec)
                )
                Text(destinationModel.location, color = Color.Gray)
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xff3e94ec))) {
                        append("$${destinationModel.amount}")
                    }
                    withStyle(SpanStyle(color = Color.Gray)) {
                        append("/Person")
                    }
                }, fontSize = 14.sp
            )

        }
    }
}

@Composable
fun Map() {
    ExploreInHeader(text = "Based on your location", button = "See map")
}