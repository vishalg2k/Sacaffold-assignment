package com.example.scaffoldassignment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldassignment.ui.theme.ScaffoldAssignmentTheme
import java.util.Calendar

class DateTimeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DateTimePickerFun(rememberNavController())
                }
            }
        }
    }
}

@Composable
fun DateTimePickerFun(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Button(onClick = {
            navController.navigate("Home")
        }) {
            Text(text = "Click for home screen")
        }


        var timeNow by remember {
            mutableStateOf("")
        }
        var dateNow by remember {
            mutableStateOf("")
        }
        val ctx = LocalContext.current

        val cal = Calendar.getInstance()
        val hr = cal[Calendar.HOUR_OF_DAY]
        val min = cal[Calendar.MINUTE]

        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val dayOfMonth = cal[Calendar.DAY_OF_MONTH]

        val timePicker = TimePickerDialog(ctx,
            {_,hr:Int, min:Int -> timeNow = "$hr:$min"},
            hr,
            min,
            false)

        val datePicker = DatePickerDialog(ctx,
            { _:DatePicker , selectedYear:Int, selectedMonth:Int,
              selectedDayOfMonth:Int -> dateNow = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"},
            year,
            month,
            dayOfMonth)


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Selected Time is : $timeNow ")
            Button(onClick = { timePicker.show() }) {
                Text(text = "Select Time")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Selected Date is : $dateNow ")
            Button(onClick = {datePicker.show()}) {
                Text(text = "Select Date")
            }
        }


    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview3() {
//    ScaffoldAssignmentTheme {
//        DateTimePickerFun(rememberNavController())
//    }
//}