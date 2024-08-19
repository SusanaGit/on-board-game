package com.example.onboardgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onboardgame.ui.theme.OnBoardGameTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnBoardGameTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardGame()
                }
            }
        }
    }

    @Composable
    fun OnBoardGame() {

        var treasures by remember { mutableStateOf(0)}
        var direction by remember { mutableStateOf("N")}

        var treasure = remember { mutableStateOf(false)}

        var boatX by remember { mutableStateOf(0.dp) }
        var boatY by remember { mutableStateOf(0.dp) }

        // TREASURES AND DIRECTION LETTERS
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {

            Image(
                //<a href="http://www.freepik.com">Designed by Freepik</a>
                painter = painterResource(id = R.drawable.water),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    //<a href="http://www.freepik.com">Designed by macrovector / Freepik</a>
                    painter = painterResource(id = R.drawable.treasure),
                    contentDescription = "treasure image",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.TopEnd),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "${treasures}",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            color = Color.Magenta
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 20.dp)
                    )
                }
            }
        }

        // BOAT
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Image(
                // <a href="http://www.freepik.com">Designed by Freepik</a>
                painter = painterResource(id = R.drawable.boat),
                contentDescription = "boat user",
                modifier = Modifier
                    .size(50.dp)
                    .offset(boatX, boatY)
                    .align(Alignment.Center)
            )

        }

        // BUTTONS
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row() {

                    Button(onClick = {
                        direction = "N"
                        if(Random.nextBoolean()){
                            treasure.value = true
                            treasures += 1
                        } else {
                            treasure.value = false
                        }
                        boatY -= 20.dp
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Go Up",
                        )
                    }
                }

                Row() {

                    Button(
                        onClick = {
                            direction = "L"
                            if (Random.nextBoolean()) {
                                treasure.value = true
                                treasures += 1
                            }
                            boatX -= 20.dp
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Go Left",
                        )
                    }

                    Spacer(modifier = Modifier.width(3.dp))

                    Button(onClick = {
                        direction = "R"
                        if(Random.nextBoolean()){
                            treasure.value = true
                            treasures += 1
                        }
                        boatX += 20.dp
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Go Right",
                        )
                    }
                }

                Row() {
                    Button(onClick = {
                        direction = "S"
                        if(Random.nextBoolean()){
                            treasure.value = true
                            treasures += 1
                        }
                        boatY += 20.dp
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Go Down",
                        )
                    }
                }
            }

            if (treasure.value){
                ShowTreasure(treasure)
            }
        }
    }

    @Composable
    fun ShowTreasure(treasure: MutableState<Boolean>){

        var treasureVisible by remember {
            mutableStateOf(treasure.value)
        }

        if (treasureVisible) {

            LaunchedEffect(key1 = treasure.value) {
                delay(1000L)
                treasureVisible = false
                treasure.value = false
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    //<a href="http://www.freepik.com">Designed by macrovector / Freepik</a>
                    painter = painterResource(id = R.drawable.treasure),
                    contentDescription = "treasure image",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun OnBoardGamePreview(){
        OnBoardGame()
    }
}