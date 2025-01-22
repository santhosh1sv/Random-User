package com.compose.jetapp.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun InvoiceItemDetailTwo(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(10.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color(0xffecf0f1), shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        InvoiceItemDetailThree(item = "Item", quantity = "Qty", price = "Price", isLabel = true)
        Spacer(modifier.height(10.dp))
        InvoiceItemDetailThree(item = "UIX Re-Design", quantity = "10", price = "£ 5.200")
        Spacer(modifier.height(10.dp))
        InvoiceItemDetailThree(item = "Custom Illustration", quantity = "5", price = "£ 1.150")
        Spacer(modifier.height(10.dp))
    }
}

@Composable
fun InvoiceItemDetailThree(
    modifier: Modifier = Modifier,
    item: String,
    quantity: String,
    price: String,
    isLabel: Boolean = false
) {
    Row(modifier.fillMaxWidth()) {
        Text(
            modifier = modifier.fillMaxWidth(0.7f),
            text = item,
            color = if (isLabel) Color.Gray else Color.Black,
            fontSize = if (isLabel) 12.sp else 14.sp,
            fontWeight = if (isLabel) FontWeight.Normal else FontWeight.SemiBold
        )
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = modifier.padding(start = 5.dp),
                text = quantity,
                color = if (isLabel) Color.Gray else Color.Black,
                fontSize = if (isLabel) 12.sp else 14.sp,
                fontWeight = if (isLabel) FontWeight.Normal else FontWeight.SemiBold
            )
            Text(
                modifier = modifier.padding(start = 5.dp),
                text = price,
                color = if (isLabel) Color.Gray else Color.Black,
                fontSize = if (isLabel) 12.sp else 14.sp,
                fontWeight = if (isLabel) FontWeight.Normal else FontWeight.SemiBold
            )

        }
    }
}