package com.compose.jetapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.compose.jetapp.utils.dpToPx
import kotlin.math.roundToInt

@Composable
fun DashedDivider(
    modifier: Modifier = Modifier, widthInDp: Int = 5, gapInDp: Int = 3
) {
    var totalWidthInPx by remember {
        mutableStateOf(0.0f)
    }
    var totalBars: Int
    Row(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                totalWidthInPx = it.size.width.toFloat()
            }, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        totalBars = (totalWidthInPx / (widthInDp.dp.dpToPx() + gapInDp.dp.dpToPx())).roundToInt()
        for (i in 1..totalBars) {
            HorizontalDivider(
                modifier.width(widthInDp.dp), color = Color.LightGray, thickness = 2.dp
            )
            Spacer(modifier.width(gapInDp.dp))
        }
    }
}