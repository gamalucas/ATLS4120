package com.example.notes

import android.app.Activity
import android.content.Intent
import com.example.notes.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var noteOptionButton: Button
    lateinit var noteTextView: TextView
    lateinit var spinner: Spinner
    private var myNoteOption =NoteOption();
    private val REQUEST_CODE = 1
    var noteValue: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //views
        noteOptionButton = findViewById(R.id.noteOptionButton)
        noteTextView = findViewById(R.id.noteTextView)
        spinner = findViewById(R.id.spinner)

        var spinnerValue = 0

        noteOptionButton.setOnClickListener{
            spinnerValue = spinner.selectedItemPosition
            myNoteOption.getUserOption(spinnerValue)

            //create intent
            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("company", myNoteOption.company)
            intent.putExtra("url", myNoteOption.url)

            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            noteValue = data?.let{data.getStringExtra("note")}.toString()
            //noteTextView.setText(data?.let{data.getStringExtra("note")})
            updateUI()
        }
    }

    fun updateUI(){
        noteTextView.text = noteValue
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("note", noteValue)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        noteValue = savedInstanceState.getString("note", "")
        updateUI()
    }
}