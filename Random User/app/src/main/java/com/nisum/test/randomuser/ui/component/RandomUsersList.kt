package com.nisum.test.randomuser.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nisum.test.randomuser.R
import com.nisum.test.randomuser.business_logic.viewmodel.RandomUsersApiViewModel
import com.nisum.test.randomuser.ui.data.PaginationState

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