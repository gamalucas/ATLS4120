package com.example.translator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import android.widget.ArrayAdapter
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    var message: String = ""
    var languageId = -1
    var setColor = false
    lateinit var layoutRoot : ConstraintLayout
    lateinit var spinner: Spinner
    lateinit var translationMsgTxtView: TextView
    lateinit var radioGroup: RadioGroup
    lateinit var capSwitch: Switch
    lateinit var checkBoxHi: CheckBox
    lateinit var checkBoxHowAreYou: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get view items


//        val spinnerValues = arrayOf("Dog", "Cat")
//        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
//        spinner.adapter = arrayAdapter
//        spinner.onItemSelectedListener = object :
        //  AdapterView.OnItemSelectedListener


        layoutRoot = findViewById(R.id.root_layout)
        spinner = findViewById<Spinner>(R.id.spinner)
        translationMsgTxtView = findViewById<TextView>(R.id.textView)
        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        capSwitch = findViewById<Switch>(R.id.switch1)
        checkBoxHi = findViewById<CheckBox>(R.id.checkBox)
        checkBoxHowAreYou = findViewById<CheckBox>(R.id.checkBox2)
    }

    fun translate(view: View) {
        var addOnHi = ""
        var addOnHow = ""
        var spinnerValue = ""
        var language: CharSequence = ""
        var addOnHiPT = ""
        var addOnHiIT = ""
        var addOnHowPT = ""
        var addOnHowIT = ""
        //check language selection
        languageId = radioGroup.checkedRadioButtonId
        if(languageId == -1){
            //Snackbar
            val alertSnackbar = Snackbar.make(layoutRoot, "Please select a language", Snackbar.LENGTH_SHORT)
            alertSnackbar.show()
        } else if(languageId != -1){
            language = findViewById<RadioButton>(languageId).text

            //check for checkboxes
            if(checkBoxHi.isChecked){
                addOnHi += checkBoxHi.text
                addOnHiPT = "Oi"
                addOnHiIT = "Ciao"
            }
            if(checkBoxHowAreYou.isChecked){
                addOnHow += ", " + checkBoxHowAreYou.text +"?"
                addOnHowPT = ", como você vai?"
                addOnHowIT = ", como va?"
            }

            //spinner
            spinnerValue = " " + spinner.selectedItem

            //Translate
            //spinnerValue = "Dog"
            //translationMsgTxtView.text = spinnerValue //FIX THIS
            if(language == "Portuguese"){
                if(spinnerValue == " Dog"){
                    message = addOnHi + " " + spinnerValue + addOnHow + " = " + addOnHiPT + " cachorro " + addOnHowPT
                }
                else if(spinnerValue == " Cat"){
                    message = addOnHi + " " + spinnerValue + addOnHow + " = " + addOnHiPT + " gato " + addOnHowPT
                }
            }
            else if(language == "Italian"){
                if(spinnerValue == " Dog"){
                    message = addOnHi + " " + spinnerValue + addOnHow + " = " + addOnHiIT + " canne " + addOnHowIT
                }
                else if(spinnerValue == " Cat"){
                    message = addOnHi + " " + spinnerValue + addOnHow + " = " + addOnHiIT + " gatto " + addOnHowIT
                }
            }

            //chek for switch
            if(capSwitch.isChecked){
                setColor = true
            }
            else {
                setColor = false
            }
            translationMsgTxtView.text = message
            updateUI(setColor)
        }
    }

    fun updateUI(setColor: Boolean){
        translationMsgTxtView.text = message
        if(setColor == true){
            translationMsgTxtView.setTextColor(Color.parseColor("#FF0000"))
        }
        else {
            translationMsgTxtView.setTextColor(Color.parseColor("black"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("message", message)
        outState.putBoolean("setColor", setColor)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        message = savedInstanceState.getString("message", "")
        setColor = savedInstanceState.getBoolean("setColor", setColor)
        updateUI(setColor)
    }
}