package com.example.mylemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mylemonade.ui.theme.MyLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(1) }
    val imageResource = when (state) {
        1 -> {R.drawable.lemon_tree        }
        in(2..4) -> {R.drawable.lemon_squeeze}
        5 -> {R.drawable.lemon_drink}
        else -> {R.drawable.lemon_restart}
    }

    val actionText = when (state) {
        1 -> {"Tap the lemon tree to select a lemon"}
        in(2..4) -> {"Keep tapping the lemon to squeeze it"}
        5 -> {"Tap the lemonade to drink it"}
        else -> {"Tap the empty glass to start again"}
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth().height(110.dp)
    ){
        Text(text = "Lemonade"
        )
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),

    ){
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = imageResource.toString(),
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    state = state + 1
                }

        )
        Text(text = actionText,
            modifier = modifier.padding(16.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    MyLemonadeTheme {
        LemonadeApp()
    }
}