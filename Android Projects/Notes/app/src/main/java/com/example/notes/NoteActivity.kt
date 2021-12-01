package com.example.notes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notes.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteBinding

    lateinit var  infoNoteTextView: TextView
    lateinit var editTextNote: EditText
    private var companyName: String? = null
    private var companyUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //views
        infoNoteTextView = findViewById(R.id.infoNoteTextView)
        editTextNote = findViewById(R.id.editTextNote)

        //intent data
        companyName = intent.getStringExtra("company")
        companyUrl = intent.getStringExtra("url")

        //edit text field on content_note
        companyName?.let { infoNoteTextView.text = "Click on the button below and also add your note on $companyName" }


        binding.fab.setOnClickListener { view -> loadWebSite() }
    }

    override fun onBackPressed() {
        val data = Intent()
        data.putExtra("note", editTextNote.text.toString())
        setResult(Activity.RESULT_OK, data)
        super.onBackPressed()
        finish()
    }

    private fun loadWebSite(){
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = companyUrl?.let{ Uri.parse(companyUrl) }
        startActivity(intent)
    }

}