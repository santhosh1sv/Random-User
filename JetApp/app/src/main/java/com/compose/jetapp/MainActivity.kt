package com.compose.jetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.composables.Explore
import com.compose.jetapp.composables.ExploreIn
import com.compose.jetapp.composables.Feed
import com.compose.jetapp.composables.Group
import com.compose.jetapp.composables.Habit
import com.compose.jetapp.composables.HabitDays
import com.compose.jetapp.composables.Profile
import com.compose.jetapp.ui.theme.JetAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetAppTheme {
              Explore()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(

        text = "Hello $name!", modifier = modifier.padding(10.dp)
    )
}


@Composable
fun GreetingPreview() {
    JetAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            GreetingRow()
        }
    }
}

@Composable
fun GreetingRow(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(all = 10.dp)
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier
                .padding(10.dp)
                .size(30.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
        Greeting("Android")
        Greeting("iPhone")
    }
}

@Composable
fun RecipeHeader(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomTextField(
            modifier.weight(1f)

        )

        IconButton(modifier = modifier
            .padding(10.dp)
            .background(color = Color(0xFFF3F5F7), shape = CircleShape),

            onClick = {

            }, content = {
                Icon(
                    modifier = modifier.padding(5.dp),
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(modifier: Modifier = Modifier, imeAction: ImeAction = ImeAction.Next) {
    var textFieldValue by remember {
        mutableStateOf("")
    }
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
    TextField(modifier = modifier, value = textFieldValue, onValueChange = {
        textFieldValue = it

    }, placeholder = {
        Text("Search Recipe")
    }, colors = TextFieldDefaults.textFieldColors(
        containerColor = Color(0xFFF3F5F7),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    ),

        shape = CircleShape, keyboardOptions = KeyboardOptions(imeAction = imeAction),

        keyboardActions = KeyboardActions {
            if (imeAction == ImeAction.Done) localSoftwareKeyboardController?.hide()
        }

    )

}

@Preview(showBackground = true)
@Composable
fun Home(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        HomeTop()
        HomeBottom(modifier.align(Alignment.BottomStart))
    }
}

@Composable
fun HomeTop(modifier: Modifier = Modifier) {
    Box {
        Image(
            modifier = modifier
                .clip(
                    shape = RoundedCornerShape(
                        bottomStart = 30.dp, bottomEnd = 30.dp
                    )
                )
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.chef_illustration),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,

            )
        IconButton(modifier = modifier
            .background(
                color = Color.Transparent, shape = CircleShape
            )
            .padding(top = 10.dp, start = 10.dp)
            .align(alignment = Alignment.TopStart),
            onClick = {},
            content = {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            })
    }
}

@Composable
fun HomeBottom(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.55f)
            .background(
                color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(30.dp)
            )
            .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
    ) {

        Text(text = "INSPIRATION", fontSize = 12.sp, letterSpacing = 5.sp)
        Spacer(modifier.height(10.dp))
        Text(
            modifier = modifier.padding(end = 20.dp),
            text = "40+ Sides to mix and match for any feast",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier.height(10.dp))
        Row(
            modifier = modifier
                .padding(end = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                modifier = modifier.weight(1.0f), text = "Lisa Scholzel", fontSize = 12.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = modifier.size(20.dp),
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                )
                Spacer(modifier.width(5.dp))
                Text(text = "98943", fontSize = 12.sp)
            }

        }
        Spacer(modifier.height(30.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Our Latest Recipes", fontSize = 16.sp, fontWeight = FontWeight.SemiBold
            )

            TextButton(contentPadding = PaddingValues(horizontal = 20.dp), onClick = {

            }) {
                Text(
                    text = "See All",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

        }


    }

}


@Composable
fun RecipeList() {
    val recipeList = listOf("", "")
    LazyRow {
        itemsIndexed(recipeList) { index, item ->
            RecipeItem()
        }
    }

}


@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,

    ) {
    var text by remember { mutableStateOf("") }
    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }
    BasicTextField(value = text, onValueChange = { newText ->
        text = newText
    }, modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(
                50

            )
        )
        .focusRequester(focusRequester), keyboardActions = KeyboardActions {
        focusRequester.requestFocus()
    }) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
            Spacer(modifier.width(10.dp))
            if (text.isEmpty()) Text(text = "Enter Recipe", color = Color.Gray)
            it()


        }
    }

}