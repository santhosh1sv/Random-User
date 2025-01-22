package com.compose.jetapp.data

val growTaskList: List<GrowTaskModel> = listOf(
    GrowTaskModel("Add task", "Creatives for branging"),
    GrowTaskModel("Review", "Verification process with team"),
    GrowTaskModel("Finalize task", "Release the build into production")
)

data class GrowTaskModel(val taskName: String, val taskDetail: String)

