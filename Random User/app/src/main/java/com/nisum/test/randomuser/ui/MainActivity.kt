package com.nisum.test.randomuser.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nisum.test.randomuser.ui.data.RandomUsersDetailRoute
import com.nisum.test.randomuser.ui.data.RandomUsersRoute
import com.nisum.test.randomuser.ui.screen.RandomUserDetailScreen
import com.nisum.test.randomuser.ui.screen.RandomUsersScreen
import com.nisum.test.randomuser.ui.theme.RandomUserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomUserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RandomUsersRoute) {
        composable<RandomUsersRoute> {
            RandomUsersScreen(navController = navController)
        }
        composable<RandomUsersDetailRoute> {
            RandomUserDetailScreen(randomUsersDetailRoute = it.toRoute<RandomUsersDetailRoute>())
        }
    }

}