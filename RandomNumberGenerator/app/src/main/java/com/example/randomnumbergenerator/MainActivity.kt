package com.example.randomnumbergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var button : Button
    lateinit var checkBox : CheckBox
    lateinit var result : TextView
    lateinit var lowerBound : EditText
    lateinit var upperBound : EditText

    private val list = mutableListOf<Int>()
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.get_number)
        checkBox = findViewById(R.id.checkbox)
        result = findViewById(R.id.result)
        lowerBound = findViewById(R.id.lower_bound)
        upperBound = findViewById(R.id.upper_bound)

        setListeners()
    }

    private fun setListeners() {
        button.setOnClickListener{generateRandomNumber(lowerBound, upperBound, result, checkBox)}
        checkBox.setOnClickListener{clearEntry(lowerBound, upperBound, result)}
    }

    private fun generateRandomNumber(lowerBound: EditText, upperBound: EditText, result: TextView, checkBox: CheckBox) {

        if(result.text.isNotBlank() && list.isEmpty()) {
            updateRange(lowerBound, upperBound)
        }
        else if(list.isEmpty() && result.text.isBlank()) updateRange(lowerBound,upperBound)

        if(!checkBox.isChecked){
            result.setText("${randomize(list.first(), list.last())}")
        } else {

            var randomResult = randomize(list.first(), list.last())
            result.text = "$randomResult"
            list.remove(randomResult)
            if (list.isEmpty()) {
                resetRange(lowerBound,upperBound)
                result.text="$randomResult \n Random results exhausted \n Last number shown"
            }
        }
    }

    private fun clearEntry(lowerBound: EditText, upperBound: EditText, result: TextView){
        result.text = ""
        list.clear()
        lowerBound.text.clear()
        upperBound.text.clear()
    }

    private fun updateRange(lowerBound: EditText, upperBound: EditText) {
        val lower = lowerBound.text.toString().toInt()
        val upper = upperBound.text.toString().toInt()
        for (i in lower..upper) list.add(i)
    }

    private fun resetRange(lowerBound:EditText,upperBound:EditText){
        list.clear()
        lowerBound.text.clear()
        upperBound.text.clear()
    }

    private fun randomize(low: Int, high: Int) = random.nextInt(high - low + 1) + low

}
