package com.example.add_mul_by_kotlin_methods.basicMath


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.add_mul_by_kotlin_methods.ui.theme.MariAppTheme


// Inline Function
inline fun inlineOperation(operation: (Int, Int) -> Int, a: Int, b: Int): Int {
    return operation(a, b)
}


// Lambda Function
val lambdaSum: (Int, Int) -> Int = { a, b -> a + b }
val lambdaMultiply: (Int, Int) -> Int = { a, b -> a * b }

// Higher-Order Function
fun higherOrderOperation(operation: (Int, Int) -> Int, a: Int, b: Int): Int {
    return operation(a, b)
}

// Infix Function
infix fun Int.infixMultiply(other: Int): Int {
    return this * other
}

infix fun Int.infixSum(other: Int): Int {
    return this + other
}

@Composable
fun ShowListContent(text: String, modifier: Modifier = Modifier) {
    val selectedOption = remember { mutableStateOf("Lambda Function") }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(modifier = modifier) {
            Text(
                text = text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        CommonText("First Value")
        val textValue1 = remember { mutableStateOf(TextFieldValue()) }
        CommonTextField(textValue1, "Enter first value")
        Spacer(modifier = Modifier.height(20.dp))
        CommonText("Second Value")
        val textValue2 = remember { mutableStateOf(TextFieldValue()) }
        CommonTextField(textValue2, "Enter second value")
        Spacer(modifier = Modifier.height(20.dp))
        CommonText("Predict the result of the addition")
        val textAdd = remember { mutableStateOf(TextFieldValue()) }
        CommonTextField(textAdd, "Enter predicted addition value")
        Spacer(modifier = Modifier.height(20.dp))
        CommonText("Predict the result of the multiplication")
        val textMul = remember { mutableStateOf(TextFieldValue()) }
        CommonTextField(textMul, " Enter predicted multiplied value")
        Spacer(modifier = Modifier.height(20.dp))
        ListItems( selectedOption,textValue1,textValue2,textAdd,textMul)

    }
}

@Composable
fun ListItems(
    selectedOption: MutableState<String>,
    textValue1: MutableState<TextFieldValue>,
    textValue2: MutableState<TextFieldValue>,
    addPredict: MutableState<TextFieldValue>,
    mulPredict: MutableState<TextFieldValue>,
) {
    val num1 = textValue1.value.text.toIntOrNull() ?: 0
    val num2 = textValue2.value.text.toIntOrNull() ?: 0
    val addPro = addPredict.value.text.toIntOrNull()?:0
    val mulPro = mulPredict.value.text.toIntOrNull()?:0
    val resultAddition = remember { mutableStateOf(0) }
    val resultMultiplication = remember { mutableStateOf(0) }


    val operations = mapOf(
        "Lambda Function" to {
            resultAddition.value = lambdaSum(num1, num2)
            resultMultiplication.value = lambdaMultiply(num1, num2)
        },
        "Higher-Order Function" to {
            resultAddition.value = higherOrderOperation(lambdaSum, num1, num2)
            resultMultiplication.value = higherOrderOperation(lambdaMultiply, num1, num2)
        },
        "Infix Function" to {
            resultAddition.value = num1 infixSum num2
            resultMultiplication.value = num1 infixMultiply num2
        },
        "Inline Function" to {
            resultAddition.value = inlineOperation(lambdaSum, num1, num2)
            resultMultiplication.value = inlineOperation(lambdaMultiply, num1, num2)
        }
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == "Lambda Function",
                onClick = { selectedOption.value = "Lambda Function" }
            )
            Text("Lambda Function", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))

            RadioButton(
                selected = selectedOption.value == "Higher-Order Function",
                onClick = { selectedOption.value = "Higher-Order Function" }
            )
            Text("Higher-Order Function", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == "Infix Function",
                onClick = { selectedOption.value = "Infix Function" }
            )
            Text("Infix Function", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))

            RadioButton(
                selected = selectedOption.value == "Inline Function",
                onClick = { selectedOption.value = "Inline Function" }
            )
            Text("Inline Function", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
             operations[selectedOption.value]?.invoke()

        }) {
            Text("Compute", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
        }

        Spacer(modifier = Modifier.height(5.dp))
        if(resultAddition.value != 0){
            if(resultAddition.value == addPro){
                Text("The prediction for addition was correct.The output is ${resultAddition.value} and your" +
                        "prediction is ${addPro}", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
            }else{
                Text("The prediction for addition was incorrect.The output is ${resultAddition.value} and your" +
                        "prediction is ${addPro}", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        if(resultMultiplication.value!=0){
            if(resultMultiplication.value == mulPro){
                Text("The prediction for multiplication was correct.The output is ${resultMultiplication.value} and your" +
                        "prediction is ${mulPro}", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
            }else{
                Text("The prediction for multiplication was incorrect.The output is ${resultMultiplication.value} and your" +
                        "prediction is ${mulPro}", style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
            }
        }
    }
}

@Composable
fun CommonText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.onPrimary
    )
}


@Composable
fun CommonTextField(text: MutableState<TextFieldValue>, label: String) {
    TextField(
        value = text.value,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        placeholder = { Text(label) },
        trailingIcon = {
            Icon(
                Icons.Filled.Clear,
                contentDescription = "Clear",
                modifier = Modifier
                    .offset(x = 10.dp)
                    .clickable {
                        //just send an update that the field is now empty
                        text.value = TextFieldValue("")
                    }
                    .padding(
                        end = 20.dp
                    )
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.Transparent,
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.Gray,
            disabledContainerColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color.White,
            unfocusedPlaceholderColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { text.value = it })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MariAppTheme {
        ShowListContent("Android", Modifier.padding(all = 20.dp))
    }
}
