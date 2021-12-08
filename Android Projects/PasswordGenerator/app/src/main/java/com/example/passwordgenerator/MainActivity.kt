package com.example.passwordgenerator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //initialize
    lateinit var layoutRoot : ConstraintLayout
    lateinit var enterPasswordField: TextView
    lateinit var radioGroupNumID: RadioGroup
    lateinit var radioGroupSpecialID: RadioGroup
    lateinit var displayPassword: TextView
    lateinit var yourPassIsID: TextView
    lateinit var passHistoryTextView: TextView
    lateinit var passHist1: TextView
    lateinit var passHist2: TextView
    lateinit var passHist3: TextView
    lateinit var switchHidePass: Switch
    var btClicks = 0
    var prevPass = ""
    private var newPassword = ""
    var passHistArr: Array<String> = arrayOf("","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get view items
        layoutRoot = findViewById(R.id.root_layout)
        enterPasswordField = findViewById<TextView>(R.id.enterPasswordField)
        radioGroupNumID = findViewById<RadioGroup>(R.id.radioGroupNum)
        radioGroupSpecialID = findViewById<RadioGroup>(R.id.radioGroupSpecial)
        displayPassword = findViewById<TextView>(R.id.newPasswordID)
        yourPassIsID = findViewById<TextView>(R.id.yourPassIsID)
        passHistoryTextView = findViewById<TextView>(R.id.passHistoryTextView)
        passHist1 = findViewById<TextView>(R.id.passHist1)
        passHist2 = findViewById<TextView>(R.id.passHist2)
        passHist3 = findViewById<TextView>(R.id.passHist3)
        switchHidePass = findViewById<Switch>(R.id.switchHidePass)

    }

    private fun generatePassword(numberQty: Int, specialQty: Int, wordAttach: CharSequence){
        var numbersArr: Array<String> = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        var specialArr: Array<String> = arrayOf("@", "#", "$", "%", "&", ".", "!", "?")
        var rand:  Int
        var randomValue: String
        newPassword = wordAttach.toString()

        for(i in 0 until numberQty){
            rand = (numbersArr.indices).random()
            randomValue = numbersArr[rand] //save a random number from the numbersArr array
            //rand = (0..newPassword.length).random() //get a random index of newPassword string and append the number value on that index
            //newPassword += newPassword.get(rand)+randomValue
            newPassword += randomValue
        }
        for(i in 0 until specialQty){
            rand = (specialArr.indices).random()
            randomValue = specialArr[rand] //save a random number from the numbersArr array
            newPassword += randomValue
        }

        passHistArr[2] = passHistArr[1]
        passHistArr[1] = passHistArr[0]
        passHistArr[0] = prevPass
        updateUI(newPassword, prevPass, btClicks, passHistArr)
    }

    fun getInput(view: View){
        var wordAttach = enterPasswordField.text
        var numberQtyID = radioGroupNumID.checkedRadioButtonId
        var specialQtyID = radioGroupSpecialID.checkedRadioButtonId
        var numberQty: Int
        var specialQty: Int
        var alertSnackbar = Snackbar.make(layoutRoot, "", Snackbar.LENGTH_SHORT)

        btClicks += 1

        //check if user entered a word to append
        if(wordAttach.isEmpty()){
            alertSnackbar = Snackbar.make(layoutRoot, "Please enter a word", Snackbar.LENGTH_LONG)
            alertSnackbar.show()
        }

        //check if user have selected radious buttons
        else if(numberQtyID == -1 && specialQtyID == -1){
            alertSnackbar = Snackbar.make(layoutRoot, "Please select both quantity of numbers and special characters", Snackbar.LENGTH_LONG)
            alertSnackbar.show()
        }
        else if(numberQtyID == -1){
            alertSnackbar = Snackbar.make(layoutRoot, "Please select quantity of numbers", Snackbar.LENGTH_SHORT)
            alertSnackbar.show()
        }
        else if(specialQtyID == -1){
            alertSnackbar = Snackbar.make(layoutRoot, "Please select quantity of special characters", Snackbar.LENGTH_SHORT)
            alertSnackbar.show()
        }
        //if user filled radios buttons with input, store values
        else{
            numberQty = findViewById<RadioButton>(numberQtyID).text.toString().toInt()
            specialQty = findViewById<RadioButton>(specialQtyID).text.toString().toInt()
            generatePassword(numberQty, specialQty, wordAttach)
        }
    }

    fun hidePassword(view: View){
        updateUI(newPassword, prevPass, btClicks, passHistArr)
    }

    private fun updateUI(newPassword: String, prevPass: String, btClicks: Int, passHistArr: Array<String>){
        var alertSnackbar = Snackbar.make(layoutRoot, "", Snackbar.LENGTH_SHORT)
        //Alert user to enter a bigger word in order to have a strong password
        if(newPassword.length < 8 && btClicks > 0){
            alertSnackbar = Snackbar.make(layoutRoot, "Consider adding a bigger word to have a stronger password generated", Snackbar.LENGTH_LONG)
            alertSnackbar.show()
        }
        yourPassIsID.text = "Your new password is: "
        displayPassword.text = newPassword

        if(btClicks > 1){
            passHistoryTextView.text = "Password history:"
            passHist3.text = passHistArr[2]
            passHist2.text = passHistArr[1]
            passHist1.text = passHistArr[0]
        }
        else{
            passHist1.text = ""
            passHist2.text = ""
            passHist3.text = ""
        }


        if(switchHidePass.isChecked){
            displayPassword.text = "--------"
            passHistoryTextView.text = "Password history hidden"
            passHist1.setTextColor(Color.parseColor("#FFFFFF"))
            passHist2.setTextColor(Color.parseColor("#FFFFFF"))
            passHist3.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else{
            passHist1.setTextColor(Color.parseColor("#000000"))
            passHist2.setTextColor(Color.parseColor("#000000"))
            passHist3.setTextColor(Color.parseColor("#000000"))
        }
        this.prevPass = newPassword
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("password", newPassword)
        outState.putString("prevPass", prevPass)
        outState.putInt("btClicks", btClicks)
        outState.putStringArray("passHistArr", passHistArr)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        newPassword = savedInstanceState.getString("password", "")
        prevPass = savedInstanceState.getString("prevPass", "")
        btClicks = savedInstanceState.getInt("btClicks", btClicks)
        passHistArr = savedInstanceState.getStringArray("passHistArr") as Array<String>
        updateUI(newPassword, prevPass, btClicks, passHistArr)
    }
}