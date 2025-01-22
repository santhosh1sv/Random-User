package com.nisum.test.randomuser.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.nisum.test.randomuser.R
import com.nisum.test.randomuser.business_logic.viewmodel.RandomUsersApiViewModel
import com.nisum.test.randomuser.ui.data.PaginationState
import com.nisum.test.randomuser.ui.data.RandomUserModel
import com.nisum.test.randomuser.ui.data.RandomUsersDetailRoute

@Composable
fun RandomUsersScreen(
    modifier: Modifier = Modifier,
    randomUsersApiViewModel: RandomUsersApiViewModel = hiltViewModel(),
    navController: NavController
) {
    var number by remember { mutableStateOf("") }
    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = number,
                    onValueChange = { number = it },
                    placeholder = { Text(text = stringResource(R.string.enter_the_number)) },
                    singleLine = true,
                    modifier = modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier.width(10.dp))
                Button(onClick = {
                    if (number != "" && number.toInt() != 0) {
                        randomUsersApiViewModel.clearPaging()
                        randomUsersApiViewModel.getRandomUsers(
                            number.toInt()
                        )
                        keyboard?.hide()
                    } else {
                        Toast.makeText(
                            context,
                            context.getText(R.string.please_enter_a_number),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }) {
                    Text(stringResource(R.string.fetch))
                }
            }

            Spacer(modifier.height(20.dp))
            RandomUsersList(
                num = number, modifier = modifier.weight(1f), navController = navController
            )
        }

    }

}


@Composable
fun RandomUsersList(
    num: String,
    modifier: Modifier = Modifier,
    randomUsersApiViewModel: RandomUsersApiViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val lazyColumnListState = rememberLazyListState()
    val userList by randomUsersApiViewModel.randomUsersListStateFlow.collectAsStateWithLifecycle()
    val pagingState by randomUsersApiViewModel.pagingStateFlow.collectAsStateWithLifecycle()
    val canPaginate by randomUsersApiViewModel.canPaginateStateFlow.collectAsStateWithLifecycle()
    val shouldPaginate by remember {
        derivedStateOf {
            canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -5) >= (lazyColumnListState.layoutInfo.totalItemsCount - 3)
        }
    }
    LaunchedEffect(key1 = shouldPaginate) {
        if (shouldPaginate && pagingState == PaginationState.REQUEST_INACTIVE) {
            randomUsersApiViewModel.getRandomUsers(num.toInt())
        }
    }
    LazyColumn(modifier = modifier, state = lazyColumnListState) {
        items(userList.size) {
            RandomUser(randomUserModel = userList[it], navController = navController)
            Spacer(modifier = modifier.height(10.dp))
        }
        when (pagingState) {
            PaginationState.LOADING -> {
                item {
                    Box(
                        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            PaginationState.PAGINATING -> {
                item {
                    Box(
                        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            else -> {}
        }

    }
    LaunchedEffect(pagingState) {
        when (pagingState) {
            PaginationState.ERROR -> {
                Toast.makeText(
                    context,
                    context.getText(R.string.uh_oh_something_has_gone_wrong),
                    Toast.LENGTH_LONG
                ).show()

            }


            PaginationState.EMPTY -> {
                Toast.makeText(
                    context, context.getText(R.string.no_data_found), Toast.LENGTH_LONG
                ).show()
            }

            else -> {

            }
        }
    }


}


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

