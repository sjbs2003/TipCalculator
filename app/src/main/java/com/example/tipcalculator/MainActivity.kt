package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var costOfService: EditText
    private lateinit var serviceQuestions: TextView
    private lateinit var tipOptions: RadioGroup
    private lateinit var roundTipSwitch: Switch
    private lateinit var calculateButton: Button
    private lateinit var tipResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        costOfService = findViewById(R.id.Tip)
        serviceQuestions = findViewById(R.id.Service_Questions)
        tipOptions = findViewById(R.id.Tip_options)
        roundTipSwitch = findViewById(R.id.Round_tip)
        calculateButton = findViewById(R.id.Calculate_Button)
        tipResult = findViewById(R.id.Tip_result)

        calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        // Get the cost of service from the EditText
        val cost = costOfService.text.toString().toDoubleOrNull()

        if (cost == null) {
            // If the input is invalid, show an error message
            Toast.makeText(this, "Please enter a valid cost of service", Toast.LENGTH_SHORT).show()
            return
        }

        // Get the selected tip percentage from the RadioGroup
        val tipPercentage = when (tipOptions.checkedRadioButtonId) {
            R.id.Option_10percent -> 0.10
            R.id.Option_7percent -> 0.07
            R.id.Option_5percent -> 0.05
            else -> 0.0
        }

        // Calculate the tip amount
        var tip = cost * tipPercentage

        // Round up the tip amount if the switch is on
        if (roundTipSwitch.isChecked) {
            tip = ceil(tip)
        }

        // Display the tip amount in the result TextView
        tipResult.text = getString(R.string.tip_amount, tip)
    }
}
