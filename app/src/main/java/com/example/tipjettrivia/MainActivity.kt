package com.example.tipjettrivia

import BillEntry
import TopHeader
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.tipjettrivia.ui.theme.TipJetTriviaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                //TopHeader()

                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val splitState = remember {
        mutableStateOf(1)
    }
    val tipAmounntState = remember {
        mutableStateOf(0.0)
    }

    val tipPerPersonState = remember {
        mutableStateOf(0.0)
    }
    TipJetTriviaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column() {
                TopHeader(TipsMoney = tipPerPersonState.value)
                BillEntry(
                    splitState = splitState,
                    tipAmounntState = tipAmounntState,
                    tipPerPersonState = tipPerPersonState,
                )
            }

        }
    }
}


