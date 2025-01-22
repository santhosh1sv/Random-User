package com.compose.jetapp.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetapp.R

@Preview(showBackground = true)
@Composable
fun Explore(modifier: Modifier = Modifier) {
    Box {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.forest),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp),
            text = "XPLORE",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp
        )
        Column(
            modifier = modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "EXPLORE",
                fontSize = 60.sp,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
            )
            Text(
                "BEAUTY OF",
                fontSize = 60.sp,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
            )
            Text(
                "JOURNEY",
                fontSize = 60.sp,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
            )
            Spacer(modifier.height(30.dp))
            Text(
                "everything you can imagine, is here", fontSize = 16.sp,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.W500,
            )

        }
        Column(
            modifier = modifier.align(Alignment.BottomStart),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
                onClick = {

                },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(vertical = 18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff3e94ec)

                )
            ) {
                Text("Sign in", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier.height(20.dp))
            val annotatedText = buildAnnotatedString {
                append("Don't have any account? ")
                pushStringAnnotation(
                    tag = "login", annotation = "Login"
                )
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("Login")
                }
                pop()
            }
            ClickableText(modifier = modifier.padding(bottom = 50.dp),
                text = annotatedText,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.W500,
                ),
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "login", start = offset, end = offset
                    )[0].let { annotation ->
                        Log.d("Clicked", annotation.item)
                    }
                })


        }
    }
}