package com.nisum.test.randomuser.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.nisum.test.randomuser.R
import com.nisum.test.randomuser.ui.data.RandomUserModel
import com.nisum.test.randomuser.ui.data.RandomUsersDetailRoute

@Composable
fun RandomUser(
    modifier: Modifier = Modifier, randomUserModel: RandomUserModel = RandomUserModel(
        "Jennie",
        "Nichols",
        "8929-Valwood Pkwy Billings Michigan UnitedStates",
        "https://randomuser.me/api/portraits/men/75.jpg",
        "female",
        "jennie.nichols@example.com",
        30,
        "(272) 790-0888"
    ), navController: NavController
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true
                )
            ) {
                navController.navigate(
                    RandomUsersDetailRoute(
                        randomUserModel.firstName,
                        randomUserModel.lastName,
                        randomUserModel.address,
                        randomUserModel.profilePicture,
                        randomUserModel.gender,
                        randomUserModel.email,
                        randomUserModel.age,
                        randomUserModel.phone
                    )
                )
            },
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black)
    )

    {
        Row(modifier = modifier.padding(10.dp)) {
            val painter = rememberAsyncImagePainter(
                model = randomUserModel.profilePicture, placeholder = painterResource(
                    id = R.drawable.ic_launcher_foreground
                )
            )
            Image(
                painter = painter,
                contentDescription = "Profile Picture",
                modifier = modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = modifier.width(15.dp))
            Column(modifier = modifier.weight(1f)) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = randomUserModel.firstName,
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                    Text(
                        text = randomUserModel.lastName,
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = randomUserModel.address,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black)
                )


            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun RandomUserPreview(navController: NavController = rememberNavController()) {
    RandomUser(navController = navController)
}