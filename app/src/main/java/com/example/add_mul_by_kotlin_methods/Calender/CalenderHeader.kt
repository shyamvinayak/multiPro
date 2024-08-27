package com.example.add_mul_by_kotlin_methods.Calender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CalenderHeader (){

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        ){
        Text(
            text = getCurrentDate(),//Saturday 20, May 2023
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }

}

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("EEEE dd, MMMM yyyy", Locale.getDefault())
    return dateFormat.format(calendar.time)
}