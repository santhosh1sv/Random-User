package com.compose.jetapp.data

val placeABidItemsList = listOf(
    PlaceABidItemModel(70),
    PlaceABidItemModel(75),
    PlaceABidItemModel(80),
    PlaceABidItemModel(85),
    PlaceABidItemModel(90),
    PlaceABidItemModel(95),
    PlaceABidItemModel(100)
)

data class PlaceABidItemModel(val count: Int, var color: Long = 0xfff4f4f2)
