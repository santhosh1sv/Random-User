package com.compose.jetapp.data

import com.compose.jetapp.R

val destinationTypeList: List<DestinationTypeModel> = listOf(
    DestinationTypeModel(name = "Beach", drawable = R.drawable.beach, isSelected = true),
    DestinationTypeModel(name = "Mountain", drawable = R.drawable.mountain),
    DestinationTypeModel(name = "Monument", drawable = R.drawable.monument)
)


data class DestinationTypeModel(
    val name: String,
    val drawable: Int,
    val isSelected: Boolean = false
)