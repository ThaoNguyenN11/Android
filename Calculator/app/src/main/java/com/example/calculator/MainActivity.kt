package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operation: String = ""
    private var isResultShown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextText)

        val button0: Button = findViewById(R.id.button19)
        val button1: Button = findViewById(R.id.button14)
        val button2: Button = findViewById(R.id.button15)
        val button3: Button = findViewById(R.id.button16)
        val button4: Button = findViewById(R.id.button10)
        val button5: Button = findViewById(R.id.button11)
        val button6: Button = findViewById(R.id.button12)
        val button7: Button = findViewById(R.id.button6)
        val button8: Button = findViewById(R.id.button7)
        val button9: Button = findViewById(R.id.button8)

        val buttonAdd: Button = findViewById(R.id.button17)
        val buttonSub: Button = findViewById(R.id.button13)
        val buttonMul: Button = findViewById(R.id.button9)
        val buttonDiv: Button = findViewById(R.id.button4)
        val buttonEqual: Button = findViewById(R.id.button21)
        val buttonClear: Button = findViewById(R.id.button2)

        // Set up number buttons
        val numberButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        for (button in numberButtons) {
            button.setOnClickListener {
                if (isResultShown) {
                    editText.setText("")
                    isResultShown = false
                }
                val currentText = editText.text.toString()
                editText.setText(currentText + button.text.toString())
            }
        }

        // Set up operation buttons
        buttonAdd.setOnClickListener { setOperation("+") }
        buttonSub.setOnClickListener { setOperation("-") }
        buttonMul.setOnClickListener { setOperation("*") }
        buttonDiv.setOnClickListener { setOperation("/") }

        // Equals button - perform the operation
        buttonEqual.setOnClickListener { calculateResult() }

        // Clear button
        buttonClear.setOnClickListener {
            editText.setText("")
            operand1 = 0.0
            operand2 = 0.0
            operation = ""
            isResultShown = false
        }
    }

    private fun setOperation(op: String) {
        if (editText.text.toString().isNotEmpty()) {
            operand1 = editText.text.toString().toDoubleOrNull() ?: 0.0
            operation = op
            val currentText = editText.text.toString()
            editText.setText("$currentText $op ") // Hiển thị phép toán với toán tử
        }
    }

    private fun calculateResult() {
        val expression = editText.text.toString()
        val parts = expression.split(" ")

        if (parts.size == 3) {
            operand1 = parts[0].toDoubleOrNull() ?: 0.0
            operation = parts[1]
            operand2 = parts[2].toDoubleOrNull() ?: 0.0
        }

        val result = when (operation) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> if (operand2 != 0.0) operand1 / operand2 else 0.0
            else -> 0.0
        }

        // Hiển thị cả phép tính và kết quả
        editText.setText("$expression = $result")
        isResultShown = true
    }
}
