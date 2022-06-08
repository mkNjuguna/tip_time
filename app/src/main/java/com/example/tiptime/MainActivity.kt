package com.example.tiptime

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //This line declares a top-level variable in the class for the binding object.

    // It's defined at this level because it will be used across multiple methods in MainActivity class.
    //lateinit - a promise that your code will initialize the variable before using it. If you don't, your app will crash.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {

        val stringInTextField =
            binding.costOfService.text.toString() //Chaining - getting text input from costofservice edittext element
        val cost = stringInTextField.toDoubleOrNull() // converting the string to decimal format
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val tipPercentage =
            when (binding.tipOptions.checkedRadioButtonId) { //checks the chosen radio button and is referrenced by id
                R.id.option_twenty_percent -> 0.20
                R.id.option_ten_percent -> 0.10
                else -> 0.05
            }

        var tip = cost * tipPercentage


        if (binding.roundUpSwitch.isChecked ) { //Referencing the switch & checking if the switch has been checked
            tip = kotlin.math.ceil(tip) //Rounding up the calculated tip
        }
        val formattedTip = getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(
            R.string.tip_amount,
            formattedTip
        ) //Asigning the formatted tip to the textview
    }
}