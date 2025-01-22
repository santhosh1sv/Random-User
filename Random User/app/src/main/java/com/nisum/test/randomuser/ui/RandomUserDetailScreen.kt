package com.nisum.test.randomuser.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nisum.test.randomuser.R
import com.nisum.test.randomuser.ui.data.RandomUsersDetailRoute

@Composable
fun RandomUserDetailScreen(
    modifier: Modifier = Modifier, randomUsersDetailRoute: RandomUsersDetailRoute
) {
    Column(modifier = modifier.fillMaxSize()) {
        val painter = rememberAsyncImagePainter(
            model = randomUsersDetailRoute.profilePicture, placeholder = painterResource(
                id = R.drawable.ic_launcher_foreground
            )
        )
        Image(
            painter = painter,
            contentDescription = "Profile Picture",
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.FillBounds,
        )
        Column(modifier = modifier.padding(15.dp)) {
            Text(
                text = stringResource(R.string.first_name, randomUsersDetailRoute.firstName),
                style = TextStyle(fontSize = 20.sp, color = Color.Black)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.last_name, randomUsersDetailRoute.lastName),
                style = TextStyle(fontSize = 20.sp, color = Color.Black)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.address, randomUsersDetailRoute.address),
                style = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.gender, randomUsersDetailRoute.gender),
                style = TextStyle(fontSize = 20.sp, color = Color.Black)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.email, randomUsersDetailRoute.email),
                style = TextStyle(fontSize = 20.sp, color = Color.Black)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.age, randomUsersDetailRoute.age),
                style = TextStyle(fontSize = 20.sp, color = Color.Black)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.phone, randomUsersDetailRoute.phone),
                style = TextStyle(fontSize = 20.sp, color = Color.Black)
            )
        }
    }

}