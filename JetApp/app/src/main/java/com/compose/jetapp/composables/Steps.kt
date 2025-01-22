package com.compose.jetapp.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Steps(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground)
    ) {
        SummaryChallenge()
        DailyActivity(modifier.align(Alignment.BottomStart))
        BottomBar()
    }

}

@Composable
fun SummaryChallenge(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
            .background(
                color = MaterialTheme.colorScheme.onBackground, shape = RoundedCornerShape(20.dp)
            )
            .padding(start = 60.dp, end = 60.dp, top = 50.dp, bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Summary Challenge",
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.SemiBold
        )
        Box(modifier.weight(1.0f)) {
            var startAngle: Float =
                calculateStartAngle(SemiCircleIndicatorItem(sweepAngle = 40f, isFirstItem = true))
            var sweepAngle: Float = calculateSweepAngle(30f)

            startAngle = calculateStartAngle(
                SemiCircleIndicatorItem(
                    startAngle = startAngle, sweepAngle = sweepAngle
                )
            )
            sweepAngle = calculateSweepAngle(40f)
            startAngle = calculateStartAngle(
                SemiCircleIndicatorItem(
                    startAngle = startAngle, sweepAngle = sweepAngle
                )
            )
            sweepAngle = calculateSweepAngle(5f)
            startAngle = calculateStartAngle(
                SemiCircleIndicatorItem(
                    startAngle = startAngle, sweepAngle = sweepAngle
                )
            )
            sweepAngle = calculateSweepAngle(25f)
            SemiCircleIndicatorItem(
                startAngle = startAngle, sweepAngle = sweepAngle
            )

            Column(
                modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "390 140",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.background
                )
                Spacer(modifier.height(5.dp))
                Text("steps of 1 million", fontSize = 14.sp, color = Color.Gray)
            }


        }
    }
}

@Composable
fun SemiCircleIndicatorItem(
    modifier: Modifier = Modifier,
    startAngle: Float = -190f,
    sweepAngle: Float,
    isFirstItem: Boolean = false
): Float {

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1.5f)
    ) {
        val strokeWidth = 20.dp.toPx()
        if (isFirstItem) {
            drawArc(
                color = Color(0xff97f6a7),
                startAngle,
                sweepAngle / 2,
                useCenter = false,
                size = Size(size.width, size.height * 2),
                style = Stroke(strokeWidth, pathEffect = PathEffect.cornerPathEffect(30f)),
            )
            drawArc(
                color = Color(0xff2e2e2e),
                startAngle + (sweepAngle / 2),
                sweepAngle / 2,
                useCenter = false,
                size = Size(size.width, size.height * 2),
                style = Stroke(
                    strokeWidth
                )

            )
        } else {
            drawArc(
                color = Color(0xff97f6a7),
                startAngle,
                sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height * 2),
                style = Stroke(strokeWidth, pathEffect = PathEffect.cornerPathEffect(15f))
            )
        }
    }
    return startAngle + sweepAngle
}

fun calculateStartAngle(startAngle: Float): Float {
    val gap = 2f
    return startAngle + gap

}

fun calculateSweepAngle(sweepAngle: Float): Float {
    return sweepAngle
}

@Composable
fun DailyActivity(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .background(
                color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(30.dp)
            )
            .padding(15.dp)
    ) {
        Row(modifier.fillMaxWidth()) {
            DailyActivityItem(
                color = 0xff8bceff,
                imageVector = Icons.Default.AutoGraph,
                name = "Distance",
                value = "9.15 km"
            )
            Spacer(modifier.width(5.dp))
            DailyActivityItem(
                color = 0xffeab5fd,
                imageVector = Icons.Default.LocationOn,
                name = "Steps",
                value = "13453"
            )
            Spacer(modifier.width(5.dp))
            DailyActivityItem(
                color = 0xfffff844,
                imageVector = Icons.Default.LocalFireDepartment,
                name = "Calories",
                value = "490 kcal"
            )
        }
    }
}

@Composable
fun RowScope.DailyActivityItem(
    modifier: Modifier = Modifier,
    color: Long,
    imageVector: ImageVector,
    name: String,
    value: String
) {
    Column(
        modifier
            .weight(1.0f)
            .background(
                color = Color(color), shape = RoundedCornerShape(30.dp)
            )
            .padding(15.dp)
    ) {
        Icon(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.background.copy(0.4f),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(10.dp), imageVector = imageVector, contentDescription = null
        )
        Spacer(modifier.height(25.dp))
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.W500)
        Spacer(modifier.height(5.dp))
        Text(text = name, fontSize = 14.sp, color = Color.Gray)


    }
}


@Composable
fun BottomBar() {

}
