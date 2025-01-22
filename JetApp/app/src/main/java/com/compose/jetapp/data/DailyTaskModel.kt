package com.compose.jetapp.data

import com.compose.jetapp.R

val dailyTaskList: List<DailyTaskModel> = listOf(
    DailyTaskModel(0xffd8efff, R.drawable.pad, "Extra version"),
    DailyTaskModel(0xffd4ffdf, R.drawable.back_anxiety, "Back anxiety inventory"),
    DailyTaskModel(0xfffff8de, R.drawable.yoga, "Breathing & yoga exercises"),
    DailyTaskModel(0xffe3e9ff, R.drawable.leadership, "7 powerful leadership exercises")
)

data class DailyTaskModel(val color: Long, val drawable: Int, val name: String)
