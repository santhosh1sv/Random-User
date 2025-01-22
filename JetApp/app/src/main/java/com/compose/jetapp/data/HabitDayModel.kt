package com.compose.jetapp.data

import com.compose.jetapp.R

val habitDayList: List<HabitDayModel> = listOf(
    HabitDayModel("Bicycle", R.drawable.bicycle, 0xffeaf5ed, "07.00 for 10km", 0xfffd5b32),
    HabitDayModel("Running", R.drawable.work_out, 0xfffaede6, "12.00 for 5km", 0xfffd5b32),
    HabitDayModel("Gym", R.drawable.gym, 0xfff9f9e4, "17.00 for 1 hour", 0xfffd5b32),
    HabitDayModel("Read Book", R.drawable.books, 0xfff7ecf5, "09.00 for 4 hours", 0xffe1e5f0)
)

data class HabitDayModel(
    val name: String, val drawable: Int, val color: Long, val detail: String, val checkColor: Long
)