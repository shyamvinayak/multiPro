package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.add_mul_by_kotlin_methods.R
import com.example.add_mul_by_kotlin_methods.roomHiltRetro.domain.Cast

@Composable
fun Cast(list: List<Cast>, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val uniqueCastList = removeDuplicates(list)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(uniqueCastList.size) { index ->
            uniqueCastList[index].let{cast->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 10.dp)
                        .clickable {
                            openSearchEngine(context,cast.name)
                        }
                ) {

                    AsyncImage(
                        model = cast.profile_path,
                        contentDescription = "",
                        placeholder = painterResource(id = R.drawable.dummy),
                        error = painterResource(id = R.drawable.dummy),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )

                    Text(
                        cast.name,
                        modifier = Modifier.padding(top = 16.dp),
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }
        }
    }
}

fun removeDuplicates(castList: List<Cast>): List<Cast> {
    return castList.distinctBy { it.id } // Use distinctBy for uniqueness based on id
}

private fun openSearchEngine(context: Context, personName: String) {
    val queryUrl = "https://www.google.com/search?q=${Uri.encode(personName)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl))
    context.startActivity(intent)

}