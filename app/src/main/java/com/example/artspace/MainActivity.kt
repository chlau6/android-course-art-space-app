package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var artSequence by remember { mutableStateOf(0) }

        val artwork = when (artSequence) {
            0 -> R.drawable.alex_chief_role
            1 -> R.drawable.anne_france
            2 -> R.drawable.berkeley_county_exterior
            3 -> R.drawable.denise_at_the_dalles
            4 -> R.drawable.john_executes_programs
            5 -> R.drawable.mike_at_the_dalles
            else -> R.drawable.st_ghislain_cooling_towers
        }

        val title = when (artSequence) {
            0 -> R.string.title_0
            1 -> R.string.title_1
            2 -> R.string.title_2
            3 -> R.string.title_3
            4 -> R.string.title_4
            5 -> R.string.title_5
            else -> R.string.title_6
        }

        val author = when (artSequence) {
            0 -> R.string.author_0
            1 -> R.string.author_1
            2 -> R.string.author_2
            3 -> R.string.author_3
            4 -> R.string.author_4
            5 -> R.string.author_5
            else -> R.string.author_6
        }

        val year = when (artSequence) {
            0 -> R.string.year_0
            1 -> R.string.year_1
            2 -> R.string.year_2
            3 -> R.string.year_3
            4 -> R.string.year_4
            5 -> R.string.year_5
            else -> R.string.year_6
        }

        val description = when (artSequence) {
            0 -> R.string.description_0
            1 -> R.string.description_1
            2 -> R.string.description_2
            3 -> R.string.description_3
            4 -> R.string.description_4
            5 -> R.string.description_5
            else -> R.string.description_6
        }

        ArtworkWall(artwork = artwork, description = description)
        Spacer(modifier = Modifier.height(100.dp))
        ArtworkDescriptor(title = title, author = author, year = year)
        Spacer(modifier = Modifier.height(20.dp))
        DisplayController(previousAction = { artSequence = (artSequence + 6) % 7 },
            nextAction = { artSequence = (artSequence + 1) % 7 })
    }
}

@Composable
fun ArtworkWall(artwork: Int, description: Int) {
    Card(modifier = Modifier,
        elevation = 10.dp
    ) {
        Image(
            modifier = Modifier
                .border(width = 2.dp, color = Color.Gray)
                .padding(
                    20.dp
                ),
            painter = painterResource(artwork),
            contentDescription = stringResource(description),
        )
    }
}

@Composable
fun ArtworkDescriptor(title: Int, author: Int, year: Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .wrapContentSize(),
        elevation = 10.dp) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(title))

            Spacer(modifier = Modifier.height(10.dp))

            Row() {
                Text(text = stringResource(author), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = stringResource(year), color = Color.Gray)
            }
        }
    }
}

@Composable
fun DisplayController(previousAction: () -> Unit, nextAction: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.width(120.dp), onClick = previousAction
        ) {
            Text(text = "Previous")
        }

        Button(
            modifier = Modifier.width(120.dp), onClick = nextAction
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}