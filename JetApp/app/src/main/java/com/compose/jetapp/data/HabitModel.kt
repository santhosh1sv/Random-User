package com.compose.jetapp.data

import androidx.compose.ui.graphics.Color
import com.compose.jetapp.R

val habitList: List<HabitModel> = listOf(
    HabitModel("Work out", R.drawable.work_out),
    HabitModel("Eat Food", R.drawable.food),
    HabitModel("Music", R.drawable.music),
    HabitModel("Art & Design", R.drawable.art),
    HabitModel("Travelling", R.drawable.travel),
    HabitModel("Read Book", R.drawable.books),
    HabitModel("Gaming", R.drawable.gaming),
    HabitModel("Mechanic", R.drawable.mechanic)
)

data class HabitModel(
    val name: String,
    val drawable: Int,
    val color: Color = Color.Transparent,
    val borderColor: Color = Color(0xffe1e5f0)
)
