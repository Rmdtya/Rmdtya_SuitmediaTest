package com.rmdtya.postaltest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class FirstScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        val etName = findViewById<EditText>(R.id.etName)
        val etSentence = findViewById<EditText>(R.id.etSentence)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val btnNext = findViewById<Button>(R.id.btnNext)

        btnCheck.setOnClickListener {
            val sentence = etSentence.text.toString()
            val cleanedSentence = cleanText(sentence)
            if (isPalindrome(cleanedSentence)) {
                showDialog("isPalindrome")
            } else {
                showDialog("not palindrome")
            }
        }

        btnNext.setOnClickListener {
            val name = etName.text.toString()
            val intent = Intent(this, SecondScreen::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
        }
    }

    private fun cleanText(text: String): String {
        // Remove non-alphanumeric characters and convert to lowercase
        return text.replace("[^a-zA-Z0-9]".toRegex(), "").toLowerCase()
    }

    private fun isPalindrome(text: String): Boolean {
        val reversedText = text.reversed()
        return text.equals(reversedText, ignoreCase = true)
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}