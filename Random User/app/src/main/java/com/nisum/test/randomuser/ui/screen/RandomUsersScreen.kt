package com.nisum.test.randomuser.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nisum.test.randomuser.R
import com.nisum.test.randomuser.business_logic.viewmodel.RandomUsersApiViewModel
import com.nisum.test.randomuser.ui.component.RandomUsersList


@Composable
fun RandomUsersScreen(
    modifier: Modifier = Modifier,
    randomUsersApiViewModel: RandomUsersApiViewModel = hiltViewModel(),
    navController: NavController
) {
    var number by rememberSaveable { mutableStateOf("") }
    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val pattern = remember { Regex("^\\d+\$") }
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
                    onValueChange = { number = it.trim() },
                    placeholder = { Text(text = stringResource(R.string.enter_the_number)) },
                    singleLine = true,
                    modifier = modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier.width(10.dp))
                Button(onClick = {
                    randomUsersApiViewModel.clearPaging()
                    if (number.matches(pattern) && number.toInt() != 0) {
                        randomUsersApiViewModel.getRandomUsers(
                            number.toInt()
                        )
                    } else {
                        number = " "
                        Toast.makeText(
                            context,
                            context.getText(R.string.please_enter_a_number),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    keyboard?.hide()
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







