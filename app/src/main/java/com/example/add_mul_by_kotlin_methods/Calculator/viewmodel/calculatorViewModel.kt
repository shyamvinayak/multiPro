package com.example.add_mul_by_kotlin_methods.Calculator.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.add_mul_by_kotlin_methods.Calculator.model.History

class CalculatorViewModel : ViewModel() {

    val buttonList = listOf(
        "C",
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "=", "+"
    )
    private var _enterValue = MutableLiveData("")
    private var _history = MutableLiveData<List<History>>(emptyList())
    private var expressionResult:String = ""

    val enterValue: LiveData<String> get() = _enterValue
    val history:LiveData<List<History>> get() = _history


    private val operators = setOf("+", "-", "*", "/")


    fun updateEnteredValue(newValue: String) {
        _enterValue.value = (_enterValue.value ?: "") + newValue
    }


    fun clearValue() {
        _enterValue.value = ""
    }
    fun clearHistory() {
        _history.value = emptyList()
    }

    fun handleOperator(operator: String) {
        _enterValue.value = (_enterValue.value ?: "") + operator
    }

    private fun addHistory(){
        val newEntry = History(expressionResult)
        val updateList = _history.value.orEmpty()+newEntry
        _history.value =  updateList
    }

    private fun lastOrNull(value: String?):String{
        return value?.lastOrNull().toString()
    }
    private fun firstOrNull(value: String?):String{
        return value?.firstOrNull().toString()
    }
    private  fun isContain(item:Set<String>,value:String):Boolean{
        return item.contains(value)
    }


    fun onEqualClick() {
        if (_enterValue.value!!.isNotEmpty()) {
            val expr = if (isContain(operators,lastOrNull(_enterValue.value))) {
                _enterValue.value + 0
            } else if (isContain(operators,firstOrNull(_enterValue.value))) {
                _enterValue.value = ""
                return
            } else {
                _enterValue.value
            }

            val result = calculateExpression("$expr")
            _enterValue.value = result
            expressionResult = "$expr=$result"
            addHistory()

        }

    }

    private fun calculateExpression(expression: String): String {
        return try {
            val operations: Map<String, (Double, Double) -> Double> = mapOf(
                "+" to { a, b -> a + b },
                "-" to { a, b -> a - b },
                "*" to { a, b -> a * b },
                "/" to { a, b -> a / b }
            )

            val operator = operations.keys.find { expression.contains(it) }

            if (operator != null) {
                val (left, right) = expression.split(operator)
                val leftOperand = left.toDouble()
                val rightOperand = right.toDouble()
                val result = operations[operator]?.invoke(leftOperand, rightOperand)
                result.toString()
            } else {
                "Error"
            }
        } catch (e: Exception) {
            "Error"
        }
    }

}