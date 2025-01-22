package com.compose.jetapp.data

import androidx.compose.ui.graphics.Color

val dayList: List<DayModel> = listOf(
    DayModel("Tue", "06"),
    DayModel("Wed", "07"),
    DayModel("Thu", "08"),
    DayModel("Fri", "09"),
    DayModel("Sat", "10"),
    DayModel("Sun", "11"),
    DayModel("Mon", "12"),
)

data class DayModel(
    val day: String,
    val date: String,
    val color: Color = Color.Transparent,
    val textColor: Color = Color.Gray
)
