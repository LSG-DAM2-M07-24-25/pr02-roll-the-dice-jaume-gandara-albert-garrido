package com.example.pr02_roll_the_dice_jaume_gandara_albert_garrido

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import com.example.pr02_roll_the_dice_jaume_gandara_albert_garrido.ui.theme.Pr02rollthedicejaumegandaraalbertgarridoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr02rollthedicejaumegandaraalbertgarridoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainLayout(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainLayout(modifier: Modifier = Modifier) {
    var diceIndex1 by remember { mutableStateOf(1) }
    var diceIndex2 by remember { mutableStateOf(1) }
    var credits by remember { mutableStateOf(10) }
    var botonsDown by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box (
        modifier = modifier.fillMaxSize().padding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.tapestry),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.lsg_logo),
                contentDescription = "Imatge de títol",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Icona credits",
                    tint = Color.Red
                )
                Text(text = "$credits")
            }

            Button(onClick = {
                if (credits > 0 && !botonsDown) {
                    credits -= 1
                    botonsDown = true
                    val handler = Handler(Looper.getMainLooper())

                    val runnable = object : Runnable {
                        override fun run() {
                            diceIndex1 = getRandomDice()
                            diceIndex2 = getRandomDice()
                            handler.postDelayed(this, 100)
                        }
                    }

                    handler.post(runnable)

                    handler.postDelayed({
                        handler.removeCallbacks(runnable)
                        diceIndex1 = getRandomDice()
                        diceIndex2 = getRandomDice()

                        if (diceIndex1 == diceIndex2) {
                            Toast.makeText(context, "JACKPOT!", Toast.LENGTH_SHORT).show()
                            credits += if (diceIndex1 == 6) {
                                10
                            } else {
                                5
                            }
                        }
                        botonsDown = false
                    }, 2000)
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Tirar daus")
            }

            Button(onClick = {
                diceIndex1 = 1
                diceIndex2 = 1
                credits = 10
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Reiniciar joc")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (credits > 1 && !botonsDown) {
                            credits -= 2
                            botonsDown = true
                            val handler = Handler(Looper.getMainLooper())

                            val runnable = object : Runnable {
                                override fun run() {
                                    diceIndex1 = getRandomDice()
                                    handler.postDelayed(this, 100)
                                }
                            }

                            handler.post(runnable)

                            handler.postDelayed({
                                handler.removeCallbacks(runnable)
                                diceIndex1 = getRandomDice()

                                if (diceIndex1 == diceIndex2) {
                                    Toast.makeText(context, "JACKPOT!", Toast.LENGTH_SHORT).show()
                                    credits += if (diceIndex1 == 6) {
                                        10
                                    } else {
                                        5
                                    }
                                }
                                botonsDown = false
                            }, 2000)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                ) {
                    Image(
                        painter = painterResource(id = getDiceImage(diceIndex1)),
                        contentDescription = "Dau 1",
                        modifier = Modifier.size(144.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        if (credits > 1 && !botonsDown) {
                            credits -= 2
                            botonsDown = true
                            val handler = Handler(Looper.getMainLooper())

                            val runnable = object : Runnable {
                                override fun run() {
                                    diceIndex2 = getRandomDice()
                                    handler.postDelayed(this, 100)
                                }
                            }

                            handler.post(runnable)

                            handler.postDelayed({
                                handler.removeCallbacks(runnable)
                                diceIndex2 = getRandomDice()

                                if (diceIndex1 == diceIndex2) {
                                    Toast.makeText(context, "JACKPOT!", Toast.LENGTH_SHORT).show()
                                    credits += if (diceIndex1 == 6) {
                                        10
                                    } else {
                                        5
                                    }
                                }
                                botonsDown = false
                            }, 2000)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                ) {
                    Image(
                        painter = painterResource(id = getDiceImage(diceIndex2)),
                        contentDescription = "Dau 2",
                        modifier = Modifier.size(144.dp)
                    )
                }
            }
        }
    }
}

fun getDiceImage(index: Int): Int {
    return when (index) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }
}

fun getRandomDice(): Int {
    return Random.nextInt(1, 7)
}

@Preview(showBackground = true)
@Composable
fun MainLayoutPreview() {
    Pr02rollthedicejaumegandaraalbertgarridoTheme {
        MainLayout()
    }
}
