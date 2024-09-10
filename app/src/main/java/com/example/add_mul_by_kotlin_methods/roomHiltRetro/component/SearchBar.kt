package com.example.add_mul_by_kotlin_methods.roomHiltRetro.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    active: MutableState<Boolean>,
    searchQuery: MutableState<String>,
    onChangeValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClear: (Boolean) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        query = searchQuery.value,
        onQueryChange = {
            searchQuery.value = it
            onChangeValue(it)
        },
        onSearch = {
            active.value = false
        },
        active = active.value,
        onActiveChange = {
            active.value = it
        },
        placeholder = {
            Text(text = "Enter your query")
        },
        trailingIcon = {
            if (active.value) {
                Icon(
                    modifier = Modifier.clickable {
                        if (searchQuery.value.isNotEmpty()) {
                            searchQuery.value = ""
                        } else {
                            active.value = false
                        }
                        onClear(searchQuery.value.isEmpty())
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close icon"
                )
            } else {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }

        },
        colors = SearchBarDefaults.colors(
            MaterialTheme.colorScheme.inverseOnSurface,
            inputFieldColors = TextFieldDefaults.colors(MaterialTheme.colorScheme.onBackground)
        ),
        content = {
            content()
        }
    )

}