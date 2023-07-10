package com.example.scaffoldassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldassignment.ui.theme.ScaffoldAssignmentTheme

class MainScreenText : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting3(rememberNavController())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting3(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier= Modifier.fillMaxWidth()) {
        var text by remember {
            mutableStateOf("")
        }
        OutlinedTextField(value = text, onValueChange = {text = it}, label = { Text(text = "Text For Home Screen")})
        Button(onClick = {
            navController.navigate("returnText/$text")
        }) {
            Text(text = "Click to check Text on Home Screen")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ScaffoldAssignmentTheme {
        Greeting3(rememberNavController())
    }
}