package com.compose.jetapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InvoiceItemMiniDetail(modifier: Modifier = Modifier, name: String, value: String) {
    Column {
        Text(name, fontSize = 12.sp, color = Color.Gray)
        Spacer(modifier.height(5.dp))
        Text(value, fontWeight = FontWeight.SemiBold)
    }

}