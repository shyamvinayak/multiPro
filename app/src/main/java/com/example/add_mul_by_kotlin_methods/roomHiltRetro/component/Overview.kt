package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme

@Composable
fun Overview(
    overview: String
) {
    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
        )
    ) {
        Text(
            text = "Overview",
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = overview,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 14.sp,
            lineHeight = 25.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOverView(){
    Overview(overview = "Karen, a single mother, gifts her son Andy a Buddi doll for his birthday, unaware of its more sinister nature. A contemporary re-imagining of the 1988 horror classic.")
}