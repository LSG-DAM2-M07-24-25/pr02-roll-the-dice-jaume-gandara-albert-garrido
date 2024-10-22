package com.example.pr02_roll_the_dice_jaume_gandara_albert_garrido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
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

        // Botó principal
        Button(onClick = { /* Acció del botó */ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Botó Principal")
        }

        // TODO: Afegir les imatges dels daus
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                diceIndex1 = getRandomDice()
            }, modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = getDiceImage(diceIndex1)),
                    contentDescription = "Dau 1",
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                diceIndex2 = getRandomDice()
            }, modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = getDiceImage(diceIndex2)),
                    contentDescription = "Dau 2",
                    modifier = Modifier.size(48.dp)
                )
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
