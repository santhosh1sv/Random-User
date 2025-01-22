package com.compose.jetapp.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.compose.jetapp.ui.theme.JetAppTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerSample() {
    val pagerState = rememberPagerState(pageCount = { 10 })
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,

        ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    when (page) {
                        0 -> Color.Red
                        1 -> Color.Green
                        2 -> Color.Blue
                        3 -> Color.Yellow
                        4 -> Color.Cyan
                        else -> Color.Black
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
          Text(text ="hi" , color = MaterialTheme.colorScheme.background)
        }
    }

    LaunchedEffect(key1 = true, block = {
        pagerState.scrollToPage(2)
    }
    )

}

@Preview(showBackground = true)
@Composable
fun HorizontalPagerPreview() {
    HorizontalPagerSample()

}