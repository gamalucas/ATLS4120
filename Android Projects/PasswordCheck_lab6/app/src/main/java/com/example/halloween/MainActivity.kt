package com.example.halloween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun checkString(input: Editable): String {
        var currentCharacter: Char;
        var numberPresent = false;
        var upperCasePresent = false;
        var lowerCasePresent = false;

        for (i in 0..input.length-1){
            currentCharacter = input[i];
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (Character.isLowerCase(currentCharacter)){
                lowerCasePresent = true;
            }
        }
        if(numberPresent && upperCasePresent && lowerCasePresent == true){
            return "strongPassword"
        }
        else if(numberPresent && upperCasePresent == true){
            return "num&upper"
        }
        else if(upperCasePresent && lowerCasePresent == true){
            return "upper&lower"
        }
        else if(numberPresent && lowerCasePresent == true){
            return "num&lower"
        }
        else if(input.length == 0){
            return ""
        }
        else{
            return "weakPassword"
        }
    }

    fun doCheck(view: View) {
        val imageOnScreen = findViewById<ImageView>(R.id.imageView)
        //EditText
        val editText = findViewById<EditText>(R.id.editText)
        val passValue = editText.text

        //check password security level
        var checkResult = checkString(passValue)

        //Print message - TextView
        val textOnScreen = findViewById<TextView>(R.id.textMessage)
        if(checkResult == "strongPassword"){
            textOnScreen.text = "Strong password for: " + passValue
            imageOnScreen.setImageResource(R.drawable.check)// set ImageView
        }
        else if(checkResult == "num&upper"){
            textOnScreen.text = "Consider adding a lowercase on: " + passValue
            imageOnScreen.setImageResource(R.drawable.weak)// set ImageView
        }
        else if(checkResult == "upper&lower"){
            textOnScreen.text = "Consider adding a number on: " + passValue
            imageOnScreen.setImageResource(R.drawable.weak)// set ImageView
        }
        else if(checkResult == "num&lower"){
            textOnScreen.text = "Consider adding a uppercase on: " + passValue
            imageOnScreen.setImageResource(R.drawable.weak)// set ImageView
        }
        else if(checkResult == "weakPassword"){
            textOnScreen.text = "Weak password for: " + passValue
            imageOnScreen.setImageResource(R.drawable.warning)// set ImageView
        }
        else{
            textOnScreen.text = "Please enter a password!"
        }
    }
}