package com.compose.jetapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.components.DashedDivider
import com.compose.jetapp.data.PlanModel
import com.compose.jetapp.data.planList

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Trip(modifier: Modifier = Modifier) {
    Scaffold(containerColor = Color(0xffedf0f5), topBar = {
        CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(
                0xffedf0f5
            )
        ), title = {
            Text(
                text = "Trip details plan", fontSize = 20.sp, fontWeight = FontWeight.SemiBold
            )
        }, navigationIcon = {
            IconButton(onClick = {}, content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null
                )
            })
        })
    }) {
        Column(
            modifier
                .padding(
                    top = it.calculateTopPadding(), start = 5.dp, end = 10.dp, bottom = 20.dp
                )
                .fillMaxSize()
        ) {
            Row {
                HeaderTopItem(name = "Days plan")
                HeaderTopItem(name = "Reservations", color = Color(0xffc6c8c9))
                HeaderTopItem(name = "Budget", color = Color(0xffc6c8c9))
            }
            Row {
                HeaderBottomItem(
                    name = "June 2",
                    color = MaterialTheme.colorScheme.onBackground,
                    contentColor = MaterialTheme.colorScheme.background
                )
                HeaderBottomItem(
                    name = "June 3",
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
                HeaderBottomItem(
                    name = "Add",
                    color = Color(0xfffee600),
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    isAdd = true
                )
            }
            PlanList(modifier.weight(1.0f))
            Spacer(modifier.height(15.dp))
            Box(
                modifier.fillMaxWidth()

            ) {
                Button(
                    modifier = modifier.align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(vertical = 20.dp, horizontal = 25.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfffee600))
                ) {
                    Icon(
                        modifier = modifier.size(30.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier.width(5.dp))
                    Text(
                        text = "New step",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderTopItem(
    name: String, color: Color = MaterialTheme.colorScheme.onBackground
) {
    TextButton(
        onClick = {}, contentPadding = PaddingValues(10.dp)
    ) {
        Text(
            name, fontSize = 18.sp, fontWeight = FontWeight.W500, color = color
        )
    }
}

@Composable
fun HeaderBottomItem(
    modifier: Modifier = Modifier,
    name: String,
    color: Color,
    contentColor: Color,
    isAdd: Boolean = false
) {
    Button(
        modifier = modifier.padding(start = 10.dp, top = 5.dp, bottom = 10.dp),
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(name, fontSize = 16.sp, color = contentColor)
        if (isAdd) {
            Spacer(modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.Add, contentDescription = null, tint = contentColor
            )
        }

    }
}

@Composable
fun PlanList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(planList) {
            PlanItem(planModel = it)
        }

    }

}

@Composable
fun PlanItem(modifier: Modifier = Modifier, planModel: PlanModel) {
    if (planModel.type == 1) {
        Card(
            modifier
                .padding(5.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(modifier.padding(10.dp)) {
                Text(planModel.time, fontSize = 16.sp, color = Color.Gray)
                Spacer(modifier.height(5.dp))
                Text(
                    planModel.name,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier.height(5.dp))
                Text(planModel.detail, fontSize = 16.sp, color = Color.Gray)
            }
        }
    } else {
        Row(
            modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DashedDivider(modifier = modifier.weight(1.0f))
            Spacer(modifier.width(10.dp))
            Icon(imageVector = Icons.Default.DirectionsCar, contentDescription = null)
            Spacer(modifier.width(5.dp))
            Text(planModel.name, fontSize = 16.sp)
            Spacer(modifier.width(10.dp))
            Icon(imageVector = Icons.Default.AccessTimeFilled, contentDescription = null)
            Spacer(modifier.width(5.dp))
            Text(planModel.time, fontSize = 16.sp)
            Spacer(modifier.width(10.dp))
            DashedDivider(modifier = modifier.weight(1.0f))
        }
    }
}
