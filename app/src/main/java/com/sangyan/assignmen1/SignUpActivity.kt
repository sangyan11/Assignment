package com.sangyan.assignmen1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
      database = FirebaseDatabase.getInstance()
        val signIn_btn = findViewById<Button>(R.id.sign_up_button)
        val user_name = findViewById<EditText>(R.id.et_name)
        val user_email = findViewById<EditText>(R.id.et_email)
        val user_password = findViewById<EditText>(R.id.et_password)
        val user_contact = findViewById<EditText>(R.id.et_contact)
        val user_confirm_password = findViewById<EditText>(R.id.et_confirm_password)
        val user_gender = findViewById<EditText>(R.id.et_gender)
        signIn_btn.setOnClickListener {
            val name = user_name.text.toString()
            val email = user_email.text.toString()
            val password = user_password.text.toString()
            val contact = user_contact.text.toString()
            val confirmPassword = user_confirm_password.text.toString()
            val gender = user_gender.text.toString()
            if(name.isNotEmpty() && email.isNotEmpty() &&password.isNotEmpty() && confirmPassword.isNotEmpty() && contact.isNotEmpty() && gender.isNotEmpty()) {


                if (password == confirmPassword) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful) {
                                val user = User(name,email,contact,gender)

                                // Save the user's data to Firebase Realtime Database
                                val userId = auth.currentUser?.uid
                                database.getReference("users").child(userId!!).setValue(user)
                                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                }
            }
            else {
                Toast.makeText(this,"Empty Fields Not Allowed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}