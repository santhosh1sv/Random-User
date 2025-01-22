package com.compose.jetapp.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.jetapp.R
import com.compose.jetapp.components.InvoiceItemMiniDetail
import com.compose.jetapp.components.PayStatus
import com.compose.jetapp.components.User

@Preview(showBackground = true)
@Composable
fun InvoiceItemDetailOne(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(10.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color(0xffecf0f1), shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InvoiceItemMiniDetail(name = "Billing", value = "Redesign Mobile App")
            PayStatus(status = "Unpaid", color = 0xffe7a52a, backgroundColor = 0xfffaedd3)
        }
        Spacer(modifier.height(15.dp))
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            User(
                image = R.drawable.avatar,
                name = "Vladimir Petkovic",
                email = "Petkovic.V@gmail.com"
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )

        }
        Spacer(modifier.height(10.dp))
        Row(
            modifier
                .fillMaxWidth()
                .background(color = Color(0xffecf0f1), shape = RoundedCornerShape(10.dp))
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            InvoiceItemMiniDetail(name = "Issued", value = "24 Nov 2021")
            InvoiceItemMiniDetail(name = "Due", value = "4 Dec 2021")
            Icon(
                imageVector = Icons.Rounded.MoreHoriz, contentDescription = null, tint = Color.Gray
            )
        }
    }
}