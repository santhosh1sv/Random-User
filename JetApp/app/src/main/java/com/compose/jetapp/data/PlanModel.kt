package com.compose.jetapp.data

val planList: List<PlanModel> = listOf(
    PlanModel(1, "7:30 am - 9:40 am", "Flight", "From Prague to Barcelona"),
    PlanModel(2, "20 min", "Taxi", ""),
    PlanModel(1, "11:00 am - 12:00 pm", "Hotel Barcelo` Sants", "Placa dels Paisos Catalans, 7"),
    PlanModel(1, "12:00 am - 3:30 pm", "Restaurant Veraz", "Av.de Francesc Cambo,14"),
    PlanModel(2, "23 min", "Taxi", ""),
    PlanModel(1, "4:00 pm - 6:00 pm", "Excursion in Barcelona", "Carrer del Poeta Cabanyes...")

)

data class PlanModel(val type: Int, val time: String, val name: String, val detail: String)
