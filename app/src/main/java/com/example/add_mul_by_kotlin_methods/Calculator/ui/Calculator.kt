package com.example.add_mul_by_kotlin_methods.Calculator.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.add_mul_by_kotlin_methods.Calculator.viewmodel.CalculatorViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.style.TextAlign
import com.example.add_mul_by_kotlin_methods.Calculator.model.History
import com.example.add_mul_by_kotlin_methods.Calculator.ui.buttons.CalculatorSingleButton

@Composable
fun Calculator(viewModel: CalculatorViewModel = viewModel()) {
    val enterValue by viewModel.enterValue.observeAsState("0")
    val history by viewModel.history.observeAsState(emptyList())
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start) // Aligns this text to the start (left side)
            ) {
                Text(
                    text = "Calculator",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.End)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(history) { entry -> HistoryItem(history = entry,
                        onClick = {
                        viewModel.updateEnteredValue(entry.data)
                    }) }
                }
            }

            Spacer(Modifier.height(5.dp))
            Text(
                text = enterValue,
                fontSize = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(Modifier.height(20.dp))
            CalculatorButton(viewModel.buttonList, viewModel)

        }
    }


}

@Composable
fun HistoryItem(history: History, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable(
                onClick = { onClick() }
            ),
        shape = MaterialTheme.shapes.medium
    ) {
        Surface(modifier = Modifier.padding(10.dp), color = Color.Transparent) {
            Text(text = history.data, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun CalculatorButton(buttonList: List<String>, viewModel: CalculatorViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(buttonList) { button ->
                    CalculatorSingleButton(button,
                        onClick = {
                            when (button) {
                                "=" -> {
                                    viewModel.onEqualClick()
                                }

                                "C" -> {
                                    viewModel.clearValue()
                                }

                                in listOf("+", "-", "*", "/") -> {
                                    viewModel.handleOperator(button)
                                }

                                else -> {
                                    viewModel.updateEnteredValue(button)
                                }

                            }
                        },
                        onLongClick = {
                            if (button == "C")
                                viewModel.clearHistory()
                        }
                    )
                }
            }
        }
    }
}







