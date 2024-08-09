package com.rmdtya.postaltest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class UserAdapter(private val context: Context, private val userList: ArrayList<User>) : BaseAdapter() {

    override fun getCount(): Int = userList.size

    override fun getItem(position: Int): Any = userList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        val user = userList[position]

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val ivAvatar = view.findViewById<ImageView>(R.id.ivAvatar)

        // Set user name and email
        tvName.text = "${user.firstName} ${user.lastName}"
        tvEmail.text = user.email

        // Load avatar image using Glide and make it circular
        Glide.with(context)
            .load(user.avatar)
            .circleCrop()
            .into(ivAvatar)

        return view
    }
}



data class User(val firstName: String, val lastName: String, val email: String, val avatar: String)