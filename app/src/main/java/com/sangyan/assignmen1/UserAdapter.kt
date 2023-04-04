package com.sangyan.assignmen1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val userList : ArrayList<User>) :  RecyclerView.Adapter<UserAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTextView.text = "Name: "+user.name
        holder.emailTextView.text = "Email: "+user.email
        holder.contactTextView.text = "Contact No: " +user.contact
        holder.genderTextView.text = "Gender: " + user.gender
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.user_name)
        val emailTextView : TextView = itemView.findViewById(R.id.user_email)
        val contactTextView : TextView = itemView.findViewById(R.id.user_contact)
        val genderTextView : TextView = itemView.findViewById(R.id.user_gender)

    }
}