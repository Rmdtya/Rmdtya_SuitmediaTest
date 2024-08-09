package com.rmdtya.postaltest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondScreen : AppCompatActivity() {
    private lateinit var tvSelectedUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        tvSelectedUser = findViewById(R.id.tvSelectedUser)
        val btnChooseUser = findViewById<Button>(R.id.btnChooseUser)
        val backBtn: ImageView = findViewById(R.id.back_btn)

        val name = intent.getStringExtra("USER_NAME")
        tvUserName.text = "$name"

        btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreen::class.java)
            startActivityForResult(intent, 100)
        }


        backBtn.setOnClickListener {
            onBackPressed() // Fungsi ini akan meniru fungsi tombol kembali
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedUser = data?.getStringExtra("SELECTED_USER")
            tvSelectedUser.text = selectedUser
        }
    }
}