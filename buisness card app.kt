package com.example.practice_2_task_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_2_task_manager.ui.theme.Practice_2_task_managerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practice_2_task_managerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuisnessCard("Nathan P" , "Android Developer" , "(123)-456-7890" , "xyz@gmail.com")
                }
            }
        }
    }
}

@Composable
fun BuisnessCard(name : String , title : String , phone : String , email : String, modifier : Modifier = Modifier ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth().fillMaxHeight()
    ){
        Column(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.size(120.dp).clip(CircleShape).align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {

                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null, modifier = modifier.fillMaxSize(),
                )
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,modifier = modifier.size(120.dp),
                    //alignment = CenterHorizontally
                )
            }

            Text(
                text = name,
                fontSize = 60.sp,
                modifier = modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold
                //textAlign = Center
            )
            Text(
                text = title ,
                fontSize = 30.sp,
                modifier = modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                //textAlign = Center
            )
            Text(
                text = phone ,
                fontSize = 20.sp,
                modifier = modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                //textAlign = Center
            )
            Text(
                text = email ,
                fontSize = 20.sp,
                modifier = modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                //textAlign = Center
            )

        }
    }


}

@Preview(showBackground = true)
@Composable
fun BuisnessCardPreview() {
    Practice_2_task_managerTheme {
        BuisnessCard("Nathan P" , "Title" , "(123)-456-7890" , "xyz@gmail.com")
    }
}