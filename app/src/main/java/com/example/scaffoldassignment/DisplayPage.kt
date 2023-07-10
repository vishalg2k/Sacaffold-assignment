package com.example.scaffoldassignment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

class DisplayPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("","","",rememberNavController())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting2(name: String?, email: String?, number: String?,navController: NavController) {
    var notf by remember {
        mutableStateOf(false)
    }

    Column( modifier = Modifier.fillMaxSize()) {
//        Text(text = "Hi")
        Button(onClick = {
            navController.navigate("date")
        }) {
            Text(text = "Click for date screen")
            
        }
        
        Box( modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Column() {
                Text("$name")
                Text("$email")
                Text("$number")
            }
        }
        
        Button(onClick = {
            navController.navigate("Home")
        }) {
            Text(text = "Click for home screen")
        }
//
//        Button(onClick = {
//                navController.navigate("Home") }) {
//                Text(text = "Go to Greeting 2 screen")
//            }
    }
}

@Composable
fun ForNotification(applicationContext: Context) {
//    val ctx = LocalContext.current
    var notificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val notificationChannel =
//            NotificationChannel(
//                "101", "channel",

//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//        notificationManager.createNotificationChannel(notificationChannel)
//    }
//        var inte = Intent(applicationContext, DisplayPage::class.java)
//        var pendin = PendingIntent.getActivity(
//            applicationContext,
//            0,
//            inte,
//            PendingIntent.FLAG_IMMUTABLE
//        )

    val notificationBuilder = NotificationCompat.Builder(applicationContext)
        .setContentTitle("Hi new")
        .setContentText("this is to notify")
//        .setContentIntent(pendin)
        .setSmallIcon(R.drawable.ic_android_black_24dp)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val next = NotificationChannel("1", "Hi", NotificationManager.IMPORTANCE_HIGH)
    }



    notificationManager.notify(1, notificationBuilder.build())
    notificationManager.notify(2, notificationBuilder.build())

}


//@Composable
//fun NavExample2(){
//    val navController2  = rememberNavController()
//
//    NavHost(
//        navController = navController2,
//        startDestination = "Home"){
//        composable("date"){
//            DateTimePickerFun(navController = navController2)
//        }
//    }
//
//}


@Composable
fun Greeting(name: String?){
    Text(text = "$name")
}
