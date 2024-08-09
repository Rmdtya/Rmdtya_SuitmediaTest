package com.rmdtya.postaltest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class ThirdScreen : AppCompatActivity() {
    private lateinit var lvUsers: ListView
    private val userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        lvUsers = findViewById(R.id.lvUsers)
        val backBtn: ImageView = findViewById(R.id.back_btn)

        fetchUsers()

        lvUsers.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = userList[position]
            val intent = Intent()
            intent.putExtra("SELECTED_USER", "${selectedUser.firstName} ${selectedUser.lastName}")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        backBtn.setOnClickListener {
            onBackPressed() // Fungsi ini akan meniru fungsi tombol kembali
        }
    }

    private fun fetchUsers() {
        val url = "https://reqres.in/api/users?page=1&per_page=8"
        val requestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val dataArray = response.getJSONArray("data")
                    for (i in 0 until dataArray.length()) {
                        val userObject = dataArray.getJSONObject(i)
                        val user = User(
                            userObject.getString("first_name"),
                            userObject.getString("last_name"),
                            userObject.getString("email"),
                            userObject.getString("avatar")
                        )
                        userList.add(user)
                    }
                    val adapter = UserAdapter(this, userList)
                    lvUsers.adapter = adapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, "Failed to fetch users", Toast.LENGTH_SHORT).show()
            })

        requestQueue.add(jsonObjectRequest)
    }
}