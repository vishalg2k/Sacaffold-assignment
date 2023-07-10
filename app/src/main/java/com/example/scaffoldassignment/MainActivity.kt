package com.example.scaffoldassignment

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldassignment.ui.theme.ScaffoldAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAssignmentTheme {
                // A surface container using the 'background' color from the theme
//                startActivity(Intent(applicationContext,DisplayPage::class.java))
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavExample()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting( modifier: Modifier = Modifier,navController: NavController) {
    Scaffold(
        topBar = {
                 TopAppBar( title = {
                     Text(text = "Home Page")
                 })
        },
       content = { h-> MainContent(h,navController,"")},
        bottomBar = {
                    BottomAppBar() {
                        Text(text = "Botom bar")
                    }
        },
        )

}

@Composable
fun NavExample(){
    val navController  = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "Home"){
        composable("Home"){
           Greeting(navController = navController)
        }
        composable("date"){
            DateTimePickerFun(navController = navController)
        }
        composable("lazycol/{a}/{b}/{c}"){
            var name = it.arguments?.getString("a")
            var email = it.arguments?.getString("b")
            var mob = it.arguments?.getString("c")
            Greeting2(name,email,mob,navController)
        }
        composable("returnText/{a}"){
            var text = it.arguments?.getString("a")
            MainContent(padding = PaddingValues(), navController = navController,"$text")
        }
        composable("mainScreen"){
            Greeting3(navController)
        }
        composable("avpage"){
            Greeting4(navController)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainContent(padding : PaddingValues,navController: NavController,value:String) {
    var email by remember {
        mutableStateOf("")
    }
    var mob by remember {
        mutableStateOf("")
    }
    var fullname by remember {
        mutableStateOf("")
    }
    var check by remember {
        mutableStateOf(false)
    }
    var show by remember {
        mutableStateOf(false)
    }
    val ctx = LocalContext.current

    val mediaPlayer = MediaPlayer.create(ctx,R.raw.mouseclicksound)
    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize()
                .consumedWindowInsets(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = fullname,
                onValueChange = { fullname = it },
                label = { Text("Full Name") })
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email address") })
            OutlinedTextField(
                value = mob,
                onValueChange = { mob = it },
                label = { Text("Mobile") })

            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = check, onCheckedChange = { check = it})
                var st = check
                if (st){
                    mediaPlayer.start()
                    st=false
                }
                
                Text(text = "Accept the Terms and Condition to continue")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {



//                Button(onClick = {
//                    show = false
//                    Toast.makeText(ctx,"Values : $fullname - $email - $mob",Toast.LENGTH_LONG).show()
//                }, enabled = check
//                ) {
//                    Text(text = "To")
//                }
                Button(onClick = {
                    navController.navigate("avpage")
                }, enabled = check) {
                    Text(text = "A/V")
                    
                }
//                Button(onClick = { show = true },enabled = check) {
//                    Text(text = "T")
//                }
                Button(onClick = {
                       navController.navigate("lazycol/$fullname/$email/$mob")
                },enabled = check) {
                    Text(text = "LC")
                }
                Button(onClick = {
                    navController.navigate("mainScreen")
                }, enabled = check) {
                    Text(text = "MS")
                }

            }
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
                Text(text = "Return string:$value")
            }
            /*if(show) {
                Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$fullname")
                    Text(text = "$email")
                    Text(text = "$mob")
                }
            }*/

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScaffoldAssignmentTheme {
        NavExample()
    }
}