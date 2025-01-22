package com.compose.jetapp.data

val flowList: List<FlowModel> = listOf(
    FlowModel("Document verification", "3 min ago"), FlowModel("Newbie onboarding", "5 days ago")
)

data class FlowModel(val name: String, val time: String)