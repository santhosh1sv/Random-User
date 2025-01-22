package com.compose.jetapp.data

import com.compose.jetapp.R

val destinationList: List<DestinationModel> = listOf(
    DestinationModel(
        name = "Nusa Penida", location = "Indonesia", drawable = R.drawable.nusa_beach, amount = 24
    ), DestinationModel(
        name = "Broken Beach",
        location = "Indonesia",
        drawable = R.drawable.broken_beach,
        amount = 28
    )

)

data class DestinationModel(
    val name: String,
    val location: String,
    val drawable: Int,
    val amount: Int,
    val isFavourite: Boolean = false
)