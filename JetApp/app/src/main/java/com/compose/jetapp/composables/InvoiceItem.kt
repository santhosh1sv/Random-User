package com.compose.jetapp.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
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

@Preview(showBackground = true, backgroundColor = 0xffecf0f1)
@Composable
fun InvoiceItem(modifier: Modifier = Modifier) {

    Column(
        modifier
            .padding(15.dp)
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            User(
                image = R.drawable.avatar, name = "Lautaro Matinez", email = "Lautaro.m@gmail.com"
            )
            PayStatus(status = "Paid", color = 0xff5eca5e, backgroundColor = 0xffd2f1d2)
        }
        Spacer(modifier.height(15.dp))
        Row(
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(color = Color(0xffecf0f1), shape = RoundedCornerShape(10.dp))
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            InvoiceItemMiniDetail(name = "Amount", value = "£6.356")
            VerticalDivider(
                modifier = modifier
                    .fillMaxHeight(0.8f)
                    .width(1.dp), color = Color.Gray
            )
            InvoiceItemMiniDetail(name = "No", value = "#0013")
            VerticalDivider(
                modifier = modifier
                    .fillMaxHeight(0.8f)
                    .width(1.dp), color = Color.Gray
            )
            InvoiceItemMiniDetail(name = "Date", value = "24 Nov 2021")
        }

    }


}
