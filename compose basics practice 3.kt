package com.example.practice_2_task_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice_2_task_manager.ui.theme.Practice_2_task_managerTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposeQuadrant(
                stringResource(R.string.sr1),
                stringResource(R.string.sr2),
                Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            ComposeQuadrant(
                stringResource(R.string.sr3),
                stringResource(R.string.sr4),
                Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }


        Row(Modifier.weight(1f)) {
            ComposeQuadrant(
                stringResource(R.string.sr5),
                stringResource(R.string.sr6),
                Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposeQuadrant(
                stringResource(R.string.sr7),
                stringResource(R.string.sr8),
                Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }

    }
}
@Composable
fun ComposeQuadrant(
    title: String,
    body: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title, modifier = modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = body, modifier = modifier.padding(8.dp),
            textAlign = Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Practice_2_task_managerTheme {
        Greeting()
    }
}