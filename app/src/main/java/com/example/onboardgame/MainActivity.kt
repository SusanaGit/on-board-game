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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onboardgame.ui.theme.OnBoardGameTheme
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

        val treasures = remember { mutableStateOf(0)}
        val direction = remember { mutableStateOf("Up")}

        val treasure = remember { mutableStateOf(false)}

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {

            Image(
                //<a href="http://www.freepik.com">Designed by Freepik</a>
                painter = painterResource(id = R.drawable.water),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                Text(text = "Treasures: ${treasures.value}")
                Text(text = "Direction: ${direction.value}")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
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
                        direction.value = "Up"
                        if(Random.nextBoolean()){
                            treasure.value = true
                            treasures.value += 1

                        } else {
                            treasure.value = false

                        }
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
                            direction.value = "Left"
                            if (Random.nextBoolean()) {
                                treasure.value = true
                                treasures.value += 1
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Go Left",
                        )
                    }

                    Spacer(modifier = Modifier.width(3.dp))

                    Button(onClick = {
                        direction.value = "Right"
                        if(Random.nextBoolean()){
                            treasure.value = true
                            treasures.value += 1
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Go Right",
                        )
                    }
                }

                Row() {
                    Button(onClick = {
                        direction.value = "Down"
                        if(Random.nextBoolean()){
                            treasure.value = true
                            treasures.value += 1
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Go Down",
                        )
                    }
                }
            }

            if (treasure.value){
                ShowTreasure()
                treasure.value = false
            }
        }
    }

    @Composable
    fun ShowTreasure(){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.treasure),
                contentDescription = "treasure image",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun OnBoardGamePreview(){
        OnBoardGame()
    }
}