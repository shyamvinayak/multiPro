package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.MovieDetails
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.local.Entity.MovieEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun MovieTitle(
    movie: MovieDetails,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    Text(
        text = movie.movie.original_title,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = textAlign,
        modifier = modifier
    )
}

fun extractYear(dateString: String): Int {
    val formatter = SimpleDateFormat("E, MM/dd/yyyy", Locale.US)
    val date = formatter.parse(dateString)
    val calendar = Calendar.getInstance()
    if (date != null) {
        calendar.time = date
    }
    return calendar.get(Calendar.YEAR)
}