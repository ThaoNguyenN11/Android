package com.example.currencyconverter

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sourceAmount: EditText
    private lateinit var targetAmount: EditText
    private lateinit var sourceCurrency: Spinner
    private lateinit var targetCurrency: Spinner

    private val exchangeRates = mapOf(
        "USD" to 1.0,  // 1 USD = 1 USD
        "EUR" to 0.85, // 1 USD = 0.85 EUR
        "VND" to 23000.0, // 1 USD = 23000 VND
        "JPY" to 110.0, // 1 USD = 110 JPY
        "GBP" to 0.75  // 1 USD = 0.75 GBP
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sourceAmount = findViewById(R.id.sourceAmount)
        targetAmount = findViewById(R.id.targetAmount)
        sourceCurrency = findViewById(R.id.sourceCurrency)
        targetCurrency = findViewById(R.id.targetCurrency)

        val currencies = exchangeRates.keys.toList()

        val sourceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceCurrency.adapter = sourceAdapter

        val targetAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        targetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        targetCurrency.adapter = targetAdapter

        sourceAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertCurrency()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        sourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        targetCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        setUpNumberButtons()
        setUpFunctionButtons()
    }

    private fun setUpNumberButtons() {
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        button0.setOnClickListener { appendToSourceAmount("0") }
        button1.setOnClickListener { appendToSourceAmount("1") }
        button2.setOnClickListener { appendToSourceAmount("2") }
        button3.setOnClickListener { appendToSourceAmount("3") }
        button4.setOnClickListener { appendToSourceAmount("4") }
        button5.setOnClickListener { appendToSourceAmount("5") }
        button6.setOnClickListener { appendToSourceAmount("6") }
        button7.setOnClickListener { appendToSourceAmount("7") }
        button8.setOnClickListener { appendToSourceAmount("8") }
        button9.setOnClickListener { appendToSourceAmount("9") }
        buttonDot.setOnClickListener { appendToSourceAmount(".") }
    }

    private fun setUpFunctionButtons() {
        val buttonClear: Button = findViewById(R.id.buttonX)
        val buttonCE: Button = findViewById(R.id.buttonCE)

        buttonClear.setOnClickListener { removeLastCharacter() }
        buttonCE.setOnClickListener { clearSourceAmount() }
    }

    private fun appendToSourceAmount(value: String) {
        sourceAmount.append(value)
        convertCurrency()
    }

    private fun removeLastCharacter() {
        val currentText = sourceAmount.text.toString()
        if (currentText.isNotEmpty()) {
            sourceAmount.setText(currentText.substring(0, currentText.length - 1))
            sourceAmount.setSelection(sourceAmount.text.length)
            convertCurrency()
        }
    }

    private fun clearSourceAmount() {
        sourceAmount.text.clear()
        targetAmount.text.clear()
    }

    @SuppressLint("DefaultLocale")
    private fun convertCurrency() {
        val amount = sourceAmount.text.toString().toDoubleOrNull()
        if (amount != null) {
            val sourceCurrencyValue = exchangeRates[sourceCurrency.selectedItem]
            val targetCurrencyValue = exchangeRates[targetCurrency.selectedItem]

            if (sourceCurrencyValue != null && targetCurrencyValue != null) {
                // Tính toán số tiền được chuyển đổi
                val convertedAmount = amount * (targetCurrencyValue / sourceCurrencyValue)
                targetAmount.setText(String.format("%.2f", convertedAmount))
            }
        } else {
            targetAmount.setText("")
        }
    }
}
